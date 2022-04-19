package com.amr.project.webapp.controller;

import com.alibaba.fastjson.JSON;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.StatusDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Image;
import com.amr.project.service.abstracts.UserService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.hamcrest.Matchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @Order(1)
    void getAllUsers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("user1@mail.com")));
    }

    @Test
    @Order(2)
    void addNewUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(4L)
                .username("Paul")
                .build();
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/admin/create/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(userDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void getUserById() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("Paul")
                .build();
        when(userService.getUserById(1L)).thenReturn(userDto);
        mockMvc.perform(get("/api/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(userDto.getUsername())));
    }

    // не работает, настроить MultipartFile file
//    @Test
//    @Order(4)
//    void updateUser() throws Exception {
//        ImageDto imageDto1 = ImageDto.builder()
//                .id(1L)
//                .picture(null)
//                .build();
//        ImageDto imageDto2 = ImageDto.builder()
//                .id(1L)
//                .picture(null)
//                .build();
//        MultipartFile multipartFile=multipart("");
//        List<ImageDto> imageDtosList = List.of(imageDto1, imageDto2);
//        UserDto userDto = UserDto.builder()
//                .id(1L)
//                .images(imageDtosList)
//                .build();
//
//        this.mockMvc.perform(put("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JSON.toJSONString(userDto)))
//                .andDo((print()))
//                .andExpect(status().isOk());
//    }

    @Test
    @Order(5)
    void deleteOrder() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("Paul")
                .build();
        this.mockMvc.perform(delete("/api/admin/delete/user/{id}", userDto.getId())
                        .with(user(userDto.getUsername()).roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).deleteUserById(1L);
    }

    @Test
    @Order(6)
    void getPaidOrders() throws Exception {
        OrderDto orderDto1 = OrderDto.builder()
                .id(1L)
                .status(StatusDto.PAID)
                .build();
        OrderDto orderDto2 = OrderDto.builder()
                .id(2L)
                .status(StatusDto.PAID)
                .build();
        OrderDto orderDto3 = OrderDto.builder()
                .id(3L)
                .status(StatusDto.WAITING)
                .build();

        List<OrderDto> orderDtoList = List.of(orderDto1, orderDto2, orderDto3);

        UserDto userDto = UserDto.builder()
                .id(1L)
                .orders(orderDtoList)
                .build();

        when(userService.getUserById(1L)).thenReturn(userDto);
        mockMvc.perform(get("/api/users/{id}/my_paid_orders", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
