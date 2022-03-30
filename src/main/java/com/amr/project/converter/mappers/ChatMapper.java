package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.entity.Chat;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

//TODO: выяснить, почему не происходит маппинга (для проверки см.сгенерированный файл "ChatMapperImpl"
@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring",
        uses = {MessageMapper.class, UserMapper.class})
public interface ChatMapper extends MapperInterface<ChatDto, Chat> {

}
