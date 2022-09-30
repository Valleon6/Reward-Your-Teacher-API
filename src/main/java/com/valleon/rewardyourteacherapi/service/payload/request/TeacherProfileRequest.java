package com.valleon.rewardyourteacherapi.service.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherProfileRequest {

    @Pattern(regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)")
    private String name;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Enter a valid email address")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Minimum eight characters, at least one letter and one number")
    private String password;

    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid schoolName")
    private String schoolTaught;

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private int yearsOfTeaching;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    private String subjectsTaught;

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private String nin;

}
