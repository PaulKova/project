package com.amr.project.webapp.controller;

import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopMapper shopMapper;

    @MockBean
    private ShopService shopService;


    @Test
    public void givenShops_whenGetShops_thenReturnJsonArray() throws Exception {
        ShopDto shopDto = ShopDto.builder()
                .id(1L)
                .rating(1)
                .email("aslan@mail.ru")
                .description("blabla")
                .name("Armani")
                .build();

        List<ShopDto> shopList = Collections.singletonList(shopDto);

        given(shopService.getAllShops()).willReturn(shopList);

        mockMvc.perform(get("/api/shops")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].name", is(shopDto.getName())));
    }
}