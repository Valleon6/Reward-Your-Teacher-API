package com.valleon.rewardyourteacherapi.domain.entities;

import com.valleon.rewardyourteacherapi.domain.entities.enums.Position;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Status;
import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter @Setter @Entity @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Teachers")
public class Teacher extends AbstractEntity {

    @Column(name = "TeacherDao Name", unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private String name;

    @Column(name = "Phone Number", unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private Integer phoneNumber;

    @Column(name = "National Identity Number", unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private Integer nin;

    @Column(name = "Display Picture", unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private String displayPicture;

    @Column(name = "Years Taught", unique = true, nullable = false, columnDefinition = "VARCHAR(250)")
    private String yearsOfTeaching;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private School school;

    private String subjectTaught;

    private String about;

    private Status status;

    private Position position;

}
