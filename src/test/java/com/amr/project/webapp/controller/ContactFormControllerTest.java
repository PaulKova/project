package com.amr.project.webapp.controller;

import com.alibaba.fastjson.JSON;
import com.amr.project.model.dto.ContactFormDto;
import com.amr.project.model.dto.ContactFormDto;
import com.amr.project.model.entity.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ContactFormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void getAllContactForms() throws Exception {
        this.mockMvc.perform(get("/api/contactForms"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("suggestion")));
    }

    @Test()
    @Order(2)
    void addNewContactForm() throws Exception {
        ContactFormDto contactFormDto = ContactFormDto.builder()
                .id(5L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("I have no more questions")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        this.mockMvc.perform(post("/api/contactForms")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(contactFormDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void getContactFormById() throws Exception {
        ContactFormDto contactFormDto = ContactFormDto.builder()
                .id(5L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("I have no more questions")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        this.mockMvc.perform(get("/api/contactForms/5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(contactFormDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void updateNewContactForm() throws Exception {
        ContactFormDto contactFormDto = ContactFormDto.builder()
                .id(5L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("I have no more questions")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        this.mockMvc.perform(put("/api/contactForms")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(contactFormDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteContactFormById() throws Exception {
        long id = 5L;
        this.mockMvc.perform(delete("/api/admin/contactForms/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}