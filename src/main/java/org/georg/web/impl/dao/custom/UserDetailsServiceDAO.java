package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IUserDetailsServiceDAO;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsServiceDAO extends GenericDAO<User, String> implements IUserDetailsServiceDAO {

    @Autowired
    public org.hibernate.SessionFactory sessionFactory;


    @Override
    public List<Gallery> getAvaliableGalleries(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public UserDetailsServiceDAO() {
        super(User.class);
    }
}
