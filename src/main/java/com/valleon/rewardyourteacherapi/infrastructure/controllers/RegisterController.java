package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.RegisterService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping(value = "/student")
    public ResponseEntity<RegistrationResponse> registerStudent(@Valid @RequestBody StudentRegistrationRequest studentRegistrationRequest) {
        return new ResponseEntity<>(registerService.registerStudent(studentRegistrationRequest), HttpStatus.CREATED);
    }

    @PostMapping(value = "/teacher")
    public ResponseEntity<RegistrationResponse> registerTeacher(@Valid TeacherRegistrationRequest teacherRegistrationRequest, @RequestPart MultipartFile file) throws IOException {
        return new ResponseEntity<>(registerService.registerTeacher(teacherRegistrationRequest, file), HttpStatus.CREATED);
    }

}
