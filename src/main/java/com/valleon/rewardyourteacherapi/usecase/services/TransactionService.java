package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.usecase.payload.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getStudentTransactions(int offset, int pageSize);

    List<TransactionResponse> getTeacherTransactions(int offset, int pageSize);
}
