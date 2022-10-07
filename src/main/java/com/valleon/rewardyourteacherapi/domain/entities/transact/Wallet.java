package com.valleon.rewardyourteacherapi.domain.entities.transact;

import com.valleon.rewardyourteacherapi.domain.entities.AbstractEntity;
import com.valleon.rewardyourteacherapi.domain.entities.Student;
import com.valleon.rewardyourteacherapi.domain.entities.Teacher;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "wallet")
public class Wallet extends AbstractEntity {

    @DecimalMin(value= "0.0", inclusive = false)
    @Digits(integer = 9, fraction= 2)
    @Column(name = "balance", nullable = false, columnDefinition = "NUMERIC(11,2) DEFAULT 0.0")
    private BigDecimal balance;

    @Column(name = "totalMoneySpent", nullable = false, columnDefinition = "NUMERIC(11,2)")
    private BigDecimal totalMoneySpent;

    @OneToOne
    @JoinColumn(name = "student_Id", referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

}
