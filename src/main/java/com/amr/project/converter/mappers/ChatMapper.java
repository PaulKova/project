package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.entity.Chat;
import org.mapstruct.*;

import java.util.Set;

//TODO: выяснить, почему не происходит маппинга (для проверки см.сгенерированный файл "ChatMapperImpl"
@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring",
        uses = {MessageMapper.class})
public interface ChatMapper extends MapperInterface<ChatDto, Chat> {

    @Override
    @Mapping(target = "membersId", source = ".", qualifiedByName = "toListMembers")
    ChatDto toDto(Chat entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("toListMembers")
    public static Set<Long> tolistMembers(Chat chat) {
        return Set.of(chat.getRecipient().getId(),
                chat.getSender().getId());
    }

}
