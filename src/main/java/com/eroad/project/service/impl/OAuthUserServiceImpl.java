package com.eroad.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.eroad.project.configurer.AuthorizationConfig;
import com.eroad.project.model.OAuthUser;
import com.eroad.project.model.OAuthUserDetails;
import com.eroad.project.service.OAuthUserService;

@Service
public class OAuthUserServiceImpl  implements   OAuthUserService {
    
    @Autowired
    private AuthorizationConfig authorization;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        /*模拟数据库操作*/
        OAuthUser user = new OAuthUser();
        
        ClientDetails client=authorization.clientDetails().loadClientByClientId(s);
        
        user.setUsername(client.getClientId());
        user.setPassword(client.getClientSecret());

        return new OAuthUserDetails(user);
    }

	@Override
	public boolean addOAuthClient(BaseClientDetails oauthClient) {
		
		boolean result=true;
		try {
			authorization.clientDetails().addClientDetails(oauthClient);
		} catch (Exception e) {
			result=false;
		}
		
		return result;
	}

	@Override
	public boolean addOAuthClient(String userName, String passWord) {
		
		BaseClientDetails oauthClient=new BaseClientDetails();
    	List<String> scope=new ArrayList<String>();
    	scope.add("read");
    	scope.add("write");
    	
    	List<String> grantType=new ArrayList<String>();
    	grantType.add("password");
    	grantType.add("refresh_token");
    	
    	BCryptPasswordEncoder encode= new BCryptPasswordEncoder();
    	
    	oauthClient.setClientId(userName);
    	oauthClient.setClientSecret(encode.encode(passWord));
    	oauthClient.setScope(scope);
    	oauthClient.setAuthorizedGrantTypes(grantType);
    	
		boolean result=true;
		try {
			authorization.clientDetails().addClientDetails(oauthClient);
		} catch (Exception e) {
			result=false;
		}
		
		return result;
	}

	@Override
	public boolean delOAuthClient(String userName) {

    	
		boolean result=true;
		try {
			authorization.clientDetails().removeClientDetails(userName);
		} catch (Exception e) {
			result=false;
		}
		
		return result;
	}

	@Override
	public boolean updateOAuthClient(String userName, String passWord) {

		BaseClientDetails oauthClient=new BaseClientDetails();
    	List<String> scope=new ArrayList<String>();
    	scope.add("read");
    	scope.add("write");
    	
    	List<String> grantType=new ArrayList<String>();
    	grantType.add("password");
    	grantType.add("refresh_token");
    	
    	oauthClient.setClientId(userName);
    	BCryptPasswordEncoder encode= new BCryptPasswordEncoder();
    	
    	oauthClient.setClientSecret(encode.encode(passWord));
    	oauthClient.setScope(scope);
    	oauthClient.setAuthorizedGrantTypes(grantType);
    	
		boolean result=true;
		try {
			authorization.clientDetails().updateClientDetails(oauthClient);
		} catch (Exception e) {
			result=false;
		}
		
		return result;
	}

}