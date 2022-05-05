package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.CouponDto;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Discount;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = ShopMapper.class)
public interface DiscountMapper extends MapperInterface<DiscountDto, Discount> {
    @Mappings({
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "shopId", source = "entity.shop.id")
    })
    @Override
    DiscountDto toDto(Discount entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    List<DiscountDto> toDtoList(List<Discount> listEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    default DiscountDto map(Discount entity) {
        return toDto(entity, new CycleAvoidingMappingContext());
    }
}
