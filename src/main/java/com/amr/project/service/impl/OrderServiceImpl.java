package com.amr.project.service.impl;

import com.amr.project.converter.mappers.OrderMapper;
import com.amr.project.dao.OrderRepository;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import com.amr.project.service.MailSender;
import com.amr.project.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderMapper.toDto(orderRepository.getById(id), new CycleAvoidingMappingContext());
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto, new CycleAvoidingMappingContext());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto, new CycleAvoidingMappingContext());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
