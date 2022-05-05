package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {CategoryMapper.class, OrderMapper.class,
        ShopMapper.class, FavoriteMapper.class, ImageMapper.class, ReviewMapper.class,
        UserMapper.class})
public interface ItemMapper extends MapperInterface<ItemDto, Item> {

    @Override
    List <ItemDto> toDtoList (List<com.amr.project.model.entity.Item> listEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
//
//    @Override
//    List<Item> toEntityList(List<ItemDto> listDtos, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
