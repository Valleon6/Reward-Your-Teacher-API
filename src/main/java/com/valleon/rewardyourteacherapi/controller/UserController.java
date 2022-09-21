package com.valleon.rewardyourteacherapi.controller;

import com.valleon.rewardyourteacherapi.entity.RytUser;
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
    public ResponseEntity<APIResponse> signUpRytUser(@RequestBody RytUser request) {
        return rytUserService.createUser(request);

    }



//        return new ResponseEntity<RytUser>(rytUserService.registerRytUser(rytUser), HttpStatus.CREATED);


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
