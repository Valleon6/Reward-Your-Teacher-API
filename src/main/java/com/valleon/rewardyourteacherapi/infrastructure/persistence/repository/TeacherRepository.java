package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Page<Teacher> findTeacherBySchoolIn(List<School> school, Pageable pageable);

    Page<Teacher> findAll(Pageable pageable);

    Teacher getTeacherByAppUser(AppUser appUser);

    Teacher findTeacherByNameContainingIgnoreCase(String name);

}
