package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ItemMapper;
import com.amr.project.converter.mappers.MessageMapper;
import com.amr.project.dao.ItemRepository;
import com.amr.project.dao.MessageRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Message;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.MessageService;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageDto> getAllMessages() {
        return messageMapper.toDtoList(messageRepository.findAll());
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return messageMapper.toDto(messageRepository.getById(id));
    }

    @Override
    public void saveMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity((messageDto));
        messageRepository.saveAndFlush(message);
    }

    @Override
    public void updateMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity((messageDto));
        messageRepository.saveAndFlush(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
