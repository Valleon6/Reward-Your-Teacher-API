package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.usecase.payload.request.LoginRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.SocialLoginRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.LoginResponse;

public interface LoginService {
    LoginResponse loginStudent(LoginRequest loginRequest);

    LoginResponse studentSocialLogin(SocialLoginRequest socialLoginRequest);

    LoginResponse loginTeacher(LoginRequest loginRequest);

    LoginResponse teacherSocialLogin(SocialLoginRequest socialLoginRequest);



}
