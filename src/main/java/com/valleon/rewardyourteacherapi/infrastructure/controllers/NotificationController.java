package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.usecase.services.NotificationService;
import com.valleon.rewardyourteacherapi.usecase.payload.request.NotificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;


    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<NotificationRequest>> retrieveStudentNotification(@PathVariable("studentId") Long studentId){
        return  new ResponseEntity<>(notificationService.allNotificationsOfA_StudentById(studentId), HttpStatus.FOUND);
    }

    @GetMapping("/teacher/{teacherId")
    public ResponseEntity<List<NotificationRequest>> retrieveTeacherNotification(@PathVariable("teacherId") Long teacherId){
        return new ResponseEntity<>(notificationService.allNotificationsOfA_TeacherById(teacherId), HttpStatus.FOUND);
    }
}
