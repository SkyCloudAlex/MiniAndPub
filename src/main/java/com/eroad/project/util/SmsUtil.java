package com.eroad.project.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.eroad.project.model.SmsSendRequest;
import com.eroad.project.model.SmsSendResponse;

public class SmsUtil {

	private static String account;
	private static String password;

	@Value("${sms.account}")
	public void setAccount(String account) {
		this.account = account;
	}

	@Value("${sms.password}")
	public void setPassword(String password) {
		this.password = password;
	}

	public static SmsSendResponse sendSms(String phone, String msg) {
		String smsSingleRequestServerUrl = "https://xxx/msg/send/json";
		String report = "false";
		String extend = "123";
		String uid = "abc123";

		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phone, report, extend);
		String requestJson = JSON.toJSONString(smsSingleRequest);
		String response = SmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		System.out.println("response  toString is :" + smsSingleResponse);
		return smsSingleResponse;
	}

	public static String sendSmsByPost(String path, String postContent) {
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
			httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			httpURLConnection.connect();
			OutputStream os=httpURLConnection.getOutputStream();
			os.write(postContent.getBytes("UTF-8"));
			os.flush();

			StringBuilder sb = new StringBuilder();
			int httpRspCode = httpURLConnection.getResponseCode();
			if (httpRspCode == HttpURLConnection.HTTP_OK) {
				// 开始获取数据
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				return sb.toString();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
