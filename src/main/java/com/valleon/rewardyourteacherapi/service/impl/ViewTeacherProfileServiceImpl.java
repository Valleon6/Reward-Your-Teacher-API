package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.service.payload.ViewTeacherProfileService;
import com.valleon.rewardyourteacherapi.service.payload.response.ViewTeacherProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ViewTeacherProfileServiceImpl implements ViewTeacherProfileService {

    private final TeacherDao teacherDao;

    @Override
    public ViewTeacherProfileResponse viewTeacherByName(String name) {

        Teacher teacher = teacherDao.searchTeacherByName(name);

        return ViewTeacherProfileResponse.builder()
                .name(teacher.getName())
                .schoolName(teacher.getSchool().getSchoolName())
                .email(teacher.getAppUser().getEmail())
                .profilePicture(teacher.getDisplayPicture())
                .phoneNumber(teacher.getPhoneNumber())
                .about(teacher.getAbout())
                .position(teacher.getAbout())
                .build();
    }
}
