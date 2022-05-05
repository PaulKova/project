package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Message;
import com.amr.project.model.entity.Review;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {UserMapper.class, ShopMapper.class, ItemMapper.class})
public interface ReviewMapper extends MapperInterface<ReviewDto, Review> {

    @Override
    @Mappings({
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "itemId", source = "entity.item.id"),
            @Mapping(target = "shopId", source = "entity.shop.id"),
            @Mapping(target = "userName", source = "entity.user.username")
    })
    ReviewDto toDto(Review entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);


//    @Override
//    @Mappings({
//            @Mapping(target = "user.id", source = "dto.userId"),
//            @Mapping(target = "item.id", source = "dto.itemId"),
//            @Mapping(target = "shop.id", source = "dto.shopId"),
//            @Mapping(target = "user.username", source = "dto.userName")
//    })
//    Review toEntity(ReviewDto dto, CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
