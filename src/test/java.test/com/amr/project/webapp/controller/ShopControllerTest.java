package com.amr.project.webapp.controller;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.exception.ShopAlreadyExistingException;
import com.amr.project.exception.ShopNotFoundException;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopMapper shopMapper;

    @MockBean
    private ShopService shopService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ShopDto shopDto1, shopDto2, shopDto3, shopDto4;

    @Before
    public void setUp() throws Exception {
        shopDto1 = ShopDto.builder()
                .id(1L)
                .rating(1)
                .email("aslan@mail.ru")
                .description("blabla")
                .name("Armani")
                .build();
        shopDto2 = ShopDto.builder()
                .id(2L)
                .rating(1)
                .email("alexey@mail.ru")
                .description("blabla2")
                .name("Versace")
                .build();
        shopDto3 = ShopDto.builder()
                .id(3L)
                .rating(1)
                .email("ali@mail.ru")
                .description("blabla3")
                .name("Dolge and Gabbana")
                .build();
        shopDto4 = ShopDto.builder()
                .id(4L)
                .rating(2)
                .email("alex@mail.ru")
                .description("blabla4")
                .name("Louis Vutton")
                .build();
    }

    @Test
    public void givenShops_whenGetShops_thenReturnJsonArray() throws Exception {
        List<ShopDto> shopList = List.of(shopDto1, shopDto2, shopDto3);

        when(shopService.findExistsShops()).thenReturn(shopList);

        mockMvc.perform(get("/api/shops")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(shopDto1.getName())))
                .andExpect(jsonPath("$[1].name", is(shopDto2.getName())))
                .andExpect(jsonPath("$[2].name", is(shopDto3.getName())));
    }

    @Test
    public void givenShop_whenGetShop_thenReturnJson() throws Exception {
        when(shopService.getShopById(1L)).thenReturn(shopDto1);

        mockMvc.perform(get("/api/shops/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(shopDto1.getName())))
                .andExpect(jsonPath("$.email", is(shopDto1.getEmail())))
                .andExpect(jsonPath("$.rating", is(shopDto1.getRating())))
                .andExpect(jsonPath("$.description", is(shopDto1.getDescription())));
    }

    @Test
    public void givenFourShops_whenGetFirstFourShopsByRating_thenReturnJsonArrayOfSizeFour() throws Exception {
        List<ShopDto> shopDtoList = List.of(shopDto1, shopDto2, shopDto3, shopDto4);

        when(shopService.findFirst4ByOrderByRatingDesc()).thenReturn(shopDtoList);

        mockMvc.perform(get("/api/shops/top"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is(shopDto1.getName())))
                .andExpect(jsonPath("$[1].name", is(shopDto2.getName())))
                .andExpect(jsonPath("$[2].name", is(shopDto3.getName())))
                .andExpect(jsonPath("$[3].name", is(shopDto4.getName())));
    }

    @Test
    public void whenRequestCreateShop_thenReturnCreatedStatus() throws Exception {
        shopDto1.setModerateAccept(true);
        shopDto1.setId(null);

        mockMvc.perform(post("/api/admin/create/shop")
                .with(user("admin").roles("ADMIN"))
                .content(objectMapper.writeValueAsString(shopDto1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(shopService).saveShop(shopDto1);
    }

    @Test
    public void whenAdminRequestCreateShop_thenReturnBadRequestStatus() throws Exception {
        shopDto1.setId(null);
        shopDto1.setModerateAccept(true);

        doThrow(new ShopAlreadyExistingException("asd")).when(shopService).saveShop(shopDto1);

        MockHttpServletResponse response = mockMvc.perform(
                post("/api/admin/create/shop")
                    .with(user("admin").roles("ADMIN"))
                    .content(objectMapper.writeValueAsString(shopDto1))
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        verify(shopService).saveShop(shopDto1);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenAdminEditShop_thenReturnOkStatus() throws Exception {
        shopDto1.setId(null);

        MockHttpServletResponse response = mockMvc.perform(
                put("/api/shop/update")
                    .with(user("admin").roles("ADMIN"))
                    .content(objectMapper.writeValueAsString(shopDto1))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(shopService).updateShopById(shopDto1);
    }

    @Test
    public void whenAdminDeleteShop_thenReturnOkStatus() throws Exception {
        mockMvc.perform(
                delete("/api/admin/delete/shop/{id}", 1)
                    .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());

        verify(shopService).deleteShopById(1L);
    }

    @Test
    public void whenAdminDeleteShop_thenReturnBadRequestStatus() throws Exception {
        //given
        doThrow(new ShopNotFoundException()).when(shopService).deleteShopById(5L);

        //when
        mockMvc.perform(
                delete("/api/admin/delete/shop/{id}", 5)
                    .with(user("admin").roles("ADMIN")))
                .andExpect(status().isBadRequest());

        //then
        verify(shopService).deleteShopById(5L);
    }
}