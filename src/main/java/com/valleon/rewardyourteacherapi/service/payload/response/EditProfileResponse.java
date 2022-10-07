package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EditProfileResponse {
    private String message;
    private LocalDateTime timeStamp;
}
