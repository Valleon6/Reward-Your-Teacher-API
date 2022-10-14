package com.valleon.rewardyourteacherapi.usecase.payload.request;

import com.valleon.rewardyourteacherapi.domain.entities.enums.NotificationType;

public record NotificationRequest(String notificationBody, NotificationType notificationType) {

}
