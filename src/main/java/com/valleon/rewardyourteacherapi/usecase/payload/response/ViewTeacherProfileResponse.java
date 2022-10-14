package com.valleon.rewardyourteacherapi.usecase.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewTeacherProfileResponse {
    private String name;
    private String email;
    private String schoolName;
    private String profilePicture;
    private String phoneNumber;
    private String about;
    private String position;
}
