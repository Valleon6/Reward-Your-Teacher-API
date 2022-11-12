package com.valleon.rewardyourteacherapi.usecase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherRegistrationRequest {

    @Pattern(regexp = "^[A-Za-z|\\s]*$", message = "Invalid name")
    private String name;

    @Pattern(regexp = "^[A-Za-z|\\s]*$", message = "Invalid School Name")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Minimum eight characters, at least one letter and one number")
    private String password;

    @Pattern(regexp = "^[A-Za-z|\\s]*$", message = "Invalid schoolName")
    private String school;

    private @Pattern(regexp = "[+-]?[0-9][0-9]*")
    String yearsOfTeaching;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    private String subjectTaught;

    @Pattern(regexp = "^[A-Za-z|\\s]*$", message = "Invalid school type")
    private String schoolType;

    @Pattern(regexp = "((^(234){1}[0–9]{10})|((^234)[0–9]{10})|((^0)(7|8|9){1}(0|1){1}[0–9]{8}))")
    private String phoneNumber;

    @NotBlank
    private String position;

    @NotBlank
    private String status;

    @NotBlank
    private String about;
}
