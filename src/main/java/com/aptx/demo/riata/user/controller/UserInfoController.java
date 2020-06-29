package com.aptx.demo.riata.user.controller;

import com.aptx.demo.riata.oauth.model.GitHubOAuthUser;
import com.aptx.demo.riata.oauth.util.OAuthUserFactory;
import com.aptx.demo.riata.user.service.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptx.demo.riata.user.model.User;
import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.service.UserInfoService;

import java.time.LocalTime;
import java.util.List;

@RestController
public class UserInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String testUser() {
        OAuth2User principal = null;
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            log.info(authentication.getClass().getName());
            Object details = authentication.getDetails();
            Object pr = authentication.getPrincipal();
            try {
                principal = OAuthUserFactory.getOAuthUser(authentication.getPrincipal());
                log.info("userinfo type;  {}", principal.getClass().getName());
            } catch (Exception e) {
                log.info("e",e);
            }
            Object user = authentication.getDetails();
//            Object cre = authentication.getCredentials();

            log.info("authen: details: {}, princia: {} , user: {}, cre: {}", details, principal.getAttributes().get("name"), user, "cre");
        } catch (Exception ee) {
            log.error("ee", ee);
        }
        finally {
            return "Test API\n" + LocalTime.now() + "\n";
        }
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.POST)
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
