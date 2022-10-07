package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(String message) {
        super(message);
    }

}
