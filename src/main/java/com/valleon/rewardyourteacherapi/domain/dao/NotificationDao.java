package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationDao extends CrudDAO<Notification,Long>{
    List<Notification> findNotificationByStudent(Student student);

    List<Notification> findNotificationByTeacher(Teacher teacher);
}
