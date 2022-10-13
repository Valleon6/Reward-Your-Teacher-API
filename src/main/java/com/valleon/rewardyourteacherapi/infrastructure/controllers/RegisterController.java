package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.google.gson.Gson;
import com.valleon.rewardyourteacherapi.service.payload.RegisterService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping(value = "/student")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerStudent(@Valid @RequestBody StudentRegistrationRequest studentRegistrationRequest)throws Exception {
        RegistrationResponse response = registerService.registerStudent(studentRegistrationRequest);
        return new ResponseEntity<>(new ApiResponse<>("Registered successfully", true, response), HttpStatus.CREATED);
    }

    @PostMapping(value = "/teacher")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registerTeacher(@Valid @RequestParam("registration") String registration, @RequestParam("file") MultipartFile file) throws Exception {
        TeacherRegistrationRequest teacherRegistrationRequest = new Gson().fromJson(registration,TeacherRegistrationRequest.class);
        RegistrationResponse response =  registerService.registerTeacher( teacherRegistrationRequest,file);
        return new ResponseEntity<>(new ApiResponse<>("Registered Successfully",true,response), HttpStatus.CREATED);
    }

    @GetMapping(value = "/verification")
    public ResponseEntity<ApiResponse<RegistrationResponse>> verifyUser(@Valid @RequestParam("token") String token) throws Exception {
        registerService.verifyUser(token);
        return new ResponseEntity<>(new ApiResponse<>("Registered Successfully",true), HttpStatus.CREATED);
    }

}
