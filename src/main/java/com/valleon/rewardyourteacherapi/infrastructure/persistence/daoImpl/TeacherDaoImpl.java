package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherDaoImpl extends CrudDaoImpl<Teacher, Long> implements TeacherDao {

    private final TeacherRepository teacherRepository;


    protected TeacherDaoImpl(TeacherRepository teacherRepository) {
        super(teacherRepository);
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Page<Teacher> findTeacherBySchool(List<School> school, Pageable pageable) {
        return teacherRepository.findTeacherBySchoolIn(school, pageable);
    }

    @Override
    public Page<Teacher> findAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Optional<List<Teacher>> findTeacherByName(String name) {
        return teacherRepository.findTeacherByNameContainingIgnoreCase(name);
    }

    @Override
    public Teacher getTeacherByAppUser(AppUser appUser) {
        return teacherRepository.getTeacherByAppUser(appUser);
    }

}
