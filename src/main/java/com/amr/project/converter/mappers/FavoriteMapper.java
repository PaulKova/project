package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import org.mapstruct.*;

import java.util.List;


@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {ShopMapper.class, ItemMapper.class})
public interface FavoriteMapper extends MapperInterface<FavoriteDto, Favorite> {

    @Mapping(target = "userId", source = "entity.user.id")
    @Override
    FavoriteDto toDto(Favorite entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    List<FavoriteDto> toDtoList(List<Favorite> listEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    default FavoriteDto map(Favorite entity) {
        return toDto(entity, new CycleAvoidingMappingContext());
    }
}
