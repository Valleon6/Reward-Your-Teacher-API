package com.valleon.rewardyourteacherapi.controller;

import com.valleon.rewardyourteacherapi.dto.RytUserLoginDto;
import com.valleon.rewardyourteacherapi.entity.RytUser;
import com.valleon.rewardyourteacherapi.pojos.APIResponse;
import com.valleon.rewardyourteacherapi.service.RytUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ryt")
@AllArgsConstructor
public class RytUserController {
    private final RytUserService rytUserService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUpRytUser(@RequestBody RytUser request) {
        return rytUserService.createUser(request);

    }

    @GetMapping("/users")
    public APIResponse getUsers() {
        return null;
    }

//    @GetMapping("/users")
//    public List<RytUser> getAllUsers(){
//        return rytUserServiceImpl.getAllUsers();
//    }

//
//
//    @DeleteMapping(path = "{userID}")
//    public void deleteUser(@PathVariable ("userID") Long userID){
////        return userService.deleteUser();
//
//    }

    @PostMapping
    ResponseEntity<String> loginUser(@RequestBody RytUserLoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>("User sigined-in successfully.", HttpStatus.OK);
    }

}
