package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.service.payload.response.ViewTeacherProfileResponse;

public interface ViewTeacherProfileService {
    ViewTeacherProfileResponse viewTeacherByName (String name);
}
