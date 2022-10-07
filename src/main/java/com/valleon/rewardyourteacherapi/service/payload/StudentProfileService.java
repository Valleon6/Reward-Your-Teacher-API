package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.request.StudentProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;

public interface StudentProfileService {
    EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest);

}
