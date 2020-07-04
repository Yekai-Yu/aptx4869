package com.aptx.demo.riata.oauth.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.aptx.demo.riata.user.model.UserIdentity;
import com.aptx.demo.riata.user.model.UserType;


/**
 * Immutable Authentication
 */
public class GoogleOAuthUser extends UserIdentity implements OidcUser {

    private List<GrantedAuthority> authorities =
            AuthorityUtils.createAuthorityList("ROLE_USER");
    private Map<String, Object> attributes;
    private Map<String, Object> claims;
    private OidcIdToken idToken;
    private OidcUserInfo userInfo;

    public GoogleOAuthUser(DefaultOidcUser user) {
        this.attributes = user.getAttributes();
        this.idToken = user.getIdToken();
        this.userInfo = user.getUserInfo();
        this.claims = user.getClaims();
        setType(UserType.GOOGLE.getValue());
        setUSignature(user.getAttributes().get("sub").toString());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }

    @Override
    public OidcIdToken getIdToken() {
        return idToken;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public String getName() {
        return (String) this.attributes.get("name");
    }
}
