package com.amr.project.converter;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.impl.ShopServiceImpl;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public interface ShopMapper {
    ShopDto toDto(Shop shop);
    Shop toShop(ShopDto shopDto);
    List<ShopDto> toListDto(List<Shop> shopList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShopFromDto(ShopDto shopDto, @MappingTarget Shop shop);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCountryFromDto(CountryDto CountryDto, @MappingTarget Country country);

    CountryDto map(Country country);

    Country map(CountryDto countryDto);
}
