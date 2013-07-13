package org.georg.web.impl.dao;

import org.georg.web.impl.dao.base.IGalleryDAO;
import org.georg.web.impl.model.Gallery;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryDAO extends GenericDAO<Gallery, Long> implements IGalleryDAO {

    public GalleryDAO() {
        setClazz(Gallery.class);
    }
}
