package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.NotificationDao;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

public class NotificationDaoImpl extends CrudDaoImpl<Notification, Long> implements NotificationDao {

    private final NotificationRepository notificationRepository;

    public NotificationDaoImpl(NotificationRepository notificationRepository) {
        super(notificationRepository);
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<List<Notification>> findNotificationByStudent(Student student) {
        return notificationRepository.findNotificationByStudent(student);
    }

    @Override
    public Optional<List<Notification>> findNotificationByTeacher(Teacher teacher) {
        return notificationRepository.findNotificationByTeacher(teacher);
    }
}
