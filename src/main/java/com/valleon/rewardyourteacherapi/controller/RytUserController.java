package com.valleon.rewardyourteacherapi.controller;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import com.valleon.rewardyourteacherapi.service.RytUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rytusers")
@AllArgsConstructor
public class RytUserController {
    private final RytUserServiceImpl rytUserServiceImpl;


    @PostMapping("/registeruser")
    public ResponseEntity<RytUser> registerUser(@RequestBody RytUser rytUser) {
        return new ResponseEntity<RytUser>(rytUserServiceImpl.registerRytUser(rytUser), HttpStatus.CREATED);
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

}
