package com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

//    public ResponseEntity<?> UserNotFoundException(CustomNotFoundException ex){
//        return new ResponseEntity<>();
//    }
}
