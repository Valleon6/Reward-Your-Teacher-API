package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.usecase.payload.request.PayStackTransactionRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.PayStackTransactionResponse;

public interface PaymentService {
PayStackTransactionResponse initTransaction(PayStackTransactionRequest request) throws Exception;

}
