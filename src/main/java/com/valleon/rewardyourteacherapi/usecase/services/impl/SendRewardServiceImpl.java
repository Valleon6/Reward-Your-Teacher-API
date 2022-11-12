package com.valleon.rewardyourteacherapi.usecase.services.impl;


import com.valleon.rewardyourteacherapi.domain.dao.*;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.NotificationType;
import com.valleon.rewardyourteacherapi.domain.entities.enums.TransactionType;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.InsufficientBalanceException;
import com.valleon.rewardyourteacherapi.usecase.payload.request.EmailDetailsRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.SendRewardRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.SendRewardResponse;
import com.valleon.rewardyourteacherapi.usecase.services.SendRewardService;
import com.valleon.rewardyourteacherapi.utilities.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class SendRewardServiceImpl implements SendRewardService {

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    private final WalletDao walletDao;

    private final AppUserDao appUserDao;

    private final EmailService emailService;

    private final TransactionDao transactionDao;

    private final NotificationDao notificationDao;


    @Override
    public SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest) throws Exception {

        double amount = Double.parseDouble(sendRewardRequest.getAmount().toString());
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new CustomNotFoundException("user not found");
        }

        String email = ((UserDetails) principal).getUsername();


        AppUser appUser = appUserDao.findAppUserByEmail(email);

        if (appUser == null) {
            throw new CustomNotFoundException("user not found");
        }

        Student student = studentDao.getStudentByAppUser(appUser);

        Teacher teacher = teacherDao.getTeacherByNameAndPhoneNumber(sendRewardRequest.getTeacherName(), sendRewardRequest.getTeacherPhone());
        Transaction teacherTransaction;
        if (teacher != null) {
            if (getStudentWalletBalance(student).compareTo(sendRewardRequest.getAmount()) >= 0) {

                if (sendRewardRequest.getAmount().compareTo(new BigDecimal("0.00")) <= 0) {
                    throw new IllegalArgumentException("Invalid amount");
                }

                student.getWallet().setBalance(student.getWallet().getBalance().subtract(sendRewardRequest.getAmount()));
                teacher.getWallet().setBalance(teacher.getWallet().getBalance().add(sendRewardRequest.getAmount()));
                studentDao.saveRecord(student);
                teacherDao.saveRecord(teacher);

                teacherTransaction = Transaction.builder()
                        .teacher(teacher)
                        .student(student)
                        .description(student.getName() + "Sent ₦" + formatter.format(amount) + "to you")
                        .transactionType(TransactionType.CREDIT.toString())
                        .amount(sendRewardRequest.getAmount())
                        .build();
                transactionDao.saveRecord(teacherTransaction);

                BigDecimal tmpTotalMoney = student.getWallet().getTotalMoneySent();
                BigDecimal totalMoney = tmpTotalMoney.add(sendRewardRequest.getAmount());
                student.getWallet().setTotalMoneySent(totalMoney);

            } else {
                throw new InsufficientBalanceException("Unable to perform transaction because of an insufficient balance");
            }

        } else {
            throw new CustomNotFoundException("teacher not found");
        }

        Notification notification = new Notification();
        notification.setMessage(student.getName() + " sent you ₦" + formatter.format(amount) + " reward");
        notification.setNotificationType(NotificationType.CREDIT_NOTIFICATION);
        notification.setTeacher(teacher);
        notification.setTransaction(teacherTransaction);
        notificationDao.saveRecord(notification);

        Notification notification1 = new Notification();
        notification1.setMessage(student.getName() + ", you just sent ₦" + formatter.format(amount) + " to " + teacher.getName());
        notification1.setNotificationType(NotificationType.CREDIT_NOTIFICATION);
        notification1.setStudent(student);
        notificationDao.saveRecord(notification1);

        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.receiveFundsEmail(teacher.getName(), student.getName(), sendRewardRequest.getAmount().toString()))
                .subject("Valleon email")
                .recipient(teacher.getAppUser().getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        EmailDetailsRequest emailDetailsRequest2 = EmailDetailsRequest.builder()
                .msgBody(emailService.sendFundsEmail(teacher.getName(), student.getName(), sendRewardRequest.getAmount().toString()))
                .subject("Valleon email")
                .recipient(appUser.getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest2);


        return new SendRewardResponse(true,
                "money sent successfully to " + teacher.getName()
                        + "you now have " + student.getWallet().getBalance() + "in your wallet", LocalDateTime.now()
        );
    }

    @Override
    public BigDecimal getStudentWalletBalance(Student student) {


        Wallet wallet = walletDao.findWalletByStudent(student);
        if (wallet == null) {
            throw new CustomNotFoundException("user wallet does not exist");
        }
        return wallet.getBalance();
    }


}
