package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ChatService {

    List<ChatDto> getAllChats();


    void saveChat(ChatDto chatDto);

    void updateChat(ChatDto chatDto);

    void deleteChat(Long id);

    Chat getChatBySenderAndRecipient(User sender, User recipient);

    List<ChatDto> getAllChatsOfUser(Long userId);
}
