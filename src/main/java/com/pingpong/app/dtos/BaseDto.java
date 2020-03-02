package com.pingpong.app.dtos;

import org.springframework.beans.BeanUtils;

public abstract class BaseDto<T> {

    public T toEntity(Class<T> entity) {
        try {
            var dest = entity.getConstructor().newInstance();
            BeanUtils.copyProperties(this, dest);
            return dest;
        } catch (Exception e) {
            throw new RuntimeException("[EntityUtils::toEntity] -> Error mapping dto to entity.", e);
        }
    }
}
