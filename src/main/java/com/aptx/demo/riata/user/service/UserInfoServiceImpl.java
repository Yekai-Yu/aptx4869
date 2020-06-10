package com.aptx.demo.riata.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.aptx.demo.riata.user.dao.UserRepository;
import com.aptx.demo.riata.user.model.UserDO;
import com.aptx.demo.riata.user.model.UserDTO;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserInfoById(String uid) {
        if(uid == null) {
            throw new RuntimeException("Invalid uid");
        }
        Optional<UserDO> userOptional;
        UserDTO userValue;
        try {
            userOptional = userRepository.findById(uid);
            userValue = new UserDTO(userOptional.get());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return userValue;
    }

    @Override
    public String updateUserInfo(UserDTO userDTO) {
        return null;
    }
}
