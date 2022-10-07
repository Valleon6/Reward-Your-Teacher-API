package com.valleon.rewardyourteacherapi.domain.pojos;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private  String password;
}
