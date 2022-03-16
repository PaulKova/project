package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShopMapper.class, UserMapper.class, ItemMapper.class})
public interface FavoriteMapper extends MapperInterface<FavoriteDto, Favorite> {

}
