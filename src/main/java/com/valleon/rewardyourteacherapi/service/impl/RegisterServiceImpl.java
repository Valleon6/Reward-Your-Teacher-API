package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.service.RegisterService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.APIResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final StudentDao studentDao;
    private final SchoolDao schoolDao;

    private final APIResponse registrationResponse;

    private final AppUserDao appUserDao;

    private final TeacherDao teacherDao;


    @Override
    public RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) {
        Optional<AppUser> appUser = appUserDao.findAppUserByEmail(studentRegistrationRequest.getEmail());
        if(appUser.isPresent()){
            throw new RuntimeException();
        }

        //        Optional<AppUser> appUser = appUserDao.findAppUserByEmail(studentRegistrationRequest.getEmail());
//        if (appUser.isPresent()) {
//            throw new EntityAlreadyExistException("Email already taken");
//        }
//
//        School school = schoolDao.findSchool(studentRegistrationRequest.getSchoolName())
//                .orElseThrow(() -> new CustomNotFoundException("School not found"));
//
//        AppUser appUserEntity = AppUser.builder()
//                .email(studentRegistrationRequest.getEmail())
//                .password(passwordEncoder.encode(studentRegistrationRequest.getPassword()))
//                .role(Role.STUDENT)
//                .build();
//        AppUserEntity appUserEntity1 = appUserDao.saveRecord(appUserEntity);
//        StudentEntity student = StudentEntity
//                .builder()
//                .name(studentRegistrationRequest.getName())
//                .phoneNumber(studentRegistrationRequest.getPhoneNumber())
//                .school(school)
//                .appUserEntity(appUserEntity1)
//                .build();
//
//        return registrationResponse.created("Success", LocalDateTime.now(), studentDao.saveRecord(student));
        return null;
    }
}
