package com.valleon.rewardyourteacherapi.domain.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@MappedSuperclass //enables the abstract class fields show on the DB
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "Date Created", nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "Date updated", nullable = false)
    private LocalDateTime updatedAt;
}
