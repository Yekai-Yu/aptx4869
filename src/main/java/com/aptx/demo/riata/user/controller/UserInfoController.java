package com.aptx.demo.riata.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptx.demo.riata.user.model.User;
import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.service.UserInfoService;

import java.util.List;

@RestController
public class UserInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "user/{uid}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable(required = true) final String uid) {
        if(StringUtils.isEmpty(uid)) {
            throw new RuntimeException("Invalid uid");
        }
        UserDTO userInfo;
        userInfo = userInfoService.getUserInfoById(uid);

        User response = new User();
        response.setId(userInfo.getId());
        response.setName(userInfo.getName());

        return response;
    }

    @RequestMapping(value = "user/{uid}", method = RequestMethod.POST)
    public User updateUserInfo(@PathVariable(required = true) final String uid,
                               @RequestParam(required = false) final List<String> preference) {
        if(StringUtils.isEmpty(uid)) {
            throw new RuntimeException("Invalid uid");
        }
        User response;
        UserDTO savedUser = userInfoService.updateUserInfo(uid, preference);

        if(savedUser != null) {
            response = new User(savedUser);
        } else {
            throw new RuntimeException("Unable to update user");
        }

        return response;
    }
}
