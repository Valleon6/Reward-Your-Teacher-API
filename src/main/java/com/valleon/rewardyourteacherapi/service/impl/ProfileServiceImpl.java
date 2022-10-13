package com.valleon.rewardyourteacherapi.service.impl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.dao.SchoolDao;
import com.valleon.rewardyourteacherapi.domain.dao.StudentDao;
import com.valleon.rewardyourteacherapi.domain.dao.TeacherDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.infrastructure.configuration.security.UserDetails;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.EntityAlreadyExistException;
import com.valleon.rewardyourteacherapi.service.payload.ProfileService;
import com.valleon.rewardyourteacherapi.service.payload.request.StudentProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.request.TeacherProfileRequest;
import com.valleon.rewardyourteacherapi.service.payload.response.EditProfileResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.ViewTeacherProfileResponse;
import com.valleon.rewardyourteacherapi.utilities.CloudinaryService;
import com.valleon.rewardyourteacherapi.utilities.PayLoadMapper;
import com.valleon.rewardyourteacherapi.utilities.RequestMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final StudentDao studentDao;
    private final SchoolDao schoolDao;
    private final AppUserDao appUserDao;
    private final TeacherDao teacherDao;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    private final RequestMapper requestMapper;
    private final PayLoadMapper payLoadMapper;

    @Override
    public EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest) {
        String email = UserDetails.getLoggedInUserDetails();

        School school = schoolDao.findSchool(studentProfileRequest.getNameOfSchool())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));

        AppUser studentEntity = appUserDao.findAppUserByEmailAndRole(email, Role.STUDENT);
        if(studentEntity == null){
            throw new CustomNotFoundException("student not found");
        }
        AppUser appUser = appUserDao.findAppUserByEmail(studentProfileRequest.getEmail());
        if(appUser != null){
            throw new EntityAlreadyExistException("Email already exist");
        }
        studentEntity.setEmail(studentProfileRequest.getEmail());
        AppUser appUser1 = appUserDao.saveRecord(studentEntity);

        Student student = studentDao.getStudentByAppUser(appUser1);
        student.setSchool(school);
        student.setName(studentProfileRequest.getName());
        student.setPhoneNumber(studentProfileRequest.getPhone());
        EditProfileResponse editProfileResponse = payLoadMapper.studentEditMapper(studentDao.saveRecord(student));

        return editProfileResponse;
    }

    @Override
    public EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException {
        String email = UserDetails.getLoggedInUserDetails();


        School school = schoolDao.findSchool(teacherProfileRequest.getSchoolTaught())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));

        AppUser appUser = appUserDao.findAppUserByEmailAndRole(email,Role.TEACHER);
        if(appUser == null){
            throw new CustomNotFoundException("Teacher not found");
        }
        AppUser appUserEntity1 = appUserDao.findAppUserByEmail(teacherProfileRequest.getEmail());
        if(appUserEntity1 != null){
            throw new EntityAlreadyExistException("Email already exist");
        }
        appUser.setEmail(teacherProfileRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(teacherProfileRequest.getPassword()));

        String url = cloudinaryService.uploadImage(file);
        Teacher teacher = teacherDao.getTeacherByAppUser(appUser);
        teacher.setName(teacherProfileRequest.getName());
        teacher.setSchool(school);
        teacher.setYearsOfTeaching(teacherProfileRequest.getYearsOfTeaching());
        teacher.setPhoneNumber(teacherProfileRequest.getPhone());
        teacher.setNin(url);

        EditProfileResponse editProfileResponse = payLoadMapper.TeacherEditMapper( teacherDao.saveRecord(teacher));
        return editProfileResponse;
    }

    @Override
    public List<ViewTeacherProfileResponse> viewTeacherByName(String name) {
        List<Teacher> teachers = teacherDao.findTeacherByName(name).orElseThrow(()-> new CustomNotFoundException("Teacher not found"));
        List<ViewTeacherProfileResponse> viewTeacherProfileResponses = new ArrayList<>();
        teachers.forEach(teacher -> {
            ViewTeacherProfileResponse viewTeacherProfileResponse = requestMapper
                    .viewTeacherProfileResponseToTeacherMapper(teacher);
            viewTeacherProfileResponses.add(viewTeacherProfileResponse);
        });
        return viewTeacherProfileResponses;
    }
}
