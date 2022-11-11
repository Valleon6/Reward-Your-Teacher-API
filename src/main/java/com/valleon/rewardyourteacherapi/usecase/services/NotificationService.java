package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.usecase.payload.request.TransactionRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    Notification studentSendMoneyNotification(TransactionRequest transactionRequest);

    Notification walletFundingNotification (TransactionRequest transactionRequest);

    Notification teacherReceivedNotification (TransactionRequest transactionRequest);

    List<NotificationResponse> allNotificationsOfA_StudentById();

    List<NotificationResponse> allNotificationsOfA_TeacherById();

    NotificationResponse studentAppreciatedNotification(Long transactionId);

}
