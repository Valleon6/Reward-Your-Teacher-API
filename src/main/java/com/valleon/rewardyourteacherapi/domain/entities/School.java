package com.valleon.rewardyourteacherapi.domain.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @Entity @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name= "School")
public class School extends AbstractEntity{

    @Column(name = "School_ID")
    private Integer schoolId;

    @Column(name = "School_Name", nullable = false, columnDefinition = "VARCHAR(250)")
    private String schoolName;

    @Column(name = "School_Type", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolType;

    @Column(name = "School_Address", nullable = false, columnDefinition = "VARCHAR(250)")
    private String schoolAddress;

    @Column(name = "School_City", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolCity;

    @Column(name = "School_State", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolState;

}
