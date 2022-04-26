package com.amr.project.util;

import com.amr.project.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceAssistant {

    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private ShopRepository shopRepository;

    @Autowired
    public EmailServiceAssistant(OrderRepository orderRepository, CategoryRepository categoryRepository,
                                 ItemRepository itemRepository, UserRepository userRepository,
                                 ShopRepository shopRepository) {
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
    }

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
