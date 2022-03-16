package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, ImageMapper.class,
        UserMapper.class, CartItemMapper.class, CouponMapper.class, ItemMapper.class,
        AddressMapper.class, FeedbackMapper.class, DiscountMapper.class, FavoriteMapper.class, ReviewMapper.class})
public interface ShopMapper extends MapperInterface<ShopDto, Shop> {

}
