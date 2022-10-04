package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Position;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Status;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.EntityAlreadyExistException;
import com.valleon.rewardyourteacherapi.service.payload.RegisterService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import com.valleon.rewardyourteacherapi.utilities.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final StudentDao studentDao;
    private final SchoolDao schoolDao;

    private final ApiResponse registrationResponse;

    private final AppUserDao appUserDao;

    private final PasswordEncoder passwordEncoder;

    private final CloudinaryService cloudinaryService;

    private final TeacherDao teacherDao;



    @Override
    public RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) {
        Optional<AppUser> appUser = appUserDao.findAppUserByEmail(studentRegistrationRequest.getEmail());

        if (appUser.isPresent()) {
            throw new EntityAlreadyExistException("Email has already been registered");
        }

        School school = schoolDao.findSchool(studentRegistrationRequest.getSchoolName())
                .orElseThrow(() -> new CustomNotFoundException("School not found."));

        AppUser appUser1 = AppUser.builder()
                .email(studentRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(studentRegistrationRequest.getPassword()))
                .role(Role.STUDENT)
                .build();

        AppUser appUser2 = appUserDao.saveRecord(appUser1);
        Student student = Student.builder()
                .name(studentRegistrationRequest.getName())
                .phoneNumber(studentRegistrationRequest.getPhoneNumber())
                .school(school)
                .appUser(appUser2)
                .build();


        return registrationResponse.created("Success", LocalDateTime.now(), studentDao.saveRecord(student));
    }



    @Override
    public RegistrationResponse registerTeacher(TeacherRegistrationRequest teacherRegistrationRequest, MultipartFile file) throws IOException {
        Optional<AppUser> appUser = appUserDao.findAppUserByEmail(teacherRegistrationRequest.getEmail());

        if(appUser.isPresent()){
            throw new EntityAlreadyExistException("Email already registered");
        }

        School school = schoolDao.findSchool(teacherRegistrationRequest.getSchool())
                .orElseThrow(() -> new CustomNotFoundException("Selected school not found"));

        String fileUrl = cloudinaryService.uploadImage(file);

        AppUser appUser1 = AppUser.builder()
                .email(teacherRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(teacherRegistrationRequest.getPassword()))
                .role(Role.TEACHER)
                .build();

        AppUser appUser2 = appUserDao.saveRecord(appUser1);

        Teacher teacher = Teacher.builder()
                .name(teacherRegistrationRequest.getName())
                .phoneNumber(teacherRegistrationRequest.getPhoneNumber())
                .school(school)
                .nin(fileUrl)
                .yearsOfTeaching(teacherRegistrationRequest.getYearsOfTeaching())
                .appUser(appUser2)
                .position(Position.valueOf(teacherRegistrationRequest.getPosition().toUpperCase()))
                .status(Status.valueOf(teacherRegistrationRequest.getStatus().toUpperCase()))
                .about(teacherRegistrationRequest.getAbout())
                .build();

        Teacher teacher1 = teacherDao.saveRecord(teacher);

        return registrationResponse.created("success", LocalDateTime.now(), teacher);
    }

}
