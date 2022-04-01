package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.dao.ShopRepository;
import com.amr.project.dao.report.SalesHistoryRepository;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailShopAssistant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
class ShopServiceImplTest {

    @Mock
    private ShopRepository shopRepository;
    @Mock
    private SalesHistoryRepository salesHistoryRepository;
    @Mock
    private ShopMapper shopMapper;
    @Mock
    private EmailShopAssistant emailShopAssistant;
    @Mock
    private MailSender mailSender;



    private final ShopService shopService;


    public ShopServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.shopService = new ShopServiceImpl(shopRepository, salesHistoryRepository, shopMapper, emailShopAssistant, mailSender);
    }

    @Test
    void getAllShops() {

        Shop shop1 = Shop.builder()
                .id(1L)
                .name("Ромашка")
                .build();
        Shop shop2 = Shop.builder()
                .id(2L)
                .name("Апельсин")
                .build();
        List<Shop> shops = new ArrayList<>(List.of(shop1, shop2));

        ShopDto shopDto1 = ShopDto.builder()
                .id(1L)
                .name("Ромашка")
                .build();
        ShopDto shopDto2 = ShopDto.builder()
                .id(2L)
                .name("Апельсин")
                .build();
        List<ShopDto> shopsDto = new ArrayList<>(List.of(shopDto1, shopDto2));

        given(shopRepository.findAll())
                .willReturn(shops);

        given(shopMapper.toDtoList(eq(shops), any(CycleAvoidingMappingContext.class)))
                .willReturn(shopsDto);


        assertThat(shopService.getAllShops()).containsExactlyInAnyOrderElementsOf(new ArrayList<>(List.of(shopDto1, shopDto2)));
        assertThat(shopService.getAllShops()).contains(shopDto2);
    }

    @Test
    void getShopById() throws NullPointerException{


        Shop shop1 = Shop.builder()
                .id(1L)
                .name("Ромашка")
                .build();
        Shop shop2 = Shop.builder()
                .id(2L)
                .name("Апельсин")
                .build();



        ShopDto shopDto1 = ShopDto.builder()
                .id(1L)
                .name("Ромашка")
                .build();
        ShopDto shopDto2 = ShopDto.builder()
                .id(2L)
                .name("Апельсин")
                .build();


        given(shopRepository.findById(1L))
                .willReturn(Optional.of(shop1));

        given(shopRepository.findById(2L))
                .willReturn(Optional.of(shop2));


        given(shopMapper.toDto(eq(shop1), any(CycleAvoidingMappingContext.class)))
                .willReturn(shopDto1);

        given(shopMapper.toDto(eq(shop2), any(CycleAvoidingMappingContext.class)))
                .willReturn(shopDto2);

        assertThat(shopService.getShopById(1L)).isEqualTo(shopDto1);
        assertThat(shopService.getShopById(2L)).isEqualTo(shopDto2);
    }
}