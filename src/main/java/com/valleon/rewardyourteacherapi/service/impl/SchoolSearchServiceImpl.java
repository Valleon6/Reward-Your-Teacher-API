package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.service.payload.SchoolSearchService;
import com.valleon.rewardyourteacherapi.service.payload.response.SchoolSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class SchoolSearchServiceImpl implements SchoolSearchService {
    private final SchoolDao schoolDao;

    @Override
    public Set<SchoolSearchResponse> findAllSchools(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        Page<School> schoolList = schoolDao.getAllSchools(pageable);

        Set<SchoolSearchResponse> listOfSchoolName = new HashSet<>();

        schoolList.forEach(school -> {
            SchoolSearchResponse response = new SchoolSearchResponse();
            response.setSchoolName(school.getSchoolName());
            listOfSchoolName.add(response);
        });
        return listOfSchoolName;
    }
}
