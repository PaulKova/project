package com.amr.project.converter.mappers;



import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {CityMapper.class,
        UserMapper.class, ShopMapper.class, OrderMapper.class})
public interface AddressMapper extends MapperInterface<AddressDto, Address> {

}
