package com.aptx.demo.riata.config;

import com.aptx.demo.riata.oauth.model.GitHubOAuthUser;
import com.aptx.demo.riata.oauth.model.GoogleOAuthUser;
import com.aptx.demo.riata.oauth.service.OAuthService;
import com.aptx.demo.riata.oauth.service.OidcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OidcService oidcService;

    @Autowired
    OAuthService oAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .authorizeRequests(authorize -> authorize
                .antMatchers("/", "/login*/**", "/logout/**", "/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login/oauth2")
                .failureUrl("/login/oauth2")
                .userInfoEndpoint(userInfo -> userInfo
                    .customUserType(GitHubOAuthUser.class, "github")
                    .customUserType(GoogleOAuthUser.class, "google")
                    .oidcUserService(oidcService)
                    .userService(oAuthService)
                )
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
            );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN");
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
