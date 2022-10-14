package com.valleon.rewardyourteacherapi.usecase.services.impl;


import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.dao.WalletDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.InsufficientBalanceException;
import com.valleon.rewardyourteacherapi.usecase.payload.request.SendRewardRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.SendRewardResponse;
import com.valleon.rewardyourteacherapi.usecase.services.SendRewardService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class SendRewardServiceImpl implements SendRewardService {

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    private final WalletDao walletDao;

    private final AppUserDao appUserDao;



    @Override
    public SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new CustomNotFoundException("user not found");
        }

        String email = ((UserDetails) principal).getUsername();


        AppUser appUser = appUserDao.findAppUserByEmail(email);

        if (appUser == null){
            throw new CustomNotFoundException("user not found");
        }

        Student student = studentDao.getStudentByAppUser(appUser);

        Teacher teacher = teacherDao.getTeacherByNameAndPhoneNumber(sendRewardRequest.getTeacherName(), sendRewardRequest.getTeacherPhone());

        if (teacher != null) {
            if (getStudentWalletBalance(student).compareTo(sendRewardRequest.getAmount()) >= 0) {

                if(sendRewardRequest.getAmount().compareTo(new BigDecimal("0.00")) <= 0){
                    throw new IllegalArgumentException("invalid amount");
                }

                student.getWallet().setBalance(student.getWallet().getBalance().subtract(sendRewardRequest.getAmount()));
                teacher.getWallet().setBalance(teacher.getWallet().getBalance().add(sendRewardRequest.getAmount()));
                studentDao.saveRecord(student);
                teacherDao.saveRecord(teacher);

            } else {
                throw new InsufficientBalanceException("Unable to perform transaction because of an insufficient balance");
            }

        } else {
            throw new CustomNotFoundException("teacher not found");
        }


        return new SendRewardResponse(true,
                "money sent successfully to " + teacher.getName()
                        + "you now have " + student.getWallet().getBalance() + "in your wallet", LocalDateTime.now()
        );
    }

    @Override
    public BigDecimal getStudentWalletBalance(Student student) {


        Wallet wallet = walletDao.findWalletByStudent(student);
        if (wallet == null){
            throw new CustomNotFoundException("user wallet does not exist");
        }
        return wallet.getBalance();
    }


}
