package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;

public interface StudentDao extends CrudDAO<Student, Long>{

    Student getStudentByAppUser(AppUser appUser);
}
