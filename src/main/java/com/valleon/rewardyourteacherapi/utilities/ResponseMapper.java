package com.valleon.rewardyourteacherapi.utilities;

import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.usecase.payload.response.TeacherSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class ResponseMapper {

    public TeacherSearchResponse teacherSearchResponseToTeacherMapper(Teacher teacher){
        TeacherSearchResponse teacherSearchResponse = new TeacherSearchResponse();
        if(teacher.getName() != null){
            teacherSearchResponse.setName(teacher.getName());
        }

        if(teacher.getSchool() != null){
            teacherSearchResponse.setSchool(teacher.getSchool().getSchoolName());
        }

        if(teacher.getYearsOfTeaching() != null){
            teacherSearchResponse.setYearsOfTeaching(teacher.getYearsOfTeaching());
        }
        if(teacher.getPosition() != null){
            teacherSearchResponse.setPosition(teacher.getPosition().toString());
        }
        if(teacher.getAppUser().getEmail() != null){
            teacherSearchResponse.setEmail(teacher.getAppUser().getEmail());
        }
        if(teacher.getPhoneNumber() != null){
            teacherSearchResponse.setPhoneNumber(teacher.getPhoneNumber());
        }
        if(teacher.getAbout() != null){
            teacherSearchResponse.setAbout(teacher.getAbout());
        }
        if(teacher.getDisplayPicture() != null){
            teacherSearchResponse.setDisplayPicture(teacher.getDisplayPicture());
        }
        return teacherSearchResponse;
    }

}
