package com.valleon.rewardyourteacherapi.domain.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//@AllArgsConstructor
@Entity
@Data
@Table(name = "rytusers")
public class AppUser {


//        @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
//    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RytUserId;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    @Column(name = "school", nullable = false)
    private String school;

//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private Role gender;


}
