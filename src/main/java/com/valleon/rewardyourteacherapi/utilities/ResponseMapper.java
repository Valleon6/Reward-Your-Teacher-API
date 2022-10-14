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
        return teacherSearchResponse;
    }

}
