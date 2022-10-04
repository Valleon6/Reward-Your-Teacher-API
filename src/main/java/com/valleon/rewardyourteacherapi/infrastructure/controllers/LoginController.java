package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.LoginService;
import com.valleon.rewardyourteacherapi.service.payload.request.LoginRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/student")
    public ResponseEntity<LoginResponse> loginStudent(@Valid @RequestBody LoginRequest loginRequest){
        return  new ResponseEntity<>(loginService.loginStudent(loginRequest), HttpStatus.ACCEPTED);
    }


}
