package com.amr.project.webapp.controller;


import com.alibaba.fastjson.JSON;
import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.enums.PersonalDataStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
 class PersonalDataControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(1)
    void getAllPersonalDataWithParam() throws Exception {
        this.mockMvc.perform(get("/api/admin/personalData"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234567890")));
    }


    @Test()
//    @Order(2)
    void addNewPersonalData() throws Exception {
        PersonalDataDto personalDataDto = PersonalDataDto.builder()
                .id(3L)
                .passport(1234567)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД С. Петербург")
                .placeOfBirth("С. Петербург")
                .listOfImages(null)
                .status(PersonalDataStatus.WAITING)
                .build();

        this.mockMvc.perform(post("/api/personalData")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(personalDataDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test()
//    @Order(3)
    void updatePersonalData() throws Exception {
        PersonalDataDto personalDataDto = PersonalDataDto.builder()
                .id(3L)
                .passport(1234567)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД г. С. Петербург")
                .placeOfBirth("г. С. Петербург")
                .listOfImages(null)
                .status(PersonalDataStatus.WAITING)
                .build();

        this.mockMvc.perform(put("/api/personalData")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(personalDataDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test()
//    @Order(4)
    void updateStatus() throws Exception {
        PersonalDataDto personalDataDto = PersonalDataDto.builder()
                .id(1L)
                .passport(1234567890)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД Москвы")
                .placeOfBirth("Москва")
                .listOfImages(null)
                .status(PersonalDataStatus.WAITING)
                .build();

        this.mockMvc.perform(patch("/api/admin/personalData/1?Status=REJECTED&Comment=hi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(personalDataDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test()
//    @Order(5)
    void getPersonalDataById() throws Exception {
        PersonalDataDto personalDataDto = PersonalDataDto.builder()
                .id(1L)
                .passport(1234567890)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД Москвы")
                .placeOfBirth("Москва")
                .listOfImages(null)
                .status(PersonalDataStatus.WAITING)
                .build();

        this.mockMvc.perform(get("/api/personalData/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(personalDataDto)))
                .andDo((print()))
                .andExpect(status().isOk());
    }

    @Test
//    @Order(6)
    void deletePersonalData() throws Exception {
        long id = 3L;
        this.mockMvc.perform(delete("/api/admin/personalData/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
