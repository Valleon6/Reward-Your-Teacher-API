package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.TeacherSearchService;
import com.valleon.rewardyourteacherapi.service.payload.response.TeacherSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search/teachers")
@AllArgsConstructor
public class TeacherSearchController {

    public final TeacherSearchService teacherSearchService;

    @GetMapping("/school")
    public ResponseEntity<List<TeacherSearchResponse>> findAllTeachersInASchool(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("school") String schoolName
            ){
        return new ResponseEntity<>(teacherSearchService.findAllTeachersInASchool(offset, pageSize, schoolName), HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<TeacherSearchResponse>> getAllTeachers(
            @RequestParam("offset") int offset,
            @RequestParam ("pageSize") int pageSize){
        return new ResponseEntity<>(teacherSearchService.findAllTeachers(offset, pageSize), HttpStatus.FOUND);
    }

    @GetMapping("teacher")
    public ResponseEntity<TeacherSearchResponse> findTeacher(@RequestParam String nameKeyword){
        return new ResponseEntity<>(teacherSearchService.searchTeacherByName(nameKeyword), HttpStatus.FOUND);

    }
}
