package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.NotificationDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.service.payload.NotificationService;
import com.valleon.rewardyourteacherapi.service.payload.request.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationDao notificationDao;
    private StudentDao studentDao;
    private TeacherDao teacherDao;


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
    public List<Notification> allNotificationsOfA_StudentById(Long studentId) {
        Student student = studentDao.findById(studentId).orElseThrow(null);
        Optional<List<Notification>> notificationEntity = notificationDao.findNotificationByStudent(student);
        if (notificationEntity.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notificationEntity.get();
    }

    @Override
    public List<Notification> allNotificationsOfA_TeacherById(Long teacherId) {
        Teacher teacher = teacherDao.findById(teacherId).orElseThrow(null);
        Optional<List<Notification>> notificationEntity = notificationDao.findNotificationByTeacher(teacher);
        if (notificationEntity.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notificationEntity.get();
    }
}