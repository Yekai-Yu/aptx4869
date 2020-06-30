package com.aptx.demo.riata.loginout.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aptx.demo.riata.loginout.model.UserForm;
import com.aptx.demo.riata.oauth.util.OAuthUserFactory;
import com.aptx.demo.riata.user.service.UserManager;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserManager userManager;

    @RequestMapping("/login.html")
    public String login() {
        log.info("login");
        return "login.html";
    }

    @RequestMapping(value = "/login/oauth2", method = RequestMethod.GET)
    @ResponseBody
    public String loginOAuth2() {
        return "<a href=\"/oauth2/authorization/github\">Github</a> \n" +
                "<a href=\"/oauth2/authorization/google\">Google</a>";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ResponseBody
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }
}
