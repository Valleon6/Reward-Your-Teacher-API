package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.usecase.payload.request.SendRewardRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.SendRewardResponse;

import java.math.BigDecimal;

public interface SendRewardService {
    SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest);

    BigDecimal getStudentWalletBalance(Student student);
}
