package com.amr.project.converter;

import java.util.List;

import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


public interface MapperInterface<D, E> {

    D toDto(E entity,
            @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    E toEntity(D dto,
               @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    List<D> toDtoList(List<E> listEntities,
                      @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    List<E> toEntityList(List<D> listDtos,
                         @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


}
