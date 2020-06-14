package com.aptx.demo.riata.user.service;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.aptx.demo.riata.user.dao.UserRepository;
import com.aptx.demo.riata.user.model.UserDO;
import com.aptx.demo.riata.user.model.UserDTO;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserInfoById(String uid) {
        if(StringUtils.isEmpty(uid)) {
            throw new RuntimeException("Invalid uid");
        }
//        UserDO user;
        Optional<UserDO> user;
        UserDTO userValue;
        try {
            user = userRepository.findById(new ObjectId(uid));
            userValue = new UserDTO(user.get());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return userValue;
    }

    @Override
    public UserDTO updateUserInfo(String uid, List<String> preference) {
        if(StringUtils.isEmpty(uid)) {
            throw new RuntimeException("Invalid uid");
        }
        // Look up for existing user
        UserDTO userDTO = getUserInfoById(uid);
        if(userDTO == null) {
            log.error("Unable to find user {}", uid);
            return null;
        }
        userDTO.setPreference(preference);
        UserDO userDO = new UserDO(userDTO);

        UserDO savedUser;
        UserDTO response;
        try {
            savedUser = userRepository.save(userDO);
            response = savedUser != null ? (new UserDTO(savedUser)) : userDTO;
        } catch (final Exception e) {
            log.error("Failed to save user {}", uid);
            throw new RuntimeException("Failed to save user");
        }

        return response;
    }
}
