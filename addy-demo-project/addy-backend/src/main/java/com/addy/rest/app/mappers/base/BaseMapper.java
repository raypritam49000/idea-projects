package com.addy.rest.app.mappers.base;

import java.util.List;
import java.util.stream.Stream;

public interface BaseMapper<T, E> {
    T toDto(E entity);

    E toEntity(T dto);

    List<T> toDtoList(List<E> entities);

    List<E> toEntityList(List<T> entities);

    default Stream<T> toDtoStream(Stream<E> streamedEntities) {
        return streamedEntities.map(this::toDto);
    }

}
