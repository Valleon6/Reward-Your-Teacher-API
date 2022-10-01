package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.response.TeacherSearchResponse;

import java.util.List;

public interface TeacherSearchService {
    List<TeacherSearchResponse> findAllTeachersInASchool(int offset, int pageSize, String school);
}
