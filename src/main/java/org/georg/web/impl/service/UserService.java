package org.georg.web.impl.service;

import org.georg.web.impl.dao.custom.UserDetailsServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserDetailsServiceDAO userDetailsServiceDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        org.georg.web.impl.model.User user = userDetailsServiceDAO.getById(s);
        if (user == null) return null;
        return user;
    }


}
