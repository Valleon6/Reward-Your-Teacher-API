package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.infrastructure.configuration.security.JwtService;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.AuthenticationFailedException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.service.payload.LoginService;
import com.valleon.rewardyourteacherapi.service.payload.request.LoginRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.SocialLoginRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService {

    private final TeacherDao teacherDao;

    private final StudentDao studentDao;

    private final JwtService jwtService;

    private final ApiResponse loginResponse;

    private final AppUserDao appUserDao;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResponse loginStudent(LoginRequest studentLoginRequest) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(studentLoginRequest.
                        getEmail(), studentLoginRequest.getPassword()));

        if(!auth.isAuthenticated()){
            throw new AuthenticationFailedException("Wrong email or password");
        }

AppUser appUser = appUserDao.findAppUserByEmail(studentLoginRequest.getEmail())
        .orElseThrow(() -> new CustomNotFoundException("User does not exist"));

     if(!appUser.getRole().equals(Role.STUDENT)){
         throw new AuthenticationFailedException("Wrong email or password");
     }
     String token = "Bearer" + jwtService
             .generateToken(new org.springframework.security.core.userdetails
                     .User(studentLoginRequest.getEmail(), studentLoginRequest.getPassword(),
                     new ArrayList<>()));
        return loginResponse.accepted(token, LocalDateTime.now());
    }

    @Override
    public LoginResponse studentSocialLogin(SocialLoginRequest socialLoginRequest) {
        socialLoginRequest.setPassword("");

        Optional<AppUser> appUser = appUserDao.findAppUserByEmail(socialLoginRequest.getPassword());

        if(appUser.isEmpty()){
            AppUser appUser1 = AppUser.builder()
                    .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                    .email(socialLoginRequest.getEmail())
                    .role(Role.STUDENT)
                    .build();

            AppUser user = appUserDao.saveRecord(appUser1);

            String name = socialLoginRequest.getFirstname() + " " + socialLoginRequest.getLastName();
            Student student = Student.builder()
                    .name(name)
                    .displayPicture(socialLoginRequest.getDisplayPicture())
                    .appUser(user)
                    .build();

            studentDao.saveRecord(student);
        }
        String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                .userdetails.User(socialLoginRequest.getEmail()
                , socialLoginRequest.getFirstname()
                , new ArrayList<>()));

        return loginResponse.accepted(token, LocalDateTime.now());
    }

    @Override
    public LoginResponse loginTeacher(LoginRequest teacherLoginRequest) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(teacherLoginRequest
                        .getEmail(), teacherLoginRequest.getPassword()));

        if(!auth.isAuthenticated()){
            throw new AuthenticationFailedException("wrong email or password");
        }

        AppUser appUser = appUserDao.findAppUserByEmail(teacherLoginRequest
                .getEmail()).orElseThrow(() -> new CustomNotFoundException("User does not exist"));

        if(!appUser.getRole().equals(Role.TEACHER)){
            throw new AuthenticationFailedException("Wrong email or password");
        }

        String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                .userdetails.User(teacherLoginRequest.getEmail(), teacherLoginRequest.getPassword(),new ArrayList<>()));

        return loginResponse.accepted(token, LocalDateTime.now());
    }

    @Override
    public LoginResponse teacherSocialLogin(SocialLoginRequest socialLoginRequest) {
        socialLoginRequest.setPassword("");

        Optional<AppUser> appUser = appUserDao.findAppUserByEmail(socialLoginRequest.getPassword());
               if(appUser.isEmpty()){
                   AppUser appUser1 = AppUser.builder()
                           .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                           .email(socialLoginRequest.getEmail())
                           .role(Role.TEACHER)
                           .build();

                   AppUser user = appUserDao.saveRecord(appUser1);

                   String name = socialLoginRequest.getFirstname() + " " + socialLoginRequest.getLastName();
                   Teacher teacher = Teacher.builder()
                           .name(name)
                           .appUser(user)
                           .displayPicture(socialLoginRequest.getDisplayPicture())
                           .build();
                   teacherDao.saveRecord(teacher);
               }
               String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core.userdetails.User(socialLoginRequest.getEmail(),
                       socialLoginRequest.getFirstname(),
                       new ArrayList<>()));

        return loginResponse.accepted(token, LocalDateTime.now());
    }
}
