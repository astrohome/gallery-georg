package org.georg.web.converters;

import org.georg.web.impl.model.Format;
import org.georg.web.impl.service.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FormatConverter implements Converter<String, Format> {

    @Autowired
    private FormatService formatService;

    public FormatConverter() {
        super();
    }

    @Override
    public Format convert(String id) {
        if (id != null && !id.isEmpty()) {
            Integer i = Integer.valueOf(id);
            return this.formatService.getById(i);
        } else {
            return new Format();
        }
    }
}
