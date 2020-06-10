package com.aptx.demo.riata.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aptx.demo.riata.user.model.User;
import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.service.UserInfoService;

@RestController
public class UserInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "user/{uid}", method = RequestMethod.GET)
    public User getUser(@PathVariable(required = true) final String uid) {
        if(uid == null) {
            throw new RuntimeException("Invalid uid");
        }
        UserDTO userInfo;
        userInfo = userInfoService.getUserInfoById(uid);

        User response = new User();
        response.setId(userInfo.getId());
        response.setName(userInfo.getName());

        return response;
    }
}
