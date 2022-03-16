package com.amr.project.converter.mappers;

import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ChatMapper.class})
public interface MessageMapper {

    MessageDto toDto(Message message);

    Message toEntity(MessageDto messageDto);

    List<MessageDto> toDtoList(List<Message> messages);

    List<Message> toEntityList(List<MessageDto> messageDtoS);
}
