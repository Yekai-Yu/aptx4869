package com.aptx.demo.riata.oauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.aptx.demo.riata.oauth.util.OAuthUserFactory;
import com.aptx.demo.riata.user.service.UserManager;

@Component
public class OAuthService extends DefaultOAuth2UserService{

    private static final Logger log = LoggerFactory.getLogger(OAuthService.class);

    @Autowired
    UserManager userManager;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        if(user == null) {
            log.debug("User authentication not found");
            return null;
        }
        String clientName = userRequest.getClientRegistration().getRegistrationId();
        boolean isExist = userManager.isExist(user, clientName);
        log.info("User exist: {}", isExist);

        if(!isExist) {
            try {
                userManager.createUserAfterOAuth(user, clientName);
            } catch (final Exception e) {
                throw new RuntimeException("Error create userIdentity for OAuth User", e);
            }
        }
        return OAuthUserFactory.getOAuthUser(user, clientName);
    }
}
