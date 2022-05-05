package com.amr.project.converter.mappers;



import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {CityMapper.class,
        UserMapper.class, ShopMapper.class, OrderMapper.class, CountryMapper.class})
public interface AddressMapper extends MapperInterface<AddressDto, Address> {

    @Override
    @Mappings({
            @Mapping(target = "city", source = "entity.city.name"),
            @Mapping(target = "cityId", source = "entity.city.id"),
            @Mapping(target = "country", source = "entity.city.country.name"),
            @Mapping(target = "countryId", source = "entity.city.country.id")
    })
    AddressDto toDto(Address entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Override
    Address toEntity(AddressDto dto, CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
