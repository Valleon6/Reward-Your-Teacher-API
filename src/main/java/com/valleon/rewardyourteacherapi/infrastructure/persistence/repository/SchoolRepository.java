package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findSchoolBySchoolName(String schoolName);

    Optional<List<School>> findBySchoolNameContainingIgnoreCase(String school);
}
