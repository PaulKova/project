package com.amr.project.converter;

import java.util.List;

import org.mapstruct.Context;



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
