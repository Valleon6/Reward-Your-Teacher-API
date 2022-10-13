package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.email.ConfirmationTokenEntity;

import java.time.LocalDateTime;

public interface ConfirmationTokenDao extends CrudDAO<ConfirmationTokenEntity, Long> {

    int updateConfirmedAt(String token, LocalDateTime now);

    ConfirmationTokenEntity findByToken (String token);
}
