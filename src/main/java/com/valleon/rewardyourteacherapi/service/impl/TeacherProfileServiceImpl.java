package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.service.payload.TeacherProfileService;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;
import com.valleon.rewardyourteacherapi.utilities.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

private final TeacherDao teacherDao;

private final SchoolDao schoolDao;

private final PasswordEncoder passwordEncoder;

private final CloudinaryService cloudinaryService;

private final AppUserDao appUserDao;

private final ApiResponse apiResponse;

    @Override
    public EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = ((UserDetails)principal).getUsername();

        School school = schoolDao.findSchool(teacherProfileRequest.getSchoolTaught())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));

        AppUser appUser = appUserDao.findAppUserByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException("Teacher not Registered"));

        appUser.setEmail(teacherProfileRequest.getEmail());

        appUser.setPassword(passwordEncoder.encode(teacherProfileRequest.getPassword()));

        String url = cloudinaryService.uploadImage(file);

        Teacher teacher = teacherDao.getTeacherByAppUser(appUser);

        teacher.setName(teacherProfileRequest.getName());

        teacher.setSchool(school);

        teacher.setYearsOfTeaching(teacherProfileRequest.getYearsOfTeaching());

        teacher.setNin(url);

        teacherDao.saveRecord(teacher);

        return apiResponse.edited("Edited Successfully", LocalDateTime.now());
    }
}
