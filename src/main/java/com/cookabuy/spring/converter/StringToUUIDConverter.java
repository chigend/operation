package com.cookabuy.spring.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-16-下午4:22
 */

public class StringToUUIDConverter implements Converter<String,UUID> {
    @Override
    public UUID convert(String source) {
        return UUID.fromString(source);
    }
}
