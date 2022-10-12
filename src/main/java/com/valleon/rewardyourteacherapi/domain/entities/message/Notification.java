package com.valleon.rewardyourteacherapi.domain.entities.message;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.valleon.rewardyourteacherapi.domain.entities.AbstractEntity;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification extends AbstractEntity {

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

//    private NotificationType

    @JsonBackReference
    @ManyToOne
    private Student student;

    @JsonBackReference
    @ManyToOne
    private Teacher teacher;

}
