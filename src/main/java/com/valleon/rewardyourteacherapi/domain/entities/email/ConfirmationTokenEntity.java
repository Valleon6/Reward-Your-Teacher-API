package com.valleon.rewardyourteacherapi.domain.entities.email;

import com.valleon.rewardyourteacherapi.domain.entities.AbstractEntity;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationTokenEntity extends AbstractEntity {

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;


    public ConfirmationTokenEntity(String token, LocalDateTime expiresAt, AppUser appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }

    public ConfirmationTokenEntity(LocalDateTime createdAt, String token, LocalDateTime expiresAt, AppUser appUser) {
        super(createdAt);
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
