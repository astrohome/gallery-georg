package org.georg.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FloatConverter implements Converter<String, Float> {
    @Override
    public Float convert(String s) {
        if (s == null || s.isEmpty()) return null;

        return Float.valueOf(s);
    }
}
