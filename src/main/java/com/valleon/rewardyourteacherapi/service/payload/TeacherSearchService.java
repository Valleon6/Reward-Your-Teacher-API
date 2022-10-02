package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.response.TeacherSearchResponse;

import java.util.List;

public interface TeacherSearchService {
    List<TeacherSearchResponse> findAllTeachersInASchool(int offset, int pageSize, String school);

    List<TeacherSearchResponse> findAllTeachers(int offset, int pageSize);

    TeacherSearchResponse searchTeacherByName(String keyword);
}
