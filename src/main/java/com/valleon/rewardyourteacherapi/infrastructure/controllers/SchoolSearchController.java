package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.SchoolSearchService;
import com.valleon.rewardyourteacherapi.service.payload.response.SchoolSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/search/all")
public class SchoolSearchController {

    private SchoolSearchService schoolSearchService;

    @GetMapping("schools/")
    private ResponseEntity<Set<SchoolSearchResponse>>getAllSchools(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize){
     return new ResponseEntity<>(schoolSearchService.findAllSchools(offset,pageSize), HttpStatus.FOUND);
    }
}
