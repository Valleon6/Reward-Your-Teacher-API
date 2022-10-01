package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getStudentTransactions(int offset, int pageSize);

    List<TransactionResponse> getTeacherTransactions(int pffset, int pageSize);
}
