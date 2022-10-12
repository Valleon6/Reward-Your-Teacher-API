package com.valleon.rewardyourteacherapi.service.payload.request;

import com.valleon.rewardyourteacherapi.domain.entities.enums.NotificationType;

public record NotificationRequest(String notificationBody, NotificationType notificationType) {

}
