package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.CartItemMapper;
import com.amr.project.dao.CartItemRepository;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {


    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemDto> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItemMapper.toDtoList(cartItems, new CycleAvoidingMappingContext());
    }

    @Override
    public Optional<CartItemDto> getCartItemsById(Long id) {
        CartItem cartItem = cartItemRepository.getById(id);
        return Optional.of(cartItemMapper.toDto(cartItem, new CycleAvoidingMappingContext()));
    }

    @Override
    public void saveCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto, new CycleAvoidingMappingContext());
        cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    public void updateCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto, new CycleAvoidingMappingContext());
        cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
