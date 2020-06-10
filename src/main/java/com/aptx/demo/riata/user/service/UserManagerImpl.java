package com.aptx.demo.riata.user.service;

import com.aptx.demo.riata.user.dao.UserRepository;
import com.aptx.demo.riata.user.model.User;
import com.aptx.demo.riata.user.model.UserDO;
import com.aptx.demo.riata.user.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userManager")
public class UserManagerImpl implements UserManager {

    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public String createUser(UserDTO user) {
        log.info("creating user");
        UserDO userDO = new UserDO();

        userDO.setName(user.getName());
        userDO.setPreference(user.getPreference());
        userDO.setRating(user.getRating());
        userDO.setRatingCount(user.getRatingCount());
        userDO.setPostCount(user.getPostCount());

        userRepository.insert(userDO);

        return "ok";
    }
}
