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
    @JoinColumn(name = "student_chat_id")
    private Student student_id;

    @ManyToOne
    @JoinColumn(name = "teacher_chat_id")
    private Teacher teacher_id;

    @CreationTimestamp
    @Column(name = "send_date", nullable = false, updatable = false)
    private LocalDateTime sendDate;

    @CreationTimestamp
    @Column(name = "date received", nullable = false, updatable = false)
    private LocalDateTime receivedDate;
}
