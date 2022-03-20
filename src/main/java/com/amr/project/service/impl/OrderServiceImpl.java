package com.amr.project.service.impl;

import com.amr.project.converter.mappers.OrderMapper;
import com.amr.project.dao.OrderRepository;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order1;
import com.amr.project.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderMapper.toDto(orderRepository.getById(id));
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        Order1 order1 = orderMapper.toEntity(orderDto);
        orderRepository.saveAndFlush(order1);
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Order1 order1 = orderMapper.toEntity(orderDto);
        orderRepository.saveAndFlush(order1);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
