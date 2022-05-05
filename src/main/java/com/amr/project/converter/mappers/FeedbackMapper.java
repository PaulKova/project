package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Feedback;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {ShopMapper.class, UserMapper.class})
public interface FeedbackMapper extends MapperInterface<FeedbackDto, Feedback> {

    @Override
    @Mappings({
            @Mapping(target = "userId", source = "entity.user.id")
    })
    FeedbackDto toDto(Feedback entity,CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    @Mappings({
            @Mapping(target = "user.id", source = "dto.userId")
    })
    Feedback toEntity(FeedbackDto dto, CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
