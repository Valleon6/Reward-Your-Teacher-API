package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

//    Optional<Notification> findNotificationByCreatedAt(LocalDateTime localDateTime);

    List<Notification> findNotificationByStudent(Student student);

    List<Notification> findNotificationByTeacher(Teacher teacher);

}
