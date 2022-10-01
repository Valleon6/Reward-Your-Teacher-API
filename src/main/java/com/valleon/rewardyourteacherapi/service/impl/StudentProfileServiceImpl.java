package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.service.payload.StudentProfileService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentDao studentDao;

    private final SchoolDao schoolDao;

    private final ApiResponse apiResponse;

    private final AppUserDao appUserDao;

    @Override
    public EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest) {
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String email = ((UserDetails)principal).getUsername();

       School school = schoolDao.findSchool(studentProfileRequest.getNameOfSchool())
               .orElseThrow(()-> new CustomNotFoundException("School not found."));

        AppUser student = appUserDao.findAppUserByEmail(email)
                .orElseThrow(()-> new CustomNotFoundException("User not Found"));

        student.setEmail(studentProfileRequest.getEmail());

        AppUser appUser = appUserDao.saveRecord(student);

        Student student1 = studentDao.getStudentByAppUser(appUser);

        student1.setSchool(school);

        student1.setName(studentProfileRequest.getName());

        student1.setPhoneNumber(studentProfileRequest.getPhoneNumber());

        studentDao.saveRecord(student1);

        return apiResponse.edited("Edited Successfully", LocalDateTime.now());
    }
}
