package com.valleon.rewardyourteacherapi.service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ViewTeacherProfileResponse {
    private String name;
    private String email;
    private String schoolName;
    private String profilePicture;
    private String phoneNumber;
    private String about;
    private String position;
}
