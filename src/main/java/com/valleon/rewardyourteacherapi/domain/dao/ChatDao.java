package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.message.Chat;

import java.time.LocalDateTime;

public interface ChatDao {

    Chat findChatByCreatedAt(LocalDateTime localDateTime);
}
