package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = ShopMapper.class)
public interface DiscountMapper extends MapperInterface<DiscountDto, Discount> {

}
