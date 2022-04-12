package com.amr.project.webapp.controller;

import com.alibaba.fastjson.JSON;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.StatusDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCategories() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/orders/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void getOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(2L)
                .build();
        this.mockMvc.perform(get("/api/orders/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(orderDto)))
                .andDo((print()))
                .andExpect(status().isOk());

    }

    @Test
    void createOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .status(StatusDto.START)
                .build();

        this.mockMvc.perform(post("/api/orders/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(orderDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test
    void editOrder() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .build();
        this.mockMvc.perform(put("/api/orders/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(orderDto)))
                .andDo((print()))
                .andExpect(status().isOk());

    }

    @Test
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

