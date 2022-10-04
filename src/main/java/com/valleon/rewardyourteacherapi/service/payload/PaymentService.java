package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.request.PayStackTransactionRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.PayStackTransactionResponse;

public interface PaymentService {
PayStackTransactionResponse initTransaction(PayStackTransactionRequest request) throws Exception;

}
