package com.valleon.rewardyourteacherapi.utilities;

import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.service.payload.response.ViewTeacherProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class RequestMapper {


    public ViewTeacherProfileResponse viewTeacherProfileResponseToTeacherMapper(Teacher teacher){
        ViewTeacherProfileResponse viewTeacherProfileResponse = new ViewTeacherProfileResponse();
        if(teacher.getName() != null){
            viewTeacherProfileResponse.setName(teacher.getName());
        }
        if(teacher.getAppUser().getEmail() != null){
            viewTeacherProfileResponse.setEmail(teacher.getAppUser().getEmail());
        }
        if(teacher.getSchool().getSchoolName() != null){
            viewTeacherProfileResponse.setSchoolName(teacher.getSchool().getSchoolName());
        }
        if(teacher.getDisplayPicture() != null){
            viewTeacherProfileResponse.setProfilePicture(teacher.getDisplayPicture());
        }
        if(teacher.getPhoneNumber() != null) {
            viewTeacherProfileResponse.setPhoneNumber(teacher.getPhoneNumber());
        }
        if(teacher.getAbout() != null){
            viewTeacherProfileResponse.setAbout(teacher.getAbout());
        }
        if(teacher.getPosition() != null) {
            viewTeacherProfileResponse.setPosition(teacher.getPosition().toString());
        }
        return viewTeacherProfileResponse;

    }

}
