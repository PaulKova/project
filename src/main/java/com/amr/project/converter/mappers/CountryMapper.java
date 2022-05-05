package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.Country;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {CityMapper.class, ShopMapper.class})
public interface CountryMapper extends MapperInterface<CountryDto, Country> {

    Country map(String value);
    String map(Country value);
//    Country map(String value);
//    String map(Country value);

}
