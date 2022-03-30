package com.amr.project.webapp.controller;

import com.alibaba.fastjson.JSON;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource("testApplication.properties")
//@Sql()
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*@Autowired
    private ShopController controller;*/

    /*@Test
    void firstTest() throws Exception{
        assertThat(controller).isNotNull();
    }*/



    /*@BeforeEach
    void setUp() {
    }*/

    @Test
    void userMarkShopToCreate() throws Exception {
        ShopDto shopDto = ShopDto.builder()
                .name("Ромашка")
                .email("rom@mail.ru")
                .description("Магазин продуктов")
                .isModerated(false)
                .build();

        this.mockMvc.perform(post("/api/shops/request/to_create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(shopDto)))
                .andDo((print()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ромашка")));
    }


    @Test
    void getAllShops() throws Exception {
        this.mockMvc.perform(get("/api/shops"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ромашка")));
    }

    @Test
    void getShop() {


    }
}