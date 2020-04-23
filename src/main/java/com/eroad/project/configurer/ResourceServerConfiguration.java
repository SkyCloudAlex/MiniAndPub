package com.eroad.project.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 关闭认证  1，删除 该类的  @EnableResourceServer注解
 *       2.security.basic.enabled=false
 *       
 * 开启认证  1，添加该类 @EnableResourceServer 的注解
 *       2.security.basic.enabled=true或者注释 #security.basic.enabled=false
 */
@Configuration
@EnableResourceServer
@EnableScheduling
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter  {

	
	@Value("#{'${security.antMatche.permit}'.split(',')}")
	private String[] securityPermit;
	    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
        http.authorizeRequests()
        .antMatchers(securityPermit).permitAll()
        .antMatchers("/**").authenticated()
        .anyRequest().authenticated();
        
    }

}
