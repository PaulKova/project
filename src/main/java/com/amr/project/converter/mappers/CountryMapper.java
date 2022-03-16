package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class, ShopMapper.class})
public interface CountryMapper extends MapperInterface<CountryDto, Country> {

}
