package com.valleon.rewardyourteacherapi.domain.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends AbstractEntity {

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(unique = true, columnDefinition = "VARCHAR (250)")
    private String displayPicture;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    @JoinColumn
    private School school;

//
//    private TeacherDao teacher;
//
//    private AppUser appUser;

}
