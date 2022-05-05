package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {CountryMapper.class, ImageMapper.class,
        UserMapper.class, CartItemMapper.class, CouponMapper.class, ItemMapper.class, CityMapper.class,
        AddressMapper.class, FeedbackMapper.class, DiscountMapper.class, FavoriteMapper.class, ReviewMapper.class})
public interface ShopMapper extends MapperInterface<ShopDto, Shop> {

    @Override
    @Mappings({
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "logo", source = "entity.logo"),
            @Mapping(target = "couponIds", source = "entity.coupons", qualifiedByName = "couponToIds")
    })
    ShopDto toDto(Shop entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("couponToIds")
    public static Long couponToIds(Coupon coupon) {
        return coupon.getId();
    }

    @Override
    @Mappings({
            @Mapping(target = "user.id", source = "dto.userId")
    })
    Shop toEntity(ShopDto dto, CycleAvoidingMappingContext cycleAvoidingMappingContext);

//    @Override
//    @Mappings({
//            @Mapping(target = "userId", source = "entity.user.id"),
//            @Mapping(target = "couponIds", source = "entity.coupons", qualifiedByName = "couponToIds")
//    })
//    List<ShopDto> toDtoList(List<Shop> listEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
//
//    @Override
//    List<Shop> toEntityList(List<ShopDto> listDtos, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}