package com.valleon.rewardyourteacherapi.domain.entities;

import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//@AllArgsConstructor
@Entity @Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "RYT App Users")
public class AppUser extends AbstractEntity{

//    @NotBlank
//    @Email
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

//    @NotBlank
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//
//    @Id()
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long RytUserId;
//
//    @NotBlank
//    @Column(name = "first_name", nullable = false)
//    private String firstName;




}
