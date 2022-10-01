package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.service.payload.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolDao schoolDao;

    @Override
    public String saveSchool(List<School> school) {
        schoolDao.saveAllRecord(school);
        return "success";
    }

    @Override
    public int getSchoolCount() {
        return schoolDao.getAllRecords().size();
    }
}
