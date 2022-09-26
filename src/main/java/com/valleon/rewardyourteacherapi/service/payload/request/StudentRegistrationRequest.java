package com.valleon.rewardyourteacherapi.service.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistrationRequest {

    @Pattern(regexp = "^[A-Za-z|\\s]*$", message = "Invalid name")
    private String name;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Enter a valid email address")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Minimum eight characters, at least one letter and one number")
    private String password;

    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid schoolName")
    private String schoolName;

    @Pattern(regexp = "((^(234){1}[0–9]{10})|((^234)[0–9]{10})|((^0)(7|8|9){1}(0|1){1}[0–9]{8}))")
    private String phoneNumber;
}
