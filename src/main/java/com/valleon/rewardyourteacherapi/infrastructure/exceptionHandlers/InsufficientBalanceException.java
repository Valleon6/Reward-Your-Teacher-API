package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }
}
