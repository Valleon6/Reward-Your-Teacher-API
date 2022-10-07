package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolDaoImpl extends CrudDaoImpl<School, Long> implements SchoolDao{
    private final SchoolRepository schoolRepository;

    protected SchoolDaoImpl(SchoolRepository schoolRepository){
        super(schoolRepository);
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Optional<School> findSchool(String schoolName) {
        return schoolRepository.findSchoolBySchoolName(schoolName);
    }

    @Override
    public Optional<List<School>> findAllSchools(String school) {
        return schoolRepository.findBySchoolNameContainingIgnoreCase(school);
    }

    @Override
    public Page<School> getAllSchools(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

}
