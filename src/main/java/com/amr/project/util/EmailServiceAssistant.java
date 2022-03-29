package com.amr.project.util;

import com.amr.project.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceAssistant {

    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private ShopRepository shopRepository;

    public ShopRepository getShopRepository() {
        return shopRepository;
    }
    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
    public ItemRepository getItemRepository() {
        return itemRepository;
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
