package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;

import java.util.List;

public interface NotificationDao extends CrudDAO<Notification,Long>{
    List<Notification> findNotificationByStudentOrderByCreatedAtDesc(Student student);

    List<Notification> findNotificationByTeacherOrderByCreatedAtDesc(Teacher teacher);

    Notification findNotificationByTeacherAndStudentOrderByCreatedAtDesc(Teacher teacher, Student student);

    Notification findNotificationByTransactionOrderByCreatedAtDesc(Transaction transaction);

}
