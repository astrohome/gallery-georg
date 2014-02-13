package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Gallery;

public interface IGalleryDAO extends IGenericDAO<Gallery, Integer> {
    Gallery findByTitle(String name);

    Gallery getByCode(String code);
}
