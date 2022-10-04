package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.service.payload.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/search/teacher")
public class TeacherSearchController {

    private TeacherService teacherService;

    @GetMapping("/findByName")
    public ResponseEntity<Teacher> searchTeacherByName(String name) {
        return new ResponseEntity<>(teacherService.searchTeacherByName(name), HttpStatus.OK);
    }

}
