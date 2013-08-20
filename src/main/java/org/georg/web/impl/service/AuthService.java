package org.georg.web.impl.service;

import org.georg.web.impl.dao.custom.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserDAO userDetailsServiceDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        org.georg.web.impl.model.User user = userDetailsServiceDAO.getById(s);
        if (user == null) throw new UsernameNotFoundException("User " + s + " not found.");

        return user;
    }


}
