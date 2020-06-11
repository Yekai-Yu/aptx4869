package com.aptx.demo.riata.user.service;

import com.aptx.demo.riata.user.dao.UserIdentityRepository;
import com.aptx.demo.riata.user.dao.UserRepository;
import com.aptx.demo.riata.user.model.User;
import com.aptx.demo.riata.user.model.UserDO;
import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.model.UserIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userManager")
public class UserManagerImpl implements UserManager {

    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    UserIdentityRepository userIdentityRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(String name, String email, String password) {
        log.info("creating user");

        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setName(name);
        userIdentity.setEmail(email);
        userIdentity.setPassword(password);

        UserIdentity createdUser;
        try {
            createdUser = userIdentityRepository.save(userIdentity);
        } catch (final Exception e) {
            log.error("Unable to create user", e);
            throw new RuntimeException("Unable to create user", e);
        }

        // initialize user info
        UserDO userInfo = new UserDO(createdUser);
        try {
            userRepository.save(userInfo);
        } catch (final Exception e) {
            throw new RuntimeException("Unable to initialize user info", e);
        }
    }
}
