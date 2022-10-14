package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.usecase.services.LoginService;
import com.valleon.rewardyourteacherapi.usecase.payload.request.LoginRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.SocialLoginRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.LoginResponse;
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
    public ResponseEntity<ApiResponse<LoginResponse>>loginStudent(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse response  = loginService.loginStudent(loginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response), HttpStatus.ACCEPTED);
    }

    @PostMapping("/teacher")
    public ResponseEntity<ApiResponse<LoginResponse>> loginTeacher(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse response = loginService.loginTeacher(loginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response), HttpStatus.ACCEPTED);
    }

    @PostMapping("/social/student")
    public ResponseEntity<ApiResponse<LoginResponse>>studentSocialLogin(@Valid @RequestBody SocialLoginRequest socialLoginRequest){
        LoginResponse response = loginService.studentSocialLogin(socialLoginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response),HttpStatus.ACCEPTED);
    }

    @PostMapping("/social/teacher")
    public ResponseEntity<ApiResponse<LoginResponse>> teacherSocialLogin(@Valid @RequestBody SocialLoginRequest socialLoginRequest){
        LoginResponse response = loginService.teacherSocialLogin(socialLoginRequest);
        return new ResponseEntity<>(new ApiResponse<>("Login Successful",true,response), HttpStatus.ACCEPTED);
    }

}
