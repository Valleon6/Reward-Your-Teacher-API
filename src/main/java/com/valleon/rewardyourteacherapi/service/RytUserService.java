package com.valleon.rewardyourteacherapi.service;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import com.valleon.rewardyourteacherapi.pojos.APIResponse;
import com.valleon.rewardyourteacherapi.pojos.AuthRequest;
import com.valleon.rewardyourteacherapi.repository.RytUserRepository;
import com.valleon.rewardyourteacherapi.security.JwtService;
import com.valleon.rewardyourteacherapi.utilities.Responder;
import com.valleon.rewardyourteacherapi.utilities.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class RytUserService {

    private final RytUserRepository rytUserRepository;
    private final Utility utility;
    private final Responder responder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<APIResponse> createUser(RytUser rytUser) {
        RytUser user = rytUserRepository.findById(rytUser.getRytUserId()).orElse(null);
        if (user == null) { // Carried out if user does not exist
            rytUser.setRytUserId(utility.generateUniqueId()); // sets a unique Id for the user
            rytUser.setPassword(passwordEncoder.encode(rytUser.getPassword()));
            RytUser result = rytUserRepository.save(rytUser);
            return responder.okay(result);
        } else {
            return responder.alreadyExist("User already exist");
        }


    }

    public ResponseEntity<APIResponse> authenticate(AuthRequest request){
        System.out.println("I am here !!!!!!!!!!!!!!!!!!");
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(auth.isAuthenticated()){
            String token="Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(request.getUsername(),request.getPassword(),new ArrayList<>()));
            return  responder.okay(token);
        }else{
            return  responder.UnAuthorize("Authentication Failed");
        }
    }

    public ResponseEntity<APIResponse> getRytUsers(){
        return responder.okay("#####This is the list of the users............");
    }
}
