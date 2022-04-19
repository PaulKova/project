package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ShopRepository;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceImplTest {

    @Mock private ShopRepository shopRepository;
    @Mock private ShopMapper shopMapper;

    @InjectMocks
    private ShopServiceImpl shopService;


    @Test
    public void canGetAllShops() {
        //given
        List<Shop> shopList = List.of(
                Shop.builder().id(1L).name("Armani").build(),
                Shop.builder().id(2L).name("Gucci").build(),
                Shop.builder().id(3L).name("Versace").build()
        );
        List<ShopDto> shopDtoList = List.of(
                ShopDto.builder().id(1L).name("Armani").build(),
                ShopDto.builder().id(2L).name("Gucci").build(),
                ShopDto.builder().id(3L).name("Versace").build()
        );
        given(shopRepository.findAll()).willReturn(shopList);
        given(shopMapper.toDtoList(eq(shopList),any())).willReturn(shopDtoList);

        //when
        List<ShopDto> actual = shopService.getAllShops();

        //then
        assertThat(actual, is(shopDtoList));

    }
}