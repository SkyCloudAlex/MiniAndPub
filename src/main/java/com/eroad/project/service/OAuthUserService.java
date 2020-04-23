package com.eroad.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public interface OAuthUserService extends UserDetailsService {
    //后期在此新增UserService的业务接口
	
	public boolean addOAuthClient(BaseClientDetails oauthClient);
	public boolean addOAuthClient(String userName,String passWord);
	public boolean delOAuthClient(String userName);
	public boolean updateOAuthClient(String userName,String passWord);
	
}
