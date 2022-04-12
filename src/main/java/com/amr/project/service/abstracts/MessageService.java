package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MessageService {
    Message save(Message message);

    List<MessageDto> getAllMessages();

    MessageDto getMessageById(Long id);

    void saveMessage(MessageDto messageDto);

    void updateMessage(MessageDto messageDto);

    void deleteMessage(Long id);

    Optional<List<MessageDto>> getAllMessagesByChatId(Long chatId);
}
