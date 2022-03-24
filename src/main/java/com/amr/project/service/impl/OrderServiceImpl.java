package com.amr.project.service.impl;

import com.amr.project.converter.mappers.OrderMapper;
import com.amr.project.dao.OrderRepository;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.StatusDto;
import com.amr.project.model.entity.Order;
import com.amr.project.service.MailSender;
import com.amr.project.service.abstracts.OrderService;
import com.amr.project.util.EmailOrderAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final MailSender mailSender;
    private final EmailOrderAssistant emailAssistantService;


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
        /////
        mailSender.send(emailAssistantService.trackerEmailOrderSave(order));
        /////
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto, new CycleAvoidingMappingContext());
        /////
        mailSender.send(emailAssistantService.trackerEmailOrderUpdate(order));
        ////
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderMapper.toEntity(getOrderById(id), new CycleAvoidingMappingContext());
        /////
        mailSender.send(emailAssistantService.trackerEmailOrderDelete(order));
        /////
        orderRepository.deleteById(id);
    }

    @Override
    public void changeStatusToStart(Long order_id) {
        OrderDto order = orderMapper.toDto(orderRepository.getById(order_id), new CycleAvoidingMappingContext());
        order.setStatus(StatusDto.START);
        updateOrder(order);
    }

    @Override
    public void changeStatusToComplete(Long order_id) {
        OrderDto order = orderMapper.toDto(orderRepository.getById(order_id), new CycleAvoidingMappingContext());
        order.setStatus(StatusDto.COMPLETE);
        updateOrder(order);
    }

    @Override
    public void changeStatusToWaiting(Long order_id) {
        OrderDto order = orderMapper.toDto(orderRepository.getById(order_id), new CycleAvoidingMappingContext());
        order.setStatus(StatusDto.WAITING);
        updateOrder(order);
    }

    @Override
    public void changeStatusToPaid(Long order_id) {
        OrderDto order = orderMapper.toDto(orderRepository.getById(order_id), new CycleAvoidingMappingContext());
        order.setStatus(StatusDto.PAID);
        updateOrder(order);
    }

    @Override
    public void changeStatusToSent(Long order_id) {
        OrderDto order = orderMapper.toDto(orderRepository.getById(order_id), new CycleAvoidingMappingContext());
        order.setStatus(StatusDto.SENT);
        updateOrder(order);
    }

    @Override
    public void changeStatusToDelivered(Long order_id) {
        OrderDto order = orderMapper.toDto(orderRepository.getById(order_id), new CycleAvoidingMappingContext());
        order.setStatus(StatusDto.DELIVERED);
        updateOrder(order);
    }
}
