package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.service.payload.TeacherSearchService;

import com.valleon.rewardyourteacherapi.service.payload.response.TeacherSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TeacherSearchServiceImpl implements TeacherSearchService {

    private final TeacherDao teacherDao;

    private final SchoolDao schoolDao;

    private List<TeacherSearchResponse> getTeacherSearchResponses(Page<Teacher> teachersList){
        List<TeacherSearchResponse> teacherSearchResponseList = new ArrayList<>();

        teachersList.forEach(teacher -> {
            TeacherSearchResponse response = new TeacherSearchResponse();
            response.setName(teacher.getName());
            response.setSchool(teacher.getSchool().getSchoolName());
            response.setYearsOfTeaching(teacher.getYearsOfTeaching());

            teacherSearchResponseList.add(response);
        });

        return teacherSearchResponseList;
    }


    @Override
    public List<TeacherSearchResponse> findAllTeachersInASchool(int offset, int pageSize, String school) {
       List<School> schools = schoolDao.findAllSchools(school)
               .orElseThrow(() -> new CustomNotFoundException("School not found"));

        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<Teacher> teachersList = teacherDao.findTeacherBySchool(schools,pageable);

        return getTeacherSearchResponses(teachersList);
    }

    @Override
    public List<TeacherSearchResponse> findAllTeachers(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<Teacher> teachersList = teacherDao.findAllTeachers(pageable);

        return getTeacherSearchResponses(teachersList);
    }

    @Override
    public TeacherSearchResponse searchTeacherByName(String keyword) {
        Teacher teacher = teacherDao.searchTeacherByName(keyword);

        TeacherSearchResponse response = TeacherSearchResponse.builder()
                .name(teacher.getName())
                .school(teacher.getSchool().getSchoolName())
                .yearsOfTeaching(teacher.getYearsOfTeaching()).build();

        return response;
    }
}
