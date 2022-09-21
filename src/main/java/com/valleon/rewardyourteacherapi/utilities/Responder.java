package com.valleon.rewardyourteacherapi.utilities;

import com.valleon.rewardyourteacherapi.pojos.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Responder <T>{

    public ResponseEntity<APIResponse> okay(T response){
        return new ResponseEntity<> (new APIResponse
                ("Request Successful", true, response), HttpStatus.OK);
    }

    public ResponseEntity<APIResponse> notFound(){
        return new ResponseEntity<>(new APIResponse
                ("Request not found", false, null), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<APIResponse> alreadyExist(String message){
        return new ResponseEntity<>(new APIResponse
                (message, true,  null), HttpStatus.CONFLICT);
    }
    public ResponseEntity<APIResponse> UnAuthorize(String message){
        return  new ResponseEntity<>(new APIResponse(message, true, null), HttpStatus.UNAUTHORIZED);
    }
}
