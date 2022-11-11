package com.valleon.rewardyourteacherapi.usecase.services.impl;

import com.valleon.rewardyourteacherapi.domain.dao.*;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.NotificationType;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.infrastructure.configuration.security.UserDetails;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.usecase.payload.request.TransactionRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.NotificationResponse;
import com.valleon.rewardyourteacherapi.usecase.services.NotificationService;
import com.valleon.rewardyourteacherapi.utilities.LocalDateTimeConverter;
import com.valleon.rewardyourteacherapi.utilities.ScheduledTasks;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationDao notificationDao;
    private StudentDao studentDao;
    private TeacherDao teacherDao;

    private ScheduledTasks scheduledTasks;
    private AppUserDao appUserDao;

    private TransactionDao transactionDao;


    @Override
    public Notification studentSendMoneyNotification(TransactionRequest transactionRequest) {
        Long teacherId = transactionRequest.getTeacherId();
        Teacher teacher = teacherDao.findById(teacherId).orElse(null);
        Notification notification = new Notification();

        try {
            if (teacher == null) {
                throw new CustomNotFoundException("Id " + teacherId + " is not valid");
            }

            String message = "Successfully transferred " + transactionRequest.getAmount() + " to " + teacher.getName();
            notification.setCreatedAt(LocalDateTime.now());
            notification.setMessage(message);
            notification.setTeacher(teacher);
            notificationDao.saveRecord(notification);
            return notification;
        } catch (CustomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        notification.setMessage("Transaction failed!!!. Invalid user.");
        notification.setCreatedAt(LocalDateTime.now());
        return notification;
    }

    @Override
    public Notification walletFundingNotification(TransactionRequest transactionRequest) {
        Long studentId = transactionRequest.getStudentId();
        Student student = studentDao.findById(studentId).orElse(null);

        if (student == null) {
            throw new CustomNotFoundException("Student with Id " + studentId + " is not valid");
        }

        Notification notification = new Notification();
        String message = "You have successfully funded your wallet with " + transactionRequest.getAmount();
        notification.setCreatedAt(transactionRequest.getCreatedAt());
        notification.setMessage(message);
        notification.setStudent(student);
        return notificationDao.saveRecord(notification);
    }


    @Override
    public Notification teacherReceivedNotification(TransactionRequest transactionRequest) {
        Notification notification = new Notification();
        Long teacherId = transactionRequest.getTeacherId();
        Teacher teacher = teacherDao.findById(teacherId).orElse(null);
        if (teacher == null) {
            throw new CustomNotFoundException(" Id " + teacherId + " is not valid");
        }
        String message = "You received " + transactionRequest.getAmount();
        notification.setCreatedAt(LocalDateTime.now());
        notification.setMessage(message);
        notification.setTeacher(teacher);
        return notificationDao.saveRecord(notification);
    }

    @Override
    public List<NotificationResponse> allNotificationsOfA_StudentById() {
        String email = UserDetails.getLoggedInUserDetails();
        AppUser user = appUserDao.findAppUserByEmail(email);
        Student student = studentDao.getStudentByAppUser(user);
        List<Notification> notification = notificationDao.findNotificationByStudentOrderByCreatedAtDesc(student);

        if (notification.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notification.stream()
                .map(n -> new NotificationResponse(n.getMessage(), LocalDateTimeConverter.localDateTimeConverter(n.getCreatedAt())))
                .toList();
    }

    @Override
    public List<NotificationResponse> allNotificationsOfA_TeacherById() {
        String email = UserDetails.getLoggedInUserDetails();
        AppUser appUser = appUserDao.findAppUserByEmail(email);
        Teacher teacher = teacherDao.getTeacherByAppUser(appUser);
        List<Notification> notification = notificationDao.findNotificationByTeacherOrderByCreatedAtDesc(teacher);
        if (notification.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notification.stream()
                .map(n -> new NotificationResponse(n.getMessage(), LocalDateTimeConverter.localDateTimeConverter(n.getCreatedAt())))
                .toList();
    }

    @Override
    public NotificationResponse studentAppreciatedNotification(Long transactionId) {
        Transaction transaction = transactionDao.getRecordById(transactionId);
        Notification notificationEntity = notificationDao.findNotificationByTransactionOrderByCreatedAtDesc(transaction);

        if (notificationEntity.isAppreciated()){
            throw new CustomNotFoundException("You have already appreciated this student");
        }
        notificationEntity.setAppreciated(true);
        String grinningFace = "\uD83D\uDC4D";
        Notification notification =new Notification();
        notification.setStudent(transaction.getStudent());
        notification.setNotificationType(NotificationType.APPRECIATION_NOTIFICATION);
        notification.setMessage(notification.getTeacher().getName()+" Appreciated you "+grinningFace);
        notificationDao.saveRecord(notification);
        notificationDao.saveRecord(notification);
        return new NotificationResponse("success",LocalDateTimeConverter.localDateTimeConverter(notification.getCreatedAt()));
    }
}