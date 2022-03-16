package com.amr.project.converter.mappers;

import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = ShopMapper.class)
public interface DiscountMapper {

    DiscountDto toDto(Discount discount);

    Discount toEntity(DiscountDto discountDto);

    List<DiscountDto> toDtoList(List<Discount> discounts);

    List<Discount> toEntityList(List<DiscountDto> discountDtoS);
}
