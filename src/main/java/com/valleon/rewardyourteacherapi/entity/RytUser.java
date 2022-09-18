package com.valleon.rewardyourteacherapi.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//@AllArgsConstructor
@Entity
@Data
@Table(name = "rytusers")
public class RytUser {


    //    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
//    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

//    @Column
//    private String school;
//
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private RytUserRole gender;


}
