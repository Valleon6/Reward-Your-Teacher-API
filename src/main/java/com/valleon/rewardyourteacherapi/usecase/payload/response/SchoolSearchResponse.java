package com.valleon.rewardyourteacherapi.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolSearchResponse {
    private long id;
    private String schoolName;
}
