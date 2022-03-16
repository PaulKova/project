package com.amr.project.converter.mappers;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class, ShopMapper.class})
public interface CountryMapper {

    CountryDto toDto(Country country);

    Country toEntity(CountryDto countryDto);

    List<CountryDto> toDtoList(List<Country> countries);

    List<Country> toEntityList(List<CountryDto> countryDtoS);
}
