package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.dto.CouponDto;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.Coupon;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {UserMapper.class, ShopMapper.class})
public interface CouponMapper extends MapperInterface<CouponDto, Coupon> {
    @Mappings({
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "shopId", source = "entity.shop.id")
    })
    @Override
    CouponDto toDto(Coupon entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    List<CouponDto> toDtoList(List<Coupon> listEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    default CouponDto map(Coupon entity) {
        return toDto(entity, new CycleAvoidingMappingContext());
    }
}
