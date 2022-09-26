package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.RegisterService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping(value = "/student")
    public ResponseEntity<RegistrationResponse> registerStudent(@Valid @RequestBody StudentRegistrationRequest studentRegistrationRequest) {
        return new ResponseEntity<>(registerService.registerStudent(studentRegistrationRequest), HttpStatus.CREATED);
    }


}
