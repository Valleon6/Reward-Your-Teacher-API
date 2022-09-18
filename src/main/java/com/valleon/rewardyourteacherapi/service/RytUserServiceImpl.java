package com.valleon.rewardyourteacherapi.service;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import com.valleon.rewardyourteacherapi.repository.RytUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RytUserServiceImpl implements RytUserService {
    private RytUserRepository rytUserRepository;

    @Autowired
    public RytUserServiceImpl(RytUserRepository rytUserRepository) {
        super();
        this.rytUserRepository = rytUserRepository;
    }

    @Override
    public RytUser registerRytUser(RytUser rytUser) {
        return rytUserRepository.save(rytUser);
    }
//    public UserRepository userRepository;
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public void addUser(User user) {
//        Boolean existsEmail = userRepository.selectExistsEmail(user.getEmail());
////        if (existsEmail) {
////            throw new BadRequestException(
////                    "Email " + user.getEmail() + " taken");
//    }
//
//
//    public void deleteUser() {
//    }
}
