package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IGalleryDAO;
import org.georg.web.impl.model.Gallery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GalleryDAO extends GenericDAO<Gallery, Long> implements IGalleryDAO {

    @Autowired
    public org.hibernate.SessionFactory sessionFactory;

    @Override
    public Gallery findByTitle(String name) {
        Criteria query = getSessionFactory().getCurrentSession().createCriteria(Gallery.class).add(Restrictions.eq("title", name));

        List<Gallery> result = query.list();
        if (result.isEmpty()) {
            return null;
        } else return result.get(0);
    }

    @Override
    public Gallery getByCode(String code) {
        Criteria query = getSessionFactory().getCurrentSession().createCriteria(Gallery.class).add(Restrictions.eq("password", code));

        Gallery result = (Gallery) query.uniqueResult();
        return result;
    }

    public GalleryDAO() {
        super(Gallery.class);
    }
}
