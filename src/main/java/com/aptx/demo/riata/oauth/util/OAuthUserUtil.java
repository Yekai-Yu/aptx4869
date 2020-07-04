package com.aptx.demo.riata.oauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.aptx.demo.riata.oauth.model.GitHubOAuthUser;
import com.aptx.demo.riata.oauth.model.GoogleOAuthUser;
import com.aptx.demo.riata.user.model.UserType;

@Component
public class OAuthUserUtil {

    private static final Logger log = LoggerFactory.getLogger(OAuthUserUtil.class);

    public OAuth2User getCurrentOAuthUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if(context != null) {
            Authentication auth = context.getAuthentication();
            if(auth != null) {
                OAuth2User userInfo = OAuthUserFactory.getOAuthUser(auth.getPrincipal());
                return userInfo;
            }
        }
        return null;
    }

    public String getOAuthType(OAuth2User user) {
        if(user == null) {
            return "";
        } else if (user instanceof GitHubOAuthUser) {
            return UserType.GITHUB.getValue();
        } else if (user instanceof GoogleOAuthUser) {
            return UserType.GOOGLE.getValue();
        } else {
            return UserType.REGULAR.getValue();
        }
    }

    public String getOAuthType(String client) {
        if(client == null) {
            return "";
        } else if (client.equalsIgnoreCase(UserType.GITHUB.getValue())) {
            return UserType.GITHUB.getValue();
        } else if (client.equalsIgnoreCase(UserType.GOOGLE.getValue())) {
            return UserType.GOOGLE.getValue();
        } else {
            return UserType.REGULAR.getValue();
        }
    }

    public String getSignature(OAuth2User user, String clientName) {
        String oAuthType = getOAuthType(clientName);
        if(user == null) {
            return "";
        } else if (oAuthType.equals(UserType.GITHUB.getValue())) {
            return user.getAttributes().get("id").toString();
        } else if (oAuthType.equals(UserType.GOOGLE.getValue())) {
            return user.getAttributes().get("sub").toString();
        } else {
            return "";
        }
    }
}
