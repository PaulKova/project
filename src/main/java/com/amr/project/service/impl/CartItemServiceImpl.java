package com.amr.project.service.impl;

import com.amr.project.converter.mappers.CartItemMapper;
import com.amr.project.dao.CartItemRepository;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {


    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemDto> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItemMapper.toDtoList(cartItems);
    }

    @Override
    public CartItemDto getCartItemsById(Long id) {
        CartItem cartItem = cartItemRepository.getById(id);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public void saveCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
        cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    public void updateCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
        cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
