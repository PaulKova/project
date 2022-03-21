package com.amr.project.service.impl;

import com.amr.project.converter.mappers.MessageMapper;
import com.amr.project.dao.MessageRepository;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Message;
import com.amr.project.service.abstracts.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mapstruct.example.mapper.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageDto> getAllMessages() {
        return messageMapper.toDtoList(messageRepository.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return messageMapper.toDto(messageRepository.getById(id), new CycleAvoidingMappingContext());
    }

    @Override
    public void saveMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity((messageDto), new CycleAvoidingMappingContext());
        messageRepository.saveAndFlush(message);
    }

    @Override
    public void updateMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity((messageDto), new CycleAvoidingMappingContext());
        messageRepository.saveAndFlush(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
