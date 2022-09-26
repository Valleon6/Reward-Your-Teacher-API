package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentDaoImpl extends CrudDaoImpl<Student, Long> implements StudentDao {

    private final StudentRepository studentRepository;

    protected StudentDaoImpl(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentByAppUser(AppUser appUser) {
        return studentRepository.getStudentByAppUser(appUser);
    }
}
