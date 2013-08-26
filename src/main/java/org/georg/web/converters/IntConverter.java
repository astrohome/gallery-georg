package org.georg.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IntConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String value) {
        if (value == null || value.isEmpty()) return null;
        return Integer.valueOf(value);
    }
}
