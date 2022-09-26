package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherDao extends CrudDAO<TeacherDao, Long>{

    Page<TeacherDao> findTeacherBySchool(List<School> school, Pageable pageable);

    Page<TeacherDao> findAllTeachers(Pageable pageable);

    TeacherDao searchTeacherByName(String name);

    TeacherDao getTeacherByAppUser(AppUser appUser);
}
