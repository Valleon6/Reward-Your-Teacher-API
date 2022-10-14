package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.usecase.services.ProfileService;
import com.valleon.rewardyourteacherapi.usecase.payload.request.StudentProfileRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.TeacherProfileRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.EditProfileResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.ViewTeacherProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService studentProfileService;
    private final ProfileService teacherProfileService;

    @PutMapping("/edit/student")
    public ResponseEntity<ApiResponse<EditProfileResponse>> editStudentProfile(@Valid @RequestBody StudentProfileRequest studentProfileRequest) {
        EditProfileResponse editProfileResponse = studentProfileService.editStudentProfile(studentProfileRequest);

        return new ResponseEntity<>(new ApiResponse<>("Edited successfully", true, editProfileResponse), HttpStatus.OK);
    }

    @PutMapping("/edit/teacher")
    public ResponseEntity<ApiResponse<EditProfileResponse>>editTeacherProfile(@Valid @RequestBody TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException {
        EditProfileResponse editProfileResponse =  teacherProfileService.editTeacherProfile(teacherProfileRequest,file);
        return new ResponseEntity<>(new ApiResponse<>("Edited successfully",true,editProfileResponse),HttpStatus.OK);
    }

    @GetMapping("/view/teacher/{teacherName}")
    public ResponseEntity<ApiResponse<List<ViewTeacherProfileResponse>>> viewTeacherProfile(@PathVariable("teacherName") String name) {
        return new ResponseEntity<>(new ApiResponse<>("success",true,teacherProfileService.viewTeacherByName(name)), HttpStatus.FOUND);
    }

}
