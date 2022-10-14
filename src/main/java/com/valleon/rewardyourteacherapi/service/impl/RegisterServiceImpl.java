package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.*;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.email.ConfirmationTokenEntity;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Position;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Status;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Wallet;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.EntityAlreadyExistException;
import com.valleon.rewardyourteacherapi.service.payload.ConfirmationTokenService;
import com.valleon.rewardyourteacherapi.service.payload.RegisterService;
import com.valleon.rewardyourteacherapi.service.payload.request.EmailDetailsRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherRegistrationRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.RegistrationResponse;
import com.valleon.rewardyourteacherapi.utilities.CloudinaryService;
import com.valleon.rewardyourteacherapi.utilities.EmailService;
import com.valleon.rewardyourteacherapi.utilities.PayLoadMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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

    private final PayLoadMapper payLoadMapper;

    private final ConfirmationTokenService confirmationTokenService;

    private final EmailService emailService;

    private  final WalletDao walletDao;

    @Override
    public RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) throws Exception{
        AppUser appUser = appUserDao.findAppUserByEmail(studentRegistrationRequest.getEmail());

        if (appUser != null) {
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
        Student student = Student
                .builder()
                .name(studentRegistrationRequest.getName())
                .phoneNumber(studentRegistrationRequest.getPhoneNumber())
                .school(school)
                .appUser(appUser2)
                .build();


        RegistrationResponse response =payLoadMapper.studentEntityMapper(studentDao.saveRecord(student));
        String token = UUID.randomUUID().toString();
        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.buildVerificationEmail(studentRegistrationRequest.getName(),"http://localhost:8001/api/v1/register/verification?token=" + token))
                .subject("Valleon email")
                .recipient(studentRegistrationRequest.getEmail())
                .build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser2
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("0.00"));
        wallet.setStudent(student);
        wallet.setTotalMoneySent(new BigDecimal("0.00"));
        walletDao.saveRecord(wallet);

        return response;  }



    @Override
    public RegistrationResponse registerTeacher(@Valid TeacherRegistrationRequest teacherRegistrationRequest, MultipartFile file) throws Exception {
        AppUser appUser = appUserDao.findAppUserByEmail(teacherRegistrationRequest.getEmail());

        if(appUser != null){
            throw new EntityAlreadyExistException("Email already registered");
        }

        Optional<Teacher> teacherPhoneNumber = teacherDao.findTeacherByPhoneNumber(teacherRegistrationRequest.getPhoneNumber());
        if(teacherPhoneNumber.isPresent()){
            throw new EntityAlreadyExistException("Phone number already taken");
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
                .subjectTaught(teacherRegistrationRequest.getSubjectTaught())
                .appUser(appUser2)
                .position(Position.valueOf(teacherRegistrationRequest.getPosition().toUpperCase()))
                .status(Status.valueOf(teacherRegistrationRequest.getStatus().toUpperCase()))
                .about(teacherRegistrationRequest.getAbout())
                .build();

        RegistrationResponse response =payLoadMapper.teacherMapper(teacherDao.saveRecord(teacher));
        String token = UUID.randomUUID().toString();
        EmailDetailsRequest emailDetailsRequest = EmailDetailsRequest.builder()
                .msgBody(emailService.buildVerificationEmail(teacherRegistrationRequest.getName(),"http://localhost:8001/api/v1/register/verification?token=" + token))
                .subject("Valleon6 email")
                .recipient(teacherRegistrationRequest.getEmail())
                .attachment(fileUrl).build();
        emailService.sendMailWithAttachment(emailDetailsRequest);

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now().plusMinutes(15),
                appUser2
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("0.00"));
        wallet.setTeacher(teacher);
        wallet.setTotalMoneySent(new BigDecimal("0.00"));
        walletDao.saveRecord(wallet);

        return response;
    }

    @Override
    public Object verifyUser(String userToken) {
        ConfirmationTokenEntity confirmationToken = confirmationTokenService.getToken(userToken);
        if (confirmationToken == null) {
            throw new CustomNotFoundException("token not found");
        }
        if (confirmationToken.getConfirmedAt() != null) {
            throw new EntityAlreadyExistException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new CustomNotFoundException("token expired");
        }
        confirmationTokenService.setConfirmedAt(userToken);
        confirmationToken.getAppUser().setVerified(true);
        return "confirmed";
    }

}
