package com.valleon.rewardyourteacherapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RYT App Users")
public class AppUser extends AbstractEntity {

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "appUser")
    private List<Teacher> teachers;

    @JsonManagedReference
    @OneToMany(mappedBy = "appUser")
    private List<Student> students;
}
