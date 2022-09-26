package com.valleon.rewardyourteacherapi.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher getTeacherByAppUser(AppUser appUser);

    Page<Teacher> findAll(Pageable pageable);

    Page<Teacher> findTeacherBySchoolIn(List<School> school, Pageable pageable);

    //    TeacherEntity getTeacherEntityByAppUserEntity(AppUserEntity appUserEntity);

//    TeacherEntity getTeacherEntityByAppUserEntity;

}
