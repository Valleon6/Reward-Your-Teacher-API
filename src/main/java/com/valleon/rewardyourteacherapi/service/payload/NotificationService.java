package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.service.payload.request.TransactionRequest;

import java.util.List;

public interface NotificationService {

    Notification studentSendMoneyNotification(TransactionRequest transactionRequest);

    Notification walletFundingNotification (TransactionRequest transactionRequest);

    Notification teacherReceivedNotification (TransactionRequest transactionRequest);

    List<Notification> allNotificationsOfA_StudentById(Long studentId);

    List<Notification> allNotificationsOfA_TeacherById(Long teacherId);

}
