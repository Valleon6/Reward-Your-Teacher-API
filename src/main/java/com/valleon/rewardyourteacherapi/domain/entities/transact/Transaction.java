package com.valleon.rewardyourteacherapi.domain.entities.transact;

import com.valleon.rewardyourteacherapi.domain.entities.AbstractEntity;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transaction")
public class Transaction extends AbstractEntity {

private String transactionType;
private String description;
private BigDecimal amount;

@CreationTimestamp
@Column(name = "createdAt", nullable = false, updatable = false)
private LocalDateTime createdAt;

@ManyToOne
private Student student;

@ManyToOne
private Teacher teacher;
}
