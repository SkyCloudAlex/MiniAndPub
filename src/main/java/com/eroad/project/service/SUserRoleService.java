package com.eroad.project.service;
import com.eroad.project.core.Service;
import com.eroad.project.model.SUserRole;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SUserRoleService extends Service<SUserRole> {
	/**
	 * 删除用户角色关联
	 * @param ids
	 * @param type uId/rId
	 */
	void deleteUserRole(String ids, String type);
}
