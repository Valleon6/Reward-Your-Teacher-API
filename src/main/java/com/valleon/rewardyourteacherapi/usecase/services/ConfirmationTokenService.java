package com.valleon.rewardyourteacherapi.usecase.services;

import com.valleon.rewardyourteacherapi.domain.entities.email.ConfirmationTokenEntity;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationTokenEntity token);

    ConfirmationTokenEntity getToken(String token);

    int setConfirmedAt(String token);

}


