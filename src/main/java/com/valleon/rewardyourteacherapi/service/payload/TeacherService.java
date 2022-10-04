package com.valleon.rewardyourteacherapi.service.payload;

import com.valleon.rewardyourteacherapi.domain.entities.Teacher;


public interface TeacherService {

    Teacher searchTeacherByName(String name);
}
