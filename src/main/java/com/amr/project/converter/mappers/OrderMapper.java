package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order1;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {UserMapper.class, ItemMapper.class, AddressMapper.class})
public interface OrderMapper extends MapperInterface<OrderDto, Order1> {


}

