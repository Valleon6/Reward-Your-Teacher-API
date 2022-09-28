package com.valleon.rewardyourteacherapi.infrastructure.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.message.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
Optional<Chat> findChatByCreatedAt(LocalDateTime localDateTime);
}
