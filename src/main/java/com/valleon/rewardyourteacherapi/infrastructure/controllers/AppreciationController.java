package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.usecase.services.impl.NotificationServiceImpl;
import com.valleon.rewardyourteacherapi.usecase.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AppreciationController {

    private final NotificationServiceImpl notificationService;

    @PostMapping("appreciate-student/{transactionId}")
    public ResponseEntity<ApiResponse<NotificationResponse>> teacherAppreciateStudent(@PathVariable ( value= "transactionId")Long transactionId){
        return ResponseEntity.ok(new ApiResponse<>("Success",true,notificationService.studentAppreciatedNotification(transactionId)));
    }

}
