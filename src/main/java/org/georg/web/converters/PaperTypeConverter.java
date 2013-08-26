package org.georg.web.converters;

import org.georg.web.impl.model.PaperType;
import org.georg.web.impl.service.PaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class PaperTypeConverter implements Converter<String, PaperType> {

    @Autowired
    private PaperTypeService paperTypeService;

    public PaperTypeConverter() {
        super();
    }

    @Override
    public PaperType convert(String value) {
        if (value != null && value.length() > 0) {
            Integer id = Integer.valueOf(value);
            return this.paperTypeService.getById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
}