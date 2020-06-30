package com.aptx.demo.riata.oauth.service;

import com.aptx.demo.riata.oauth.util.OAuthUserFactory;
import com.aptx.demo.riata.user.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.aptx.demo.riata.oauth.model.GoogleOAuthUser;
import org.springframework.stereotype.Component;

@Component
public class OidcService extends OidcUserService {

    private static final Logger log = LoggerFactory.getLogger(OidcService.class);

    @Autowired
    UserManager userManager;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser user = super.loadUser(userRequest);
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

        return (GoogleOAuthUser) OAuthUserFactory.getOAuthUser(user, userRequest.getClientRegistration().getRegistrationId());
    }
}
