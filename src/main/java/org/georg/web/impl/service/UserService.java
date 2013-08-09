package org.georg.web.impl.service;

import org.georg.web.impl.dao.custom.base.IUserDAO;
import org.georg.web.impl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private IUserDAO userDAO;

    @Transactional(readOnly = false)
    public void registerNewUser(User user) {

    }

    @Transactional(readOnly = false)
    public void activateUser(String code) {
        userDAO.getByActivationCode(code);
    }


}
