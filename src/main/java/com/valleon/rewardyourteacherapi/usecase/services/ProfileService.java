package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.usecase.payload.request.StudentProfileRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.TeacherProfileRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.EditProfileResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.ViewTeacherProfileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {
    EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest);

    EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException;

    List<ViewTeacherProfileResponse> viewTeacherByName(String name);

}
