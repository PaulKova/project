package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CartItemDto;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    List<CartItemDto> getAllCartItems();

    Optional<CartItemDto> getCartItemsById(Long id);

    void saveCartItem(CartItemDto cartItemDto);

    void updateCartItem(CartItemDto cartItemDto);

    void deleteCartItem(Long id);
}
