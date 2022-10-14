package com.valleon.rewardyourteacherapi.usecase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentProfileRequest {
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    private String name;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Enter a valid phone number")
    private String phone;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Enter a valid email address")
    private String email;

    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid schoolName")
    private String nameOfSchool;
}
