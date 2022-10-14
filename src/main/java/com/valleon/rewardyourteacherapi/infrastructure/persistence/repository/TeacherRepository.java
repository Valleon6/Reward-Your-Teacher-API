package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Page<Teacher> findTeacherBySchoolIn(List<School> school, Pageable pageable);

    Page<Teacher> findAll(Pageable pageable);

    Optional<List<Teacher>> findTeachersByNameContainingIgnoreCase(String name);

    Teacher getTeacherByAppUser(AppUser appUser);

    Optional<Teacher> findTeacherByPhoneNumber(String phoneNumber);

    Optional<Teacher> findTeacherByNin(String nin);
    Optional<List<Teacher>> findTeachersByName(String name);
    Teacher getTeachersByNameContainingIgnoreCaseAndPhoneNumber(String name, String phoneNumber);

}
