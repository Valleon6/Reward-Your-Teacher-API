package com.valleon.rewardyourteacherapi.service;

import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

public interface RegisterService {
    RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest);

//    RegistrationResponse registerTeacher(TeacherRegistrationRequest teacherRegistrationRequest, MultipartFile file) throws IOException;
}
