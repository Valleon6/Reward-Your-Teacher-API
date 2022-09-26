package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class RegistrationResponse<T> {
    private String message;
    private LocalDateTime timeStamp;
    private T payload;
}
