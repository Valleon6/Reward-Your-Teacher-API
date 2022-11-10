package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findNotificationByStudent(Student student);

    List<Notification> findNotificationByTeacher(Teacher teacher);

    Notification findNotificationByTeacherAndStudentOrderByCreatedAtDesc(Teacher teacher, Student student);

    Notification findNotificationByTransactionOrderByCreatedAtDesc(Transaction transaction);
}
