package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
//@Service
public class ApiResponse<T> {

    private String message;

    private boolean status = false;

    private LocalDateTime timeCreated = LocalDateTime.now();

    private T data;

    public ApiResponse(String message, boolean status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    //    public RegistrationResponse created(String success, LocalDateTime localDateTime, T payload){
//        return new RegistrationResponse<>(success, localDateTime, payload);
//    }
//
//    public LoginResponse accepted(String success,LocalDateTime localDateTime){
//        return new LoginResponse(success, localDateTime);
//    }
//
//    public EditProfileResponse edited(String success, LocalDateTime localDateTime){
//        return new EditProfileResponse(success, localDateTime);
//    }
//
//    public PaymentResponse success(String success, LocalDateTime localDateTime){
//        return new PaymentResponse(success, localDateTime);
//    }
//    public WalletResponse checked(BigDecimal balance, BigDecimal totalMoneySpent) {
//        return new WalletResponse(balance, totalMoneySpent);
//    }
    
}
