package com.valleon.rewardyourteacherapi.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String transactionType;
    private String description;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentSchool;
    private Long transactionId;
}
