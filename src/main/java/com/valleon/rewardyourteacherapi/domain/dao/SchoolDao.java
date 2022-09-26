package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface SchoolDao extends CrudDAO<School, Long>{

    Optional<School> findSchool(String schoolName);

    Optional<List<School>> findAllSchools(String school);

    Page<School> getAllSchools(Pageable pageable);
}
