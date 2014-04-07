package org.georg.web.impl.service;

import org.georg.web.impl.dao.custom.base.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authService")
public class AuthService implements UserDetailsService {

    @Autowired
    private IUserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        org.georg.web.impl.model.User user = userDAO.getById(s);
        if (user == null) throw new UsernameNotFoundException("User " + s + " not found.");

        return user;
    }


}
