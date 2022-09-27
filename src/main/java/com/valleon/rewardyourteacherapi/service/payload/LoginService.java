package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.request.LoginRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.LoginResponse;

public interface LoginService {
    LoginResponse loginStudent(LoginRequest loginRequest);

    LoginResponse StudentSocialLogin(SocialLoginRequest socialLoginRequest)

    LoginResponse loginTeacher(LoginRequest loginRequest);



}
