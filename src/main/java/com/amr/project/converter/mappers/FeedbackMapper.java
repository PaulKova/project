package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Feedback;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {ShopMapper.class, UserMapper.class})
public interface FeedbackMapper extends MapperInterface<FeedbackDto, Feedback> {

}
