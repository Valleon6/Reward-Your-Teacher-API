package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.pojos.APIResponse;
import com.valleon.rewardyourteacherapi.service.RytUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ryt")
@AllArgsConstructor
public class UserController {
    private final RytUserService rytUserService;


    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUpRytUser(@RequestBody AppUser request) {
        return rytUserService.createUser(request);

    }
    @GetMapping("/users")
    public ResponseEntity<APIResponse>  getRytUser(){
        return rytUserService.getRytUsers();
    }


//    @DeleteMapping(path = "{userID}")
//    public void deleteUser(@PathVariable ("userID") Long userID){
////        return userService.deleteUser();
//    }

}
