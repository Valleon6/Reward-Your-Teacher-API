package com.valleon.rewardyourteacherapi.usecase.services.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.dao.WalletDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.configuration.security.JwtService;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.AuthenticationFailedException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.usecase.payload.request.LoginRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.request.SocialLoginRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.LoginResponse;
import com.valleon.rewardyourteacherapi.usecase.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService {

    private final UserDetailsService userService;
    private final TeacherDao teacherDao;
    private final StudentDao studentDao;
    private final JwtService jwtService;
    private final AppUserDao appUserDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final WalletDao walletDao;


    @Override
    public LoginResponse loginStudent(LoginRequest studentLoginRequest) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(studentLoginRequest
                        .getEmail(), studentLoginRequest.getPassword()));

        if (!auth.isAuthenticated()) {
            throw new AuthenticationFailedException("Wrong email or password");
        }

        AppUser appUser = appUserDao
                .findAppUserByEmailAndRole(studentLoginRequest.getEmail(), Role.STUDENT);
        if (appUser == null) {
            throw new CustomNotFoundException("User does not exist");
        }

        if (!appUser.getRole().equals(Role.STUDENT)) {
            throw new AuthenticationFailedException("Unauthorized");
        }
        if (!appUser.isVerified()) {
            throw new AuthenticationFailedException("User not verified");
        }
        Student student = studentDao.getStudentByAppUser(appUser);
        String token = "Bearer" + jwtService
                .generateToken(new org.springframework.security.core.userdetails
                        .User(studentLoginRequest.getEmail(), studentLoginRequest.getPassword(),
                        new ArrayList<>()));
        return new LoginResponse(token, student.getName(), student.getDisplayPicture());
    }

    @Override
    public LoginResponse studentSocialLogin(SocialLoginRequest socialLoginRequest) {
        if (socialLoginRequest.getEmail().equals("")) {
            throw new CustomNotFoundException("Enter email");
        }

        socialLoginRequest.setPassword("");
        AppUser appUser = appUserDao.findAppUserByEmailAndRole(socialLoginRequest.getEmail(), Role.STUDENT);

        if (appUser == null) {
            AppUser appUser1 =  appUserDao.findAppUserByEmailAndRole(socialLoginRequest.getEmail(),Role.TEACHER);
            if(appUser1!= null){
                throw new CustomNotFoundException("Already Registered as a Teacher");
            }else {
                AppUser appUserEntity = AppUser.builder()
                        .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                        .email(socialLoginRequest.getEmail())
                        .isVerified(true)
                        .role(Role.STUDENT)
                        .build();
                AppUser user = appUserDao.saveRecord(appUserEntity);

                String name = socialLoginRequest.getFirstname() + " " + socialLoginRequest.getLastName();
                Student studentEntity = Student.builder()
                        .name(name)
                        .displayPicture(socialLoginRequest.getDisplayPicture())
                        .appUser(user).build();

                studentDao.saveRecord(studentEntity);
                Wallet wallet = new Wallet();
                wallet.setBalance(new BigDecimal("0.00"));
                wallet.setStudent(studentEntity);
                wallet.setTotalMoneySent(new BigDecimal("0.00"));
                walletDao.saveRecord(wallet);
                Student student = studentDao.getStudentByAppUser(appUser);
                String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                        .userdetails.User(socialLoginRequest.getEmail(), socialLoginRequest
                        .getFirstname(), new ArrayList<>()));
                return new LoginResponse(token,student.getName(),student.getDisplayPicture());
            }

        }
        Student student = studentDao.getStudentByAppUser(appUser);
        String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                .userdetails.User(socialLoginRequest.getEmail(), socialLoginRequest
                .getFirstname(), new ArrayList<>()));
        return new LoginResponse(token,student.getName(),student.getDisplayPicture());
        }

        @Override
        public LoginResponse loginTeacher (LoginRequest teacherLoginRequest){
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(teacherLoginRequest
                            .getEmail(), teacherLoginRequest.getPassword()));

            if (!auth.isAuthenticated()) {
                throw new AuthenticationFailedException("wrong email or password");
            }

            AppUser appUser = appUserDao
                    .findAppUserByEmailAndRole(teacherLoginRequest.getEmail(), Role.TEACHER);
            if (appUser == null) {
                throw new CustomNotFoundException("Unauthorised");
            }

            if (!appUser.getRole().equals(Role.TEACHER)) {
                throw new AuthenticationFailedException("Unauthorised");
            }
            if (!appUser.isVerified()) {
                throw new AuthenticationFailedException("User not verified");
            }

            String token = "Bearer " + jwtService
                    .generateToken(new org.springframework.security.core
                            .userdetails.User(teacherLoginRequest.getEmail(), teacherLoginRequest.getPassword(), new ArrayList<>()));

            return new LoginResponse(token);
        }

        @Override
        public LoginResponse teacherSocialLogin (SocialLoginRequest socialLoginRequest){
            socialLoginRequest.setPassword("");

            AppUser appUser = appUserDao.findAppUserByEmail(socialLoginRequest.getPassword());
            if (appUser == null) {
                AppUser appUser1 = AppUser.builder()
                        .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                        .email(socialLoginRequest.getEmail())
                        .role(Role.TEACHER)
                        .isVerified(true)
                        .build();

                AppUser user = appUserDao.saveRecord(appUser1);

                String name = socialLoginRequest.getFirstname() + " " + socialLoginRequest.getLastName();
                Teacher teacher = Teacher.builder()
                        .name(name)
                        .appUser(user)
                        .displayPicture(socialLoginRequest.getDisplayPicture())
                        .build();
                teacherDao.saveRecord(teacher);
                Wallet wallet = new Wallet();
                wallet.setBalance(new BigDecimal("0.00"));
                wallet.setTeacher(teacher);
                wallet.setTotalMoneySent(new BigDecimal("0.00"));
                walletDao.saveRecord(wallet);
            }
            String token = "Bearer " + jwtService.generateToken(new org.springframework.security.core
                    .userdetails.User(socialLoginRequest.getEmail(),
                    socialLoginRequest.getFirstname(),
                    new ArrayList<>()));

            return new LoginResponse(token);
        }
    }
