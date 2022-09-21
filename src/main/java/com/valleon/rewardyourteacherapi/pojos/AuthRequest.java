package com.valleon.rewardyourteacherapi.pojos;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private  String password;
}
