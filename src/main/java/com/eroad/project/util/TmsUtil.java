package com.eroad.project.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.eroad.project.model.Template;
import com.eroad.project.model.TemplateResult;

@Component
public class TmsUtil {
	
	private static String appId;
	private static String appSecret;
	
	@Value("${wechat.appId}")
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	@Value("${wechat.appSecret}")
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	private static final String TEMPLATE_URL= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 微信token获取
	 * @return
	 */
	public static String getToken() {
		String url = TOKEN_URL.replace("APPID", appId).replace("SECRET", appSecret);
		String tokenResult = HttpUtil.sendPost(url, "");
		
		@SuppressWarnings("unchecked")
		String token = ((Map<String, Object>) JSON.parse(tokenResult)).get("access_token").toString();
		
		return token;
	}

	/**
	 * 微信模版消息发送
	 * @param token
	 * @param template
	 * @return
	 */
	public static TemplateResult sendTms(String token, Template template) {
    	String url = TEMPLATE_URL.replace("ACCESS_TOKEN", token);
    	
    	String result = HttpUtil.sendPost(url, template.toJSON());
    	
    	TemplateResult tmsResult = JSON.parseObject(result, TemplateResult.class);
    	
    	return tmsResult;
    }
}
