package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.impl.NotificationServiceImpl;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppreciationController {

    private final NotificationServiceImpl notificationService;


    public ResponseEntity<ApiResponse<NotificationResponse>> teacherAppreciateStudent(@PathVariable (value = "studentId") Long studentId, @PathVariable(value = "teacherId") Long teacherId){
        return ResponseEntity.ok(new ApiResponse<>("Success", true, notificationService.studentAppreciatedNotification(studentId, teacherId)));
    }

}
