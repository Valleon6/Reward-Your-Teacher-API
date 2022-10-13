package com.valleon.rewardyourteacherapi.utilities;

import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class PayLoadMapper {

    public RegistrationResponse teacherMapper(Teacher teacher){
        RegistrationResponse teacher1 = new RegistrationResponse();

        if(teacher.getName() != null){
            teacher1.setName(teacher.getName());
        }

        if(teacher.getAppUser().getEmail() != null){
            teacher1.setEmail((teacher.getAppUser().getEmail()));
        }

        if(teacher.getId() != null){
            teacher1.setId(teacher.getId());
        }

        return teacher1;
    }

    public RegistrationResponse studentEntityMapper(Student student){

        RegistrationResponse student1 = new RegistrationResponse();

        if(student.getName() != null){
            student1.setName(student.getName());
        }

        if(student.getAppUser().getEmail() != null){
            student1.setEmail(student.getAppUser().getEmail());
        }
        if(student.getId() != null){
            student1.setId(student.getId());
        }
        return student1;
    }

    public EditProfileResponse studentEditMapper(Student student){
        EditProfileResponse student1 = new EditProfileResponse();

        if(student.getName() != null){
            student1.setName(student.getName());
        }

        if(student.getAppUser().getEmail() != null){
            student1.setEmail(student.getAppUser().getEmail());
        }

        if(student.getId() != null){
            student1.setId(student1.getId());
        }
        return student1;
    }

    public EditProfileResponse TeacherEditMapper(Teacher teacher){
        EditProfileResponse teacher2 = new EditProfileResponse();

        if(teacher.getName() != null){
            teacher2.setName(teacher.getName());
        }
        if(teacher.getAppUser().getEmail() != null){
            teacher2.setEmail(teacher.getAppUser().getEmail());
        }
        if(teacher.getId() != null){
            teacher2.setId(teacher.getId());
        }
        return teacher2;
    }
}
