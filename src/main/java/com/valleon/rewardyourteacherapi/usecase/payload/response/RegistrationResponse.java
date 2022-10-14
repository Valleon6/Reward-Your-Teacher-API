package com.valleon.rewardyourteacherapi.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
