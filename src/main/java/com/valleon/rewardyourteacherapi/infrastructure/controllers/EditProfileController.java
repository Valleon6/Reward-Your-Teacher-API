package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.StudentProfileService;
import com.valleon.rewardyourteacherapi.service.payload.TeacherProfileService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/edit")
public class EditProfileController {
    private final StudentProfileService studentProfileService;
    private final TeacherProfileService teacherProfileService;

    @PostMapping("/student")
    public ResponseEntity<EditProfileResponse> editStudentProfile(@Valid @RequestBody StudentProfileRequest studentProfileRequest){
        return ResponseEntity.ok(studentProfileService.editStudentProfile(studentProfileRequest));
    }

    @PostMapping("/teacher")
    public ResponseEntity<EditProfileResponse> editTeacherProfile(@Valid @RequestBody TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException {
        return ResponseEntity.ok(teacherProfileService.editTeacherProfile(teacherProfileRequest, file));
    }

}
