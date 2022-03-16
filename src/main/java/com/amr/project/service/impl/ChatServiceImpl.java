package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ChatMapper;
import com.amr.project.dao.ChatRepository;
import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;


    @Override
    public List<ChatDto> getAllChats() {
        List<Chat> chats = chatRepository.findAll();
        return chatMapper.toDtoList(chats);
    }

    @Override
    public ChatDto getChatById(Long id) {
        Chat chat = chatRepository.getById(id);
        return chatMapper.toDto(chat);
    }

    @Override
    public void saveChat(ChatDto chatDto) {
        Chat chat = chatMapper.toEntity(chatDto);
        chatRepository.saveAndFlush(chat);
    }

    @Override
    public void updateChat(ChatDto chatDto) {
        Chat chat = chatMapper.toEntity(chatDto);
        chatRepository.saveAndFlush(chat);
    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }
}
