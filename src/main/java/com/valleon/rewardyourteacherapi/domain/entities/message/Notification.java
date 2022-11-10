package com.valleon.rewardyourteacherapi.domain.entities.message;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.valleon.rewardyourteacherapi.domain.entities.AbstractEntity;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import com.valleon.rewardyourteacherapi.domain.entities.enums.NotificationType;
import com.valleon.rewardyourteacherapi.domain.entities.transact.Transaction;
import lombok.*;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @JsonBackReference
    @ManyToOne
    private Student student;

    @JsonBackReference
    @ManyToOne
    private Teacher teacher;

    private boolean isAppreciated = false;

    @OneToOne
    private Transaction transaction;

}
