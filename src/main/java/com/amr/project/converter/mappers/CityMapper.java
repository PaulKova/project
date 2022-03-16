package com.amr.project.converter.mappers;

import com.amr.project.model.dto.CityDto;
import com.amr.project.model.entity.City;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.annotation.Generated;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CountryMapper.class})
public interface CityMapper {

    CityDto toDto(City city);

    City toEntity(CityDto cityDto);

    List<CityDto> toDtoList(List<City> cities);

    List<City> toEntityList(List<CityDto> cityDtoS);
}