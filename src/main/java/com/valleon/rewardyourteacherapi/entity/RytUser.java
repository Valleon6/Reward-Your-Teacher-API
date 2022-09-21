package com.valleon.rewardyourteacherapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Indexed;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

//@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rytusers")
public class RytUser implements Persistable<String> {


    //        @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
//    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
//    @Id()
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @JsonProperty("id")
    private String id;

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
//    @Index(unique = true)
    @Column(name = "email", nullable = false)
    private String email;
    @NotBlank
    String password;
    //    @Column
//    private String school;
//
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private RytUserRole gender;
    private RytUserRole role;

    @Override
    public boolean isNew() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
            return true;
        }
        return false;
    }
}
