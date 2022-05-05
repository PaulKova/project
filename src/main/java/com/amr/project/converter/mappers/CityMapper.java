package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {AddressMapper.class, CountryMapper.class})
public interface CityMapper extends MapperInterface<CityDto, City> {

    @Override
    @Mappings({
            @Mapping(target = "countryName", source = "entity.country.name"),
            @Mapping(target = "countryId", source = "entity.country.id")
    })
    CityDto toDto(City entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    String map(City value);
    City map(String value);

    @Override
    @Mappings({
            @Mapping(target = "country.name", source = "dto.countryName"),
            @Mapping(target = "country.id", source = "dto.countryId")
    })
    City toEntity(CityDto dto, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    @Mappings({
            @Mapping(target = "countryName", source = "entity.country.name"),
            @Mapping(target = "countryId", source = "entity.country.id")
    })
    List<CityDto> toDtoList(List<com.amr.project.model.entity.City> listEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    @Mappings({
            @Mapping(target = "country.name", source = "dto.countryName"),
            @Mapping(target = "country.id", source = "dto.countryId")
    })
    List<City> toEntityList(List<CityDto> listDtos, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}