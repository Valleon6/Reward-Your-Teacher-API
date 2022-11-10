package com.valleon.rewardyourteacherapi.domain.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "school")
public class School extends AbstractEntity {



    @Column(name = "school_name", nullable = false, columnDefinition = "VARCHAR(250)")
    private String schoolName;

    @Column(name = "school_type", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolType;

    @Column(name = "school_address", nullable = false, columnDefinition = "VARCHAR(250)")
    private String schoolAddress;

    @Column(name = "school_city", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolCity;

    @Column(name = "school_state", nullable = false, columnDefinition = "VARCHAR(100)")
    private String schoolState;

}
