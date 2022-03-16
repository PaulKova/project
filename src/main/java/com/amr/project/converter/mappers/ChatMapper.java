package com.amr.project.converter.mappers;

import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MessageMapper.class, UserMapper.class})
public interface ChatMapper {

    ChatDto toDto(Chat chat);

    Chat toEntity(ChatDto chatDto);

    List<ChatDto> toDtoList(List<Chat> chats);

    List<Chat> toEntityList(List<ChatDto> chatDtoS);
}
