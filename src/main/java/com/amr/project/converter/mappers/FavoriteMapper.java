package com.amr.project.converter.mappers;

import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShopMapper.class, UserMapper.class, ItemMapper.class})
public interface FavoriteMapper { ;

    FavoriteDto toDto(Favorite favorite);

    Favorite toEntity(FavoriteDto favoriteDto);

    List<Favorite> toEntityList(List<FavoriteDto> dtos);

    List<FavoriteDto> toDtoList(List<Favorite> entities);
}
