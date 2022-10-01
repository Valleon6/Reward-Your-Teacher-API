package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.request.FundWalletRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.PaymentResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.WalletResponse;

public interface WalletService {
    WalletResponse getStudentWalletBalance();

    PaymentResponse fundWallet(FundWalletRequest fundWalletRequest) throws Exception;


    WalletResponse getTeacherWalletResponse();
}
