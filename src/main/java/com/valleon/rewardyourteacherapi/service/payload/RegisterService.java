package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RegisterService {
    RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest);

    RegistrationResponse registerTeacher(TeacherRegistrationRequest teacherRegistrationRequest, MultipartFile file) throws IOException;
}
