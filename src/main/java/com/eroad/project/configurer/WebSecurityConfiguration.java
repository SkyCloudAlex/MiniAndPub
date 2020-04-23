package com.eroad.project.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eroad.project.service.OAuthUserService;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter  {

    private final OAuthUserService oauthUserService;

    @Autowired
    public WebSecurityConfiguration(OAuthUserService oauthUserService) {
        this.oauthUserService = oauthUserService;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.userDetailsService(oauthUserService).passwordEncoder(new BCryptPasswordEncoder());

    }

}
