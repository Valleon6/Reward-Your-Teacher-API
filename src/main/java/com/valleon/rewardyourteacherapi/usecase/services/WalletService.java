package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.usecase.payload.request.FundWalletRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.PaymentResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.WalletResponse;

public interface WalletService {
    WalletResponse getStudentWalletBalance();

    PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception;


    WalletResponse getTeacherWalletBalance();
}
