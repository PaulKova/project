package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ChatMapper;
import com.amr.project.converter.mappers.UserMapper;
import com.amr.project.dao.ChatRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private final UserRepository userRepository;

    @Override
    public List<ChatDto> getAllChats() {
        List<Chat> chats = chatRepository.findAll();
        return chatMapper.toDtoList(chats, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveChat(ChatDto chatDto) {
        Chat chat = chatMapper.toEntity(chatDto, new CycleAvoidingMappingContext());
        chatRepository.saveAndFlush(chat);
    }

    @Override
    public void updateChat(ChatDto chatDto) {
        Chat chat = chatMapper.toEntity(chatDto, new CycleAvoidingMappingContext());
        chatRepository.saveAndFlush(chat);
    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public Chat getChatBySenderAndRecipient(User sender, User recipient) {
        return chatRepository.findBySenderAndRecipient(sender, recipient)
                .orElseGet(() -> {
                    Chat chat = Chat.builder().sender(sender).recipient(recipient).build();
                    chatRepository.save(chat);
                    return chat;
                });
    }

    @Override
    public List<ChatDto> getAllChatsOfUser(Long userId) {
        User user = userRepository.getById(userId);
        List<Chat> chatList = chatRepository.getAllBySender(user);
        return chatMapper.toDtoList(chatList, new CycleAvoidingMappingContext());
    }

}
