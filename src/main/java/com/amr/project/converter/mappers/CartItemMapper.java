package com.amr.project.converter.mappers;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ShopMapper.class, ItemMapper.class})
public interface CartItemMapper {

    CartItemDto toDto(CartItem cartItem);

    CartItem toEntity(CartItemDto cartItemDto);

    List<CartItemDto> toDtoList(List<CartItem> cartItems);

    List<CartItem> toEntityList(List<CartItemDto> cartItemDtoS);
}
