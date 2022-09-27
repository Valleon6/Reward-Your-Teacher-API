package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

public class JwtInvalidException extends RuntimeException{

    public JwtInvalidException(String message) {
        super(message);
    }
}
