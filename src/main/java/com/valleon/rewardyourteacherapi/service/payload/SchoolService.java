package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.domain.entities.School;

import java.util.List;

public interface SchoolService {

    String saveSchool(List<School> school);

    int getSchoolCount();
}
