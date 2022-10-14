package com.valleon.rewardyourteacherapi.usecase.services.impl;

import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.usecase.services.SearchService;
import com.valleon.rewardyourteacherapi.usecase.payload.response.SchoolSearchResponse;
import com.valleon.rewardyourteacherapi.usecase.payload.response.TeacherSearchResponse;
import com.valleon.rewardyourteacherapi.utilities.ResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final SchoolDao schoolDao;
    private final TeacherDao teacherDao;

    private final ResponseMapper responseMapper;

    @Override
    public Set<SchoolSearchResponse> findAllSchools(int offSet, int pageSize) {
        Pageable pageable = PageRequest.of(offSet, pageSize);
        Page<School> schoolList = schoolDao.getAllSchools(pageable);

        Set<SchoolSearchResponse> listOfSchoolName = new HashSet<>();

        schoolList.forEach(school -> {
            SchoolSearchResponse response =new SchoolSearchResponse();
            response.setSchoolName(school.getSchoolName());

            listOfSchoolName.add(response);
        });

        return listOfSchoolName;
    }

    private List<TeacherSearchResponse> getTeacherSearchResponses(Page<Teacher> teachersList) {
        List<TeacherSearchResponse> teacherSearchResponseList = new ArrayList<>();

        teachersList.forEach(teacher -> {
            TeacherSearchResponse response = responseMapper.teacherSearchResponseToTeacherMapper(teacher);
            teacherSearchResponseList.add(response);
        });

        return teacherSearchResponseList;
    }

    @Override
    public List<TeacherSearchResponse> findAllTeachersInASchool(int offset, int pageSize, String school) {
        List<School> schoolEntity = schoolDao.findAllSchools(school).orElseThrow(
                () -> new CustomNotFoundException("School not found"));
        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<Teacher> teachersList = teacherDao.findTeacherBySchool(schoolEntity,pageable);

        return getTeacherSearchResponses(teachersList);
    }

    @Override
    public List<TeacherSearchResponse> getAllTeachers(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);

        Page<Teacher> teachersList = teacherDao.findAllTeachers(pageable);
        return getTeacherSearchResponses(teachersList);
    }

    @Override
    public List<TeacherSearchResponse> searchTeacherByName(String keyword) {
        List<Teacher> teacher = teacherDao.findTeachersByNameIsContainingIgnoreCase(keyword);
        List<TeacherSearchResponse> teacherSearchResponseList = new ArrayList<>();
        teacher.forEach(teacher1 -> {
            TeacherSearchResponse teacherSearchResponse =  responseMapper.teacherSearchResponseToTeacherMapper(teacher1);
            teacherSearchResponseList.add(teacherSearchResponse);
        });
        return teacherSearchResponseList;
    }
}
