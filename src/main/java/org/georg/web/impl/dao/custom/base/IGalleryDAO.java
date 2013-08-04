package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Gallery;

import java.util.List;

public interface IGalleryDAO extends IGenericDAO<Gallery, Long> {
    Gallery findByTitle(String name);

    List<Gallery> getByCode(String code);
}
