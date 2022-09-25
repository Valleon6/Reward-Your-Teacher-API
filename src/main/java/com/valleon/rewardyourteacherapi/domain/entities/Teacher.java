package com.valleon.rewardyourteacherapi.domain.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @Entity @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Teacher")
public class Teacher {

    private String name;

    private Integer phoneNumber;

    private Integer nin;

    private String displayPicture;

    private School school;

    private String yearsOfTeaching;

    private String subjectTaught;

    private Status status;

}
