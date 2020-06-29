package com.aptx.demo.riata.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aptx.demo.riata.oauth.util.OAuthUserUtil;
import com.aptx.demo.riata.user.model.UserType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.aptx.demo.riata.oauth.util.OAuthUserFactory;
import com.aptx.demo.riata.user.dao.UserIdentityRepository;
import com.aptx.demo.riata.user.dao.UserRepository;
import com.aptx.demo.riata.user.model.UserDO;
import com.aptx.demo.riata.user.model.UserDTO;
import com.aptx.demo.riata.user.model.UserIdentity;

@Service("userManager")
public class UserManagerImpl implements UserManager {

    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

    private static final String NOT_FOUND = "NOTFOUND";
    private static final String UNKNOWN = "UNKNOWN";

    @Autowired
    OAuthUserUtil oAuthUserUtil;

    @Autowired
    UserIdentityRepository userIdentityRepository;

    @Autowired
    UserRepository userRepository;

    public void createUser(UserIdentity user) {
        if(user == null) {
            log.error("No user info is passed in");
            throw new RuntimeException("UserIdentity is null");
        }
        UserIdentity createdUser;
        try {
            createdUser = userIdentityRepository.save(user);
        } catch (final Exception e) {
            log.error("Unable to create user", e);
            throw new RuntimeException("Unable to create user", e);
        }

        initUserInfo(createdUser);

    }

    @Override
    public void createUser(String name, String email, String password) {
        createUser(name, email, password, UserType.REGULAR.getValue(), "");
    }

    @Override
    public void createUser(String name, String email, String password, String type, String signature) {
        log.info("creating user");

        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUName(name);
        userIdentity.setUEmail(email);
        userIdentity.setUPassword(password);
        if(!StringUtils.isEmpty(type)) {
            userIdentity.setType(type);
        }
        userIdentity.setUSignature(signature);

        createUser(userIdentity);
    }

    @Override
    public void createUserAfterOAuth(OAuth2User user, String clientName) {
        log.info("Creating user using OAuth info");

        try {
            Object oAuthName = user.getAttributes().get("name");
            Object oAuthEmail = user.getAttributes().get("email");
            String oAuthSig = oAuthUserUtil.getSignature(user, clientName);
            createUser(oAuthName != null ? oAuthName.toString() : UNKNOWN,
                       oAuthEmail != null ? oAuthEmail.toString() : UNKNOWN,
                      "",
                       clientName != null ? oAuthUserUtil.getOAuthType(clientName) : UserType.REGULAR.getValue(),
                       !StringUtils.isEmpty(oAuthSig) ? oAuthSig : UNKNOWN);
        } catch (final Exception e) {
            log.error("Error create user using OAuth info", e);
        }
    }

    public void initUserInfo(UserIdentity user) {
        // initialize user info
        if(user == null) {
            log.warn("User init failed, due to UserIdentity is null");
            return;
        }
        UserDO userInfo = new UserDO(user);
        try {
            userRepository.save(userInfo);
        } catch (final Exception e) {
            throw new RuntimeException("Unable to initialize user info", e);
        }
    }

    @Override
    public boolean isExist(OAuth2User user, String clientName) {
        if(user == null) {
            log.debug("User not found");
            return false;
        }
        String signature = oAuthUserUtil.getSignature(user, clientName);
        Optional<UserIdentity> result = userIdentityRepository.findByuSignature(signature);

        return result.isPresent();
    }
}
