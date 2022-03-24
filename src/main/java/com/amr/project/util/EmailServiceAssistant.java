package com.amr.project.util;

import com.amr.project.dao.CategoryRepository;
import com.amr.project.dao.ItemRepository;
import com.amr.project.dao.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceAssistant {

    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
    public ItemRepository getItemRepository() {
        return itemRepository;
    }

}
