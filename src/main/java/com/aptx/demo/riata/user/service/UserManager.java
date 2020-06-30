package com.aptx.demo.riata.user.service;

import java.util.List;

import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.model.UserIdentity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserManager {
    public void createUser(String name, String email, String password);
    public void createUser(String name, String email, String password, String type, String signature);
    public void createUserAfterOAuth(OAuth2User user, String clientName);
    public boolean isExist(OAuth2User user, String clientName);
}
