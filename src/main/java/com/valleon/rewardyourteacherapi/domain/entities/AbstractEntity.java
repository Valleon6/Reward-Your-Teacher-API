package com.valleon.rewardyourteacherapi.domain.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @Entity @Builder
@AllArgsConstructor @NoArgsConstructor
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "Date Created", nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @Column(name = " Date updated", nullable = false)
    private LocalDateTime updateAt;
}
