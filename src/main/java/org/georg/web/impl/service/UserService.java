package org.georg.web.impl.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.georg.web.impl.dao.custom.base.IUserDAO;
import org.georg.web.impl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private IUserDAO userDAO;

    @Value("${file.thumbs.strategy}")
    private String thumbsStrategy;

    @Transactional(readOnly = false)
    public String registerNewUser(User user) {
        String code = Hex.encodeHexString(Base64.encodeBase64(DigestUtils.sha256(user.getLogin() + "." + user.getPassword())));
        user.setActivationCode(code);
        user.setRole("ROLE_USER");
        try {
            user.setPassword(Hex.encodeHexString(MessageDigest.getInstance(thumbsStrategy).digest(user.getPassword().getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDAO.update(user);
        return code;
    }

    @Transactional(readOnly = false)
    public void activateUser(String code) {
        User user = userDAO.getByActivationCode(code);
        if (user != null) {
            user.setActivated(true);
            userDAO.update(user);
        }
    }


}
