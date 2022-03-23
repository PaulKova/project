package com.amr.project.util;

import com.amr.project.dao.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailOrderAssistant {

    private OrderRepository orderRepository;



    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
}
