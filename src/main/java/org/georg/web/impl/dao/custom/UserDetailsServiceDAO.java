package org.georg.web.impl.dao.custom;

import org.georg.web.impl.dao.base.GenericDAO;
import org.georg.web.impl.dao.custom.base.IUserDetailsServiceDAO;
import org.georg.web.impl.model.Gallery;
import org.georg.web.impl.model.User;
import org.georg.web.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsServiceDAO extends GenericDAO<User, String> implements IUserDetailsServiceDAO {

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<Gallery> getAvaliableGalleries(User user) {
        return null;
    }

    @Override
    public void addPrivateUser(String code) {
        User user = new User();
        user.setLogin(code);
        user.setPassword(fileUtils.getDigest("gallery"));
        user.setRole("ROLE_USER");
        makePersistent(user);
    }

    public UserDetailsServiceDAO() {
        super(User.class);
    }
}
