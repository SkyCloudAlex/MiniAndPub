package com.eroad.project.service;
import java.util.List;
import java.util.Map;

import com.eroad.project.core.Service;
import com.eroad.project.model.SRole;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SRoleService extends Service<SRole> {
	/**
	 * 获取用户关联角色
	 * @param uId
	 * @return
	 */
	List<SRole> getUserRole(String uId);

	/**
	 * 获取角色列表（带权限是否已配字段）
	 * @return
	 */
	List<Map<String, Object>> findAllRole();

	/**
	 * 可用角色列表
	 * @return
	 */
	List<Map<String, Object>> roleList();

	/**
	 * 批量删除角色
	 * @param rIds
	 */
	void deleteRoles(String rIds);
}
