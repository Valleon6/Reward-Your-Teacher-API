//package com.valleon.rewardyourteacherapi.service;
//
//import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
//import com.valleon.rewardyourteacherapi.response.ApiResponse;
//import com.valleon.rewardyourteacherapi.pojos.AuthRequest;
//import com.valleon.rewardyourteacherapi.persistence.repository.AppUserRepository;
//import com.valleon.rewardyourteacherapi.infrastructure.configuration.security.JwtService;
//import com.valleon.rewardyourteacherapi.service.payload.response.Responder;
//import com.valleon.rewardyourteacherapi.utilities.Utility;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//@AllArgsConstructor
//public class RytUserService {
//
//    private final AppUserRepository appUserRepository;
//    private final Utility utility;
//    private final Responder responder;
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;
//    private PasswordEncoder passwordEncoder;
//
//
//    public ResponseEntity<ApiResponse> createUser(AppUser appUser) {
//        AppUser user = appUserRepository.findById(appUser.getRytUserId()).orElse(null);
//        if (user == null) { // Carried out if user does not exist
//            appUser.setRytUserId(utility.generateUniqueId()); // sets a unique Id for the user
//            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//            AppUser result = appUserRepository.save(appUser);
//            return responder.okay(result);
//        } else {
//            return responder.alreadyExist("User already exist");
//        }
//
//
//    }
//
//    public ResponseEntity<ApiResponse> authenticate(AuthRequest request){
//        System.out.println("I am here !!!!!!!!!!!!!!!!!!");
//        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        if(auth.isAuthenticated()){
//            String token="Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(request.getUsername(),request.getPassword(),new ArrayList<>()));
//            return  responder.okay(token);
//        }else{
//            return  responder.UnAuthorize("Authentication Failed");
//        }
//    }
//
//    public ResponseEntity<ApiResponse> getRytUsers(){
//        return responder.okay("#####This is the list of the users............");
//    }
//}
