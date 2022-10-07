//package com.valleon.rewardyourteacherapi.service.payload.response;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class Responder <T>{
//
//    public ResponseEntity<ApiResponse> okay(T response){
//        return new ResponseEntity<> (new ApiResponse
//                ("Request Successful", true, response), HttpStatus.OK);
//    }
//
//    public ResponseEntity<ApiResponse> notFound(){
//        return new ResponseEntity<>(new ApiResponse
//                ("Request not found", false, null), HttpStatus.NOT_FOUND);
//    }
//
//    public ResponseEntity<ApiResponse> alreadyExist(String message){
//        return new ResponseEntity<>(new ApiResponse
//                (message, true,  null), HttpStatus.CONFLICT);
//    }
//    public ResponseEntity<ApiResponse> UnAuthorize(String message){
//        return  new ResponseEntity<>(new ApiResponse(message, true, null), HttpStatus.UNAUTHORIZED);
//    }
//}
