package com.eroad.project.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;

@RestController
@RequestMapping("/wechat")
public class WechatController {

	// 微信网页授权code获取链接
	private static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	// 微信网页授权access_token获取链接
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 微信网页授权user_info获取链接
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// 微信user_info获取链接（非网页授权）
	private static final String CGI_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	@Value("${wechat.appId}")
	private String appId;
	
	@Value("${wechat.appSecret}")
	private String appSecret;
	
	@PostMapping("/getOpenid")
    public Result getOpenid() {
	
		return null;
	}
	
}
