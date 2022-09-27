package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

public class AuthenticationFailedException extends RuntimeException {

    public AuthenticationFailedException(String message) {
        super(message);
    }


}
