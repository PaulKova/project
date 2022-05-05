package com.amr.project.mapsTest;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopMapperUnitTest {

    private ShopMapper shm = Mappers.getMapper(ShopMapper.class);

    @Test
    public void givenShopDtoToEntityWhenMapIsCorrect() {

        ShopDto dto = ShopDto.builder()
                .id(1L)
                .description("description")
                .name("Vasya")
                .email("tets@mail.com")
                .build();

        Shop entity = shm.toEntity(dto, new CycleAvoidingMappingContext());

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getEmail(), entity.getEmail());
    }
}