package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.ViewTeacherProfileService;
import com.valleon.rewardyourteacherapi.service.payload.response.ViewTeacherProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/view/Profile")
public class ViewTeacherProfileController {

    private final ViewTeacherProfileService viewTeacherProfileService;

    @GetMapping("/teacher")
    public ResponseEntity<ViewTeacherProfileResponse> viewTeacherProfile(@RequestParam("name") String name){
        return new ResponseEntity<>(viewTeacherProfileService.viewTeacherByName(name), HttpStatus.FOUND);
    }
}
