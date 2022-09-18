package com.valleon.rewardyourteacherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID =1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public BadRequestException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s is not with %s : '%s'", resourceName,fieldName,fieldValue ));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
