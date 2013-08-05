package org.georg.web.impl.dao.custom.base;

import org.georg.web.impl.dao.base.IGenericDAO;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.model.User;

import java.util.List;

public interface IUserDetailsServiceDAO extends IGenericDAO<User, String> {
    List<Gallery> getAvaliableGalleries(User user);

    void addPrivateUser(String code);
}
