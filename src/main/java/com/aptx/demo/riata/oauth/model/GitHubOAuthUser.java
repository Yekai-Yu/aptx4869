package com.aptx.demo.riata.oauth.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.aptx.demo.riata.user.model.UserIdentity;
import com.aptx.demo.riata.user.model.UserType;

/**
 * Immutable Authentication
 */
public class GitHubOAuthUser extends UserIdentity implements OAuth2User {
    private List<GrantedAuthority> authorities =
            AuthorityUtils.createAuthorityList("ROLE_USER");
    private Map<String, Object> attributes;

    public GitHubOAuthUser(DefaultOAuth2User user) {
        this.attributes = user.getAttributes();
        setType(UserType.GITHUB.getValue());
        setUSignature(user.getAttributes().get("id").toString());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return (String) this.attributes.get("name");
    }
}
