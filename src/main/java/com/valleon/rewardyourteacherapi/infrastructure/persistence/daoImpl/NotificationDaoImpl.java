package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.NotificationDao;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.message.Notification;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationDaoImpl extends CrudDaoImpl<Notification, Long> implements NotificationDao {

    private final NotificationRepository notificationRepository;

    protected NotificationDaoImpl(NotificationRepository notificationRepository) {
        super(notificationRepository);
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> findNotificationByStudentOrderByCreatedAtDesc(Student student) {
        return notificationRepository.findNotificationByStudent(student);
    }

    @Override
    public List<Notification> findNotificationByTeacherOrderByCreatedAtDesc(Teacher teacher) {
        return notificationRepository.findNotificationByTeacher(teacher);
    }

    @Override
    public Notification findNotificationByTeacherAndStudentOrderByCreatedAtDesc(Teacher teacher, Student student) {
        return notificationRepository.findNotificationByTeacherAndStudentOrderByCreatedAtDesc(teacher, student);
    }

    @Override
    public Notification findNotificationByTransactionOrderByCreatedAtDesc(Transaction transaction) {
        return notificationRepository.findNotificationByTransactionOrderByCreatedAtDesc(transaction);
    }
}
