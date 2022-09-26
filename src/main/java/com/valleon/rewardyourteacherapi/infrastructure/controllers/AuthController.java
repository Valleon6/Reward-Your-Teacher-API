//package com.valleon.rewardyourteacherapi.infrastructure.controllers;
//
//import com.valleon.rewardyourteacherapi.pojos.APIResponse;
//import com.valleon.rewardyourteacherapi.pojos.AuthRequest;
//import com.valleon.rewardyourteacherapi.service.RytUserService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("api/ryt")
//@RestController
//@AllArgsConstructor
//public class AuthController {
//    private final RytUserService rytUserService;
//
//    @PostMapping("/auth")
//    public ResponseEntity<APIResponse> authenticate(@RequestBody AuthRequest request){
//        System.out.println(request.getUsername());
//        return  rytUserService.authenticate(request);
//    }
//
//}
