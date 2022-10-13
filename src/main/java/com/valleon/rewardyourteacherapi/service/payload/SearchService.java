package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.response.SchoolSearchResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.TeacherSearchResponse;

import java.util.List;
import java.util.Set;

public interface SearchService {

    Set<SchoolSearchResponse> findAllSchools(int offSet, int pageSize);
    List<TeacherSearchResponse> findAllTeachersInASchool(int offset, int pageSize, String school);

    List<TeacherSearchResponse> getAllTeachers(int offset, int pageSize);

    List<TeacherSearchResponse> searchTeacherByName(String keyword);

}
