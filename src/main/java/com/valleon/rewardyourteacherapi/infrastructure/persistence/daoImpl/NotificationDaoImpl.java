package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.NotificationDao;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationDaoImpl extends CrudDaoImpl<Notification, Long> implements NotificationDao {

    private final NotificationRepository notificationRepository;

    protected NotificationDaoImpl(NotificationRepository notificationRepository) {
        super(notificationRepository);
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> findNotificationByStudent(Student student) {
        return notificationRepository.findNotificationByStudent(student);
    }

    @Override
    public List<Notification> findNotificationByTeacher(Teacher teacher) {
        return notificationRepository.findNotificationByTeacher(teacher);
    }
}
