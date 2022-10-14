package com.valleon.rewardyourteacherapi.usecase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendRewardRequest {

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private BigDecimal Amount;

    @Pattern(regexp = "^[A-Za-z|\\s]*$", message = "Invalid name")
    private String teacherName;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Enter a valid phone number")
    private String teacherPhone;
}
