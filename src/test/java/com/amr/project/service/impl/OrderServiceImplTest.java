package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.OrderMapper;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.OrderRepository;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.StatusDto;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.enums.Status;
import com.amr.project.service.abstracts.OrderService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailOrderAssistant;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private EmailOrderAssistant emailOrderAssistant;
    @Mock
    private MailSender mailSender;
    @Mock
    private OrderMapper orderMapper;


    public OrderServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.orderService = new OrderServiceImpl(orderRepository, orderMapper, mailSender, emailOrderAssistant);
    }

    @Test
    void getAllOrders() {
        Order order1 = Order.builder()
                .id(1L)
                .status(Status.START)
                .build();
        Order order2 = Order.builder()
                .id(2L)
                .status(Status.WAITING)
                .build();
        List<Order> orders = new ArrayList<>(List.of(order1, order2));

        OrderDto orderDto1 = OrderDto.builder()
                .id(1L)
                .status(StatusDto.START)
                .build();
        OrderDto orderDto2 = OrderDto.builder()
                .id(2L)
                .status(StatusDto.WAITING)
                .build();
        List<OrderDto> orderDto = new ArrayList<>(List.of(orderDto1, orderDto2));

        given(orderRepository.findAll())
                .willReturn(orders);

        given(orderMapper.toDtoList(eq(orders), any(CycleAvoidingMappingContext.class)))
                .willReturn(orderDto);


        assertThat(orderService.getAllOrders()).containsExactlyInAnyOrderElementsOf(new ArrayList<>(List.of(orderDto1, orderDto2)));
        assertThat(orderService.getAllOrders()).contains(orderDto2);
    }

    @Test
    void getOrderById() throws NullPointerException {
        Order order1 = Order.builder()
                .id(1L)
                .build();
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();

        given(orderRepository.getById(1L))
                .willReturn(order1);
        given(orderMapper.toDto(eq(order1), any(CycleAvoidingMappingContext.class)))
                .willReturn(orderDto);

        assertThat(orderService.getOrderById(1L)).isEqualTo(orderDto);
    }

    @Test
    void saveOrder() {
        Order order = Order.builder()
                .id(1L)
                .build();
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(1L);
//        orderService.saveOrder(orderDto);
//        Mockito.verify(orderRepository,Mockito.times(1)).saveAndFlush(order1);
        given(orderRepository.saveAndFlush(any(Order.class)))
                .willReturn(order);
        given(orderMapper.toEntity(orderDto, new CycleAvoidingMappingContext()))
                .willReturn(order);

        Mockito.verify(orderRepository, times(1)).save(order);

    }

    @Test
    void updateOrder() throws NullPointerException{
        Order order = new Order();
        order.setId(1L);
        order.setCurrency("100");
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();
        given(orderRepository.saveAndFlush(any(Order.class)))
                .willReturn(order);
        given(orderMapper.toDto(eq(order), any(CycleAvoidingMappingContext.class)))
                .willReturn(orderDto);
        orderService.updateOrder(orderMapper.toDto(eq(order), any(CycleAvoidingMappingContext.class)));
        verify(orderRepository, times(1)).saveAndFlush(order);
    }

    @Test
    void deleteOrder() {
        Order order = new Order();
        order.setId(1L);
        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);

    }


    @Test
    void changeStatusToStart() {
    }

    @Test
    void changeStatusToComplete() {
    }

    @Test
    void changeStatusToWaiting() {
    }

    @Test
    void changeStatusToPaid() {
    }

    @Test
    void changeStatusToSent() {
    }

    @Test
    void changeStatusToDelivered() {
    }
}
