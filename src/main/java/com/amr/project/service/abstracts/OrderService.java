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

    void changeStatusToStart(Long order_id);

    void changeStatusToComplete(Long order_id);

    void changeStatusToWaiting(Long order_id);

    void changeStatusToPaid(Long order_id);

    void changeStatusToSent(Long order_id);

    void changeStatusToDelivered(Long order_id);

}
