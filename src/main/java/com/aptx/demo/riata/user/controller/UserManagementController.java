package com.aptx.demo.riata.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptx.demo.riata.user.service.UserManager;

@RestController
public class UserManagementController {

    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestParam(required = true) final String name,
                             @RequestParam(required = true) final String email,
                             @RequestParam(required = true) final String password) {
        log.info("creating user {}", name);
        try {
            userManager.createUser(name, email, password);
        } catch (final Exception e) {
            throw new RuntimeException("Unable to create user");
        }

        return "ok";
    }
}
