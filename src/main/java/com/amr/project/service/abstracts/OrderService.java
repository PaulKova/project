package com.amr.project.service.abstracts;

import com.amr.project.model.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    void saveOrder(OrderDto orderDto);

    void updateOrder(OrderDto orderDto);

    void deleteOrder(Long id);
}
