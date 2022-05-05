package com.amr.project.converter.mappers;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = { AddressMapper.class})
public interface OrderMapper extends MapperInterface<OrderDto, Order> {

    @Override
    @Mappings({
            @Mapping(target = "date", source = "entity.orderDate"),
            @Mapping(target = "total", source = "entity.grandTotal"),
            @Mapping(target = "userId", source = "entity.user", qualifiedByName = "userToIds"),

    })
    OrderDto toDto(Order entity, CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("userToIds")
    public static Long userToIds(User user) {
        return user.getId();
    }
}

