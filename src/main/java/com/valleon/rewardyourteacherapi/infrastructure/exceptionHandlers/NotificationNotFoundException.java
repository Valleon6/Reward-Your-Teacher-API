package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

public class NotificationNotFoundException extends RuntimeException{

    public NotificationNotFoundException(String message){
        super(message);
    }
}
