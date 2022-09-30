package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.ChatDao;
import com.valleon.rewardyourteacherapi.domain.entities.message.Chat;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatDaoImpl extends CrudDaoImpl<Chat, Long> implements ChatDao {

    private final ChatRepository chatRepository;

    public ChatDaoImpl(ChatRepository chatRepository) {
        super(chatRepository);
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat findChatByCreatedAt(LocalDateTime localDateTime) {
        Optional<Chat> optionalChat = chatRepository.findChatByCreatedAt(localDateTime);

        if(!optionalChat.isPresent()){
            throw new RuntimeException();
        }

        return optionalChat.get();
    }
}
