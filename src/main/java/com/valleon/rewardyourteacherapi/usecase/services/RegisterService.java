package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.usecase.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.RegistrationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RegisterService {
    RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) throws Exception;

    RegistrationResponse registerTeacher(TeacherRegistrationRequest teacherRegistrationRequest, MultipartFile file) throws Exception;

    Object verifyUser(String userToken);

}
