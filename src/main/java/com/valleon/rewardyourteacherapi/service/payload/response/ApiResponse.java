package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Service
public class ApiResponse<T> {
    public RegistrationResponse created(String success, LocalDateTime localDateTime, T payload){
        return new RegistrationResponse<>(success, localDateTime, payload);
    }

//    private String message;
//    private boolean success;
//    private T payload;

    
}
