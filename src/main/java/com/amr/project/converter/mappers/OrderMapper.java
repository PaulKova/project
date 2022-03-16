package com.amr.project.converter.mappers;

import ch.qos.logback.core.status.StatusManager;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ItemMapper.class, AddressMapper.class, StatusManager.class})
public interface OrderMapper extends MapperInterface<OrderDto, Order> {


}

