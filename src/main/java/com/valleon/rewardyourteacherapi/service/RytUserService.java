package com.valleon.rewardyourteacherapi.service;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import com.valleon.rewardyourteacherapi.pojos.APIResponse;
import com.valleon.rewardyourteacherapi.repository.RytUserRepository;
import com.valleon.rewardyourteacherapi.utilities.Responder;
import com.valleon.rewardyourteacherapi.utilities.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RytUserService {

    private final RytUserRepository rytUserRepository;
    private final Utility utility;
    private final Responder responder;

    public ResponseEntity<APIResponse> createUser(RytUser rytUser) {
        RytUser user = rytUserRepository.findById(rytUser.getRytUserId()).orElse(null);
        if (user == null) { // Carried out if user does not exist
            rytUser.setRytUserId(utility.generateUniqueId()); // sets a unique Id for the user
            RytUser result = rytUserRepository.save(rytUser);
            return responder.okay(result);
        } else {
            return responder.alreadyExist("User already exist");
        }


    }



//    private RytUserRepository rytUserRepository;
//
//    @Autowired
//    public RytUserService(RytUserRepository rytUserRepository) {
//        super();
//        this.rytUserRepository = rytUserRepository;
//    }
//
//    @Override
//    public RytUser registerRytUser(RytUser rytUser) {
//        return rytUserRepository.save(rytUser);
//    }
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
