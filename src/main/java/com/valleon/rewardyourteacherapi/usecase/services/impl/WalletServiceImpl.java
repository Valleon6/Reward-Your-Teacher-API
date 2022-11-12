package com.valleon.rewardyourteacherapi.usecase.services.impl;

import com.valleon.rewardyourteacherapi.domain.dao.*;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.NotificationType;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.domain.entities.enums.TransactionType;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.configuration.security.UserDetails;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.usecase.payload.request.EmailDetailsRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.FundWalletRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.PayStackTransactionRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.PayStackTransactionResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.PaymentResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.WalletResponse;
import com.valleon.rewardyourteacherapi.usecase.services.PaymentService;
import com.valleon.rewardyourteacherapi.usecase.services.WalletService;
import com.valleon.rewardyourteacherapi.utilities.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;


@Service
@AllArgsConstructor
@Transactional
public class WalletServiceImpl implements WalletService {

    private final EmailService emailService;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    private final WalletDao walletDao;
    private final PaymentService paymentService;

    private final TransactionDao transactionDao;

    private final AppUserDao appUserDao;
    private final NotificationDao notificationDao;

    @Override
    public WalletResponse getStudentWalletBalance() {

        String email = UserDetails.getLoggedInUserDetails();
        AppUser appUser = appUserDao.findAppUserByEmailAndRole(email, Role.STUDENT);
        if (appUser == null) {
            throw new CustomNotFoundException("User not found");
        }

        Student student = studentDao.getStudentByAppUser(appUser);

        if (student == null) {
            throw new CustomNotFoundException("Invalid user");
        }
        Wallet wallet = walletDao.findWalletByStudent(student);
        if (wallet == null) {
            throw new CustomNotFoundException("Wallet not found or phone number missing");
        }

        return new WalletResponse(wallet.getBalance(),wallet.getTotalMoneySent());
    }

    @Override
    public WalletResponse getTeacherWalletBalance() {
        String email = UserDetails.getLoggedInUserDetails();
        AppUser appUser = appUserDao.findAppUserByEmailAndRole(email, Role.TEACHER);
        if (appUser == null) {
            throw new CustomNotFoundException("User not found");
        }
        Teacher teacher = teacherDao.getTeacherByAppUser(appUser);
        if (teacher == null) {
            throw new CustomNotFoundException("Invalid user");
        }


        Wallet wallet = walletDao.findWalletByTeacher(teacher)
                .orElseThrow(() -> new CustomNotFoundException("Wallet not found or Phone number missing"));

        return new WalletResponse(wallet.getBalance(),wallet.getTotalMoneySent());
    }

    @Override
    public PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception {

        fundWalletRequest.setAmount(String.valueOf(Integer.parseInt(fundWalletRequest.getAmount())* 100));
        fundWalletRequest.setAmount(fundWalletRequest.getAmount());

        String email = UserDetails.getLoggedInUserDetails();
        PayStackTransactionRequest payStackTransactionRequest = PayStackTransactionRequest
                .builder()
                .email(email)
                .amount(new BigDecimal(fundWalletRequest.getAmount()))
                .build();

        PayStackTransactionResponse transactionResponse = paymentService.initTransaction(payStackTransactionRequest);

        if (!transactionResponse.isStatus()) {
            throw new Exception("Payment not authorized");
        }
        AppUser appUser = appUserDao.findAppUserByEmailAndRole(email, Role.STUDENT);
        if (appUser == null) {
            throw new CustomNotFoundException("Entity not found");
        }
        fundWalletRequest.setAmount(String.valueOf(Integer.parseInt(fundWalletRequest.getAmount())/ 100));

        double amount = Double.parseDouble(fundWalletRequest.getAmount());
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        Student student = studentDao.getStudentByAppUser(appUser);
        Wallet wallet = walletDao.findWalletByStudent(student);
        if (wallet == null) {
            Wallet walletDao1 = Wallet.builder()
                    .balance(new BigDecimal(fundWalletRequest.getAmount()))
                    .student(student)
                    .build();
            walletDao.saveRecord(walletDao1);
            Transaction transaction = Transaction.builder()
                    .transactionType(TransactionType.CREDIT.name())
                    .student(student)
                    .amount(new BigDecimal(fundWalletRequest.getAmount()))
                    .description(transactionResponse.getMessage())
                    .build();
            transactionDao.saveRecord(transaction);
            return new PaymentResponse("Success");

        }
        BigDecimal result = wallet.getBalance().add(new BigDecimal(fundWalletRequest.getAmount()));
        wallet.setBalance(result);
        wallet.setStudent(student);
        walletDao.saveRecord(wallet);

        Transaction transaction = Transaction
                .builder()
                .transactionType(TransactionType.DEBIT.name())
                .student(student)
                .amount(new BigDecimal(fundWalletRequest.getAmount()))
                .description(transactionResponse.getMessage())
                .build();
        transactionDao.saveRecord(transaction);

        Notification notification = Notification.builder()
                .notificationType(NotificationType.CREDIT_NOTIFICATION)
                .student(student)
                .transaction(transaction)
                .message("You Funded your wallet with ₦"+formatter.format(amount)).build();
        notificationDao.saveRecord(notification);

        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.WalletFundingEmail(student.getName(), fundWalletRequest.getAmount()))
                .subject("Valleon6 email")
                .recipient(appUser.getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        return new PaymentResponse(transactionResponse.getData().getReference());
    }




}
