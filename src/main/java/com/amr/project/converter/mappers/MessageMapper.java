package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Message;
import com.amr.project.model.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {UserMapper.class, ChatMapper.class})
public interface MessageMapper extends MapperInterface<MessageDto, Message> {

    @Override
    @Mappings({
            @Mapping(target = "toUserId", source = "entity.recipient"),
            @Mapping(target = "fromUserId", source = "entity.sender"),
            @Mapping(target = "creationTime", source = "entity.date"),
            @Mapping(target = "chatId", source = "entity.chat.id")
    })
    MessageDto toDto(Message entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

   User map(Long value);
   Long map(User value);

    @Override
    @Mappings({
            @Mapping(target = "recipient", source = "dto.toUserId"),
            @Mapping(target = "sender", source = "dto.fromUserId"),
            @Mapping(target = "date", source = "dto.creationTime"),
            @Mapping(target = "chat.id", source = "dto.chatId")
    })
    Message toEntity(MessageDto dto, CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
