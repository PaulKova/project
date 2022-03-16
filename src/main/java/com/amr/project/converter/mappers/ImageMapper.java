package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = ShopMapper.class)
public interface ImageMapper extends MapperInterface<ImageDto, Image> {

}
