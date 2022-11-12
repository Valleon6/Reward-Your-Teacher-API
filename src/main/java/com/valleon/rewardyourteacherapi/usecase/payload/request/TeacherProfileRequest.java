package com.valleon.rewardyourteacherapi.usecase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherProfileRequest {

    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid name")
    private String name;
    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid Email")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Minimum eight characters, at least one letter and one number")
    private String password;
    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid schoolName")
    private String school;

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private String yearsOfTeaching;
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    private String subjectTaught;

    @Pattern(regexp = "((^(234){1}[0–9]{10})|((^234)[0–9]{10})|((^0)(7|8|9){1}(0|1){1}[0–9]{8}))")
    private String phoneNumber;

    @NotBlank
    private String position;

    @NotBlank
    private String status;

    @NotBlank
    private String about;

}
