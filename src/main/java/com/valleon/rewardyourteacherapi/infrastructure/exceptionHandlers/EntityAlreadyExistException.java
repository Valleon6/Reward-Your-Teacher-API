package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
