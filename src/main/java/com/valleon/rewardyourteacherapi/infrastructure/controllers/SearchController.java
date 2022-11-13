package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.usecase.services.SearchService;
import com.valleon.rewardyourteacherapi.usecase.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.SchoolSearchResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.TeacherSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/search")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    private final SearchService service;

    @GetMapping("/schools")
    public ResponseEntity<ApiResponse<Set<SchoolSearchResponse>>> getAllSchools(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity(new ApiResponse<>("success",true,service.findAllSchools(offset, pageSize)), HttpStatus.OK);
    }
    @GetMapping("/teachers/{schoolName}")
    public ResponseEntity<ApiResponse<List<TeacherSearchResponse>>> findAllTeacherInASchool(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize,
            @PathVariable("schoolName") String schoolName
    ) {
        return new ResponseEntity<>(new ApiResponse<>("success",true,service.findAllTeachersInASchool(offset, pageSize, schoolName)), HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<ApiResponse<List<TeacherSearchResponse>>> getAllTeachers(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<>(new ApiResponse<>("success",true,service.getAllTeachers(offset, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/teacher/{teacherName}")
    public ResponseEntity<ApiResponse<List<TeacherSearchResponse>>> findTeacher(@PathVariable("teacherName") String keyword) {
        return new ResponseEntity<>(new ApiResponse<>("success",true,service.searchTeacherByName(keyword)), HttpStatus.OK);
    }
}
