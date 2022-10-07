package com.valleon.rewardyourteacherapi.service.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private BigDecimal Amount;

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private Long studentId;

    @Pattern(regexp = "[+-]?[0-9][0-9]*")
    private Long teacherId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
