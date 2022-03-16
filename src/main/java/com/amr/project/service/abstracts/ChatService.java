package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ChatDto;

import java.util.List;

public interface ChatService {

    List<ChatDto> getAllChats();

    ChatDto getChatById(Long id);

    void saveChat(ChatDto chatDto);

    void updateChat(ChatDto chatDto);

    void deleteChat(Long id);
}
