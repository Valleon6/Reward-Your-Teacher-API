package com.valleon.rewardyourteacherapi.usecase.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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

}
