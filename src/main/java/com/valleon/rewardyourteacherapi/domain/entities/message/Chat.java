package com.valleon.rewardyourteacherapi.domain.entities.message;

import com.valleon.rewardyourteacherapi.domain.entities.AbstractEntity;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat")
public class Chat extends AbstractEntity {

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String  message;

    @ManyToOne
    @JoinColumn
    private Student student;

    @ManyToOne
    @JoinColumn
    private Teacher teacher;

    @CreationTimestamp
    @Column(name = "Send_Date", nullable = false, updatable = false)
    private LocalDateTime sendDate;

    @CreationTimestamp
    @Column(name = "Date Received", nullable = false, updatable = false)
    private LocalDateTime receiveDate;


}
