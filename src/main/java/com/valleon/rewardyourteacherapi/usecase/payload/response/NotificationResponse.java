package com.valleon.rewardyourteacherapi.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationResponse {
    private String message;
    private String date;
}
