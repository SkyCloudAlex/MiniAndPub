package com.eroad.project.service;
import java.util.List;
import java.util.Map;

import com.eroad.project.core.Service;
import com.eroad.project.model.SUser;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SUserService extends Service<SUser> {
	/**
	 * 根据手机号、密码查找用户
	 * @param uMobileNo
	 * @param encodeUPassword
	 * @return
	 */
	SUser getUserByMobileAndPwd(String uMobileNo, String encodeUPassword);

	/**
	 * 用户列表
	 * @param coId
	 * @param deptId
	 * @param rId
	 * @return
	 */
	List<Map<String, Object>> findAllUser(String coId, String deptId, String rId);

	/**
	 * 通过ID查询用户
	 * @param uId
	 * @return
	 */
	Map<String, Object> findUserById(String uId);

	/**
	 * 删除用户
	 * @param uIds
	 */
	void deleteUsers(String uIds);
}
