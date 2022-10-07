package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.response.SchoolSearchResponse;

import java.util.Set;

public interface SchoolSearchService {
    Set<SchoolSearchResponse> findAllSchools(int offset, int pageSize);
}
