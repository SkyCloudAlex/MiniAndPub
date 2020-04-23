package com.eroad.project.service;
import com.eroad.project.core.Service;
import com.eroad.project.model.SVcode;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SVcodeService extends Service<SVcode> {
	/**
	 * 失效短信验证码
	 * @param vMobileNo
	 * @param vType
	 */
	void failVcode(String vMobileNo, String vType);

	/**
	 * 短信验证码校验
	 * @param vMobileNo
	 * @param vcode
	 * @param vType
	 * @return
	 */
	SVcode checkVcode(String vMobileNo, String vcode, String vType);

	/**
	 * 新增短信验证码
	 * @param vcode
	 * @param mobileNo
	 * @param vType
	 */
	void saveVcode(int vcode, String vMobileNo, String vType);

	/**
	 * 根据手机号获取有效验证码（抽奖凭证）
	 * @param mobileNo
	 * @return
	 */
	SVcode findAbleCode(String mobileNo, String type);
}
