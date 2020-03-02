package com.pingpong.app.dtos;

import static org.springframework.beans.BeanUtils.copyProperties;


public abstract class BaseDto<D, E> {

    public E toEntity(Class<E> entity) {
        try {
            var instant = entity.getConstructor().newInstance();
            copyProperties(this, instant);
            return instant;
        } catch (Exception e) {
            throw new RuntimeException("[EntityUtils::toEntity] -> Error mapping dto to entity.", e);
        }
    }

    public D toDto(E instant) {
        try {
            copyProperties(instant, this);
            return (D) this;
        } catch (Exception ex) {
            throw new RuntimeException("[EntityUtils::toDto] -> Error mapping entity to dto.", ex);
        }
    }
}
