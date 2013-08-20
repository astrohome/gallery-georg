package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IUserDAO;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.model.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends GenericDAO<User, String> implements IUserDAO {

    @Override
    public List<Gallery> getAvaliableGalleries(User user) {
        return null;
    }

    @Override
    public User getByActivationCode(String code) {
        return (User) getSessionFactory().getCurrentSession().createCriteria(User.class).add(Restrictions.eq("activationCode", code)).uniqueResult();
    }

    public UserDAO() {
        super(User.class);
    }
}
