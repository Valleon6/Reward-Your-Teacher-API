package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public interface TeacherProfileService {
    EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException;


}
