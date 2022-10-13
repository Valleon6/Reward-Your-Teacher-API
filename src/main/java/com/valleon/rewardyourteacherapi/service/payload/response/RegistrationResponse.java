package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse<T> {
//    private String message;
//    private LocalDateTime timeStamp;
//    private T payload;

    private String name;
    private String email;
    private long id;
}
