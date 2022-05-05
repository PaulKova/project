package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {ShopMapper.class, ItemMapper.class})
public interface FavoriteMapper extends MapperInterface<FavoriteDto, Favorite> {

    @Mapping(target = "userId", source = "entity.user.id")
    @Override
    FavoriteDto toDto(Favorite entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
