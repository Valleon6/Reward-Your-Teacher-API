package com.valleon.rewardyourteacherapi.domain.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @Entity @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name= "School")
public class School extends AbstractEntity{

    @Column(name = "School ID", unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    private Integer schoolId;

    @Column(name = "School Name", nullable = false, columnDefinition = "VARCHAR(250)")
    private String schoolName;

    @Column(name = "School Type", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolType;

    @Column(name = "School Address", nullable = false, columnDefinition = "VARCHAR(250)")
    private String schoolAddress;

    @Column(name = "School State", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolCity;

    @Column(name = "School State", nullable = true, columnDefinition = "VARCHAR(100)")
    private String schoolState;

}
