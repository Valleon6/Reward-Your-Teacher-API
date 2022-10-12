package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TeacherDao extends CrudDAO<Teacher, Long>{

    Page<Teacher> findTeacherBySchool(List<School> school, Pageable pageable);

    Page<Teacher> findAllTeachers(Pageable pageable);

    Teacher getTeacherByAppUser(AppUser appUser);

    Optional<Teacher> findTeacherByPhoneNumber(String phoneNumber);

    List<Teacher> findTeacherByNameIsContainingIgnoreCase(String name);

    Optional<Teacher> findTeacherByNin(String nin);

    Optional<List<Teacher>> findTeacherByName(String name);

    Teacher getTeacherByNameAndPhoneNumber(String name, String phoneNumber);


}
