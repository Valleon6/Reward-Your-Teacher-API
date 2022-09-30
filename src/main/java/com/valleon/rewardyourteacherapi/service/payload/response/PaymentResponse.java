package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {
    private String message;
    private LocalDateTime timeStamp;

}
