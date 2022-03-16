package com.amr.project.converter;

import java.util.List;

public interface MapperInterface<D, E> {

    D toDto(E entity);
    E toEntity(D dto);
    List<D> toDtoList(List<E> listEntities);
    List<E> toEntityList(List<D> listDtos);
}
