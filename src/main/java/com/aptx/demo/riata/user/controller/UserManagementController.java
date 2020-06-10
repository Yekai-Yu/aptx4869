package com.aptx.demo.riata.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aptx.demo.riata.user.model.User;
import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.service.UserManager;

@RestController
public class UserManagementController {

    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        log.info("creating user {}", user.getName());
        UserDTO userDTO = new UserDTO(user);
        userManager.createUser(userDTO);
        return "ok";
    }
}
