package com.valleon.rewardyourteacherapi.service.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WalletRequest {
    private BigDecimal balance;

    private BigDecimal totalMoneySent;
}
