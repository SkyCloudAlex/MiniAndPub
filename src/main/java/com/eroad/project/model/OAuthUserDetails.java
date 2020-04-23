package com.eroad.project.model;

import java.util.Collections;

public class OAuthUserDetails extends org.springframework.security.core.userdetails.User {

    private OAuthUser user;

    public OAuthUserDetails(OAuthUser oauthUser) {
        super(oauthUser.getUsername(), oauthUser.getPassword(), true, true, true, true, Collections.EMPTY_SET);
        this.user = oauthUser;
    }

    public OAuthUser getUser() {
        return user;
    }

    public void setUser(OAuthUser user) {
        this.user = user;
    }
}