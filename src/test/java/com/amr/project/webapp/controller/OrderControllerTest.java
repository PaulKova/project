package com.amr.project.webapp.controller;

import com.alibaba.fastjson.JSON;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.service.abstracts.OrderService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    @Order(1)
    void getAllCategories() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orders/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void createOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/orders/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.status().is(201));
        verify(orderService).saveOrder(any(OrderDto.class));
    }

    @Test
    @Order(3)
    void getOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(4L)
                //.currency("EURO")
                .build();
        when(orderService.getOrderById(4L)).thenReturn(orderDto);
        mockMvc.perform(get("/api/orders/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.currency", is(orderDto.getCurrency())));
    }

    @Test
    @Order(4)
    void editOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();
        this.mockMvc.perform(put("/api/orders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(orderDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();
        this.mockMvc.perform(delete("/api/orders/{id}", orderDto.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

