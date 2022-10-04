package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.*;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.TransactionType;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.service.payload.PaymentService;
import com.valleon.rewardyourteacherapi.service.payload.WalletService;
import com.valleon.rewardyourteacherapi.service.payload.request.FundWalletRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.PayStackTransactionRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.PayStackTransactionResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.PaymentResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.WalletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class WalletServiceImpl implements WalletService {

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    private final WalletDao walletDao;

    private final ApiResponse successResponse;

    private final ApiResponse walletResponse;

    private final PaymentService paymentService;

    private final TransactionDao transactionDao;

    private final AppUserDao appUserDao;

    @Override
    public WalletResponse getStudentWalletBalance() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserDetails)){
            throw  new CustomNotFoundException("user not found.");
        }

        String email = ((UserDetails)principal).getUsername();

        AppUser appUser = appUserDao.findAppUserByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException("User not found"));

        Student student = studentDao.getStudentByAppUser(appUser);

        Wallet wallet = walletDao.findWalletByStudent(student)
                .orElseThrow(()-> new CustomNotFoundException("User not found"));

        return  walletResponse.checked(wallet.getBalance(),wallet.getTotalMoneySpent());
    }

    @Override
    public PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationException("User not Authenticated");
        }
        String email = authentication.getName();
        PayStackTransactionRequest payStackTransactionRequest = PayStackTransactionRequest
                .builder()
                .email(email)
                .amount(new BigDecimal(fundWalletRequest.getAmount()))
                .build();

        PayStackTransactionResponse transactionResponse = paymentService.initTransaction(payStackTransactionRequest);
        if (!transactionResponse.isStatus()) {
            throw new Exception("Payment not authorized");
        }
        AppUser appUserEntity = appUserDao.findAppUserByEmail(email)
                .orElseThrow(()->new CustomNotFoundException("Entity not found"));
        Student student = studentDao.getStudentByAppUser(appUserEntity);
        Optional<Wallet> wallet = walletDao.findWalletByStudent(student);
        if (wallet.isEmpty()) {
            Wallet walletDao1 = Wallet.builder()
                    .balance(new BigDecimal(fundWalletRequest.getAmount()))
                    .student(student)
                    .totalMoneySpent(new BigDecimal(fundWalletRequest.getAmount())).build();
            walletDao.saveRecord(walletDao1);
            Transaction transaction = Transaction.builder()
                    .transactionType(TransactionType.DEBIT.name())
                    .student(student)
                    .amount(new BigDecimal(fundWalletRequest.getAmount()))
                    .description(transactionResponse.getMessage())
                    .build();
            transactionDao.saveRecord(transaction);
            return successResponse.success("Success", LocalDateTime.now());

        }
        BigDecimal result = wallet.get().getBalance().add(new BigDecimal(fundWalletRequest.getAmount()));
        wallet.get().setBalance(result);
        wallet.get().setStudent(student);
        wallet.get().setTotalMoneySpent(new BigDecimal(fundWalletRequest.getAmount()));
        walletDao.saveRecord(wallet.get());

        Transaction transaction = Transaction
                .builder()
                .transactionType(TransactionType.DEBIT.name())
                .student(student)
                .amount(new BigDecimal(fundWalletRequest.getAmount()))
                .description(transactionResponse.getMessage())
                .build();
        transactionDao.saveRecord(transaction);
        return successResponse.success("Success", LocalDateTime.now());
    }

    @Override
    public WalletResponse getTeacherWalletResponse() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserDetails)){
            throw new CustomNotFoundException("User not found");
        }

        String email = ((UserDetails)principal).getUsername();

        AppUser appUser = appUserDao.findAppUserByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException("User not found."));

        Teacher teacher = teacherDao.getTeacherByAppUser(appUser);

        Wallet wallet = walletDao.findWalletByTeacher(teacher.getId())
                .orElseThrow(()->  new CustomNotFoundException("Wallet not found."));

        return walletResponse.checked(wallet.getBalance(), wallet.getTotalMoneySpent());
    }




}
