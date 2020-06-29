package com.aptx.demo.riata.oauth.util;

import com.aptx.demo.riata.oauth.model.GitHubOAuthUser;
import com.aptx.demo.riata.oauth.model.GoogleOAuthUser;
import com.aptx.demo.riata.user.model.UserType;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuthUserFactory {

    public static OAuth2User getOAuthUser(Object user) {
        if(user == null) {
            return null;
        } else if(user instanceof GitHubOAuthUser) {
            return (GitHubOAuthUser) user;
        } else if(user instanceof DefaultOidcUser) {
            return (GoogleOAuthUser) user;
        } else {
            return (OAuth2User) user;
        }
    }

    public static OAuth2User getOAuthUser(OAuth2User user, String registrationId) {
        if(user == null) {
            return null;
        } else if(registrationId.equalsIgnoreCase(UserType.GITHUB.getValue())) {
            return new GitHubOAuthUser((DefaultOAuth2User) user);
        } else if(registrationId.equalsIgnoreCase(UserType.GOOGLE.getValue())) {
            return new GoogleOAuthUser((DefaultOidcUser) user);
        } else {
            return user;
        }
    }
}
