package com.eroad.project.service;
import com.eroad.project.core.Service;
import com.eroad.project.model.SRoleMenu;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SRoleMenuService extends Service<SRoleMenu> {
	/**
	 * 清空角色菜单关联
	 * @param rId
	 */
	void deleteByRId(String rId);

	/**
	 * 删除角色菜单关联
	 * @param ids
	 * @param type rId/mId
	 */
	void deleteRoleMenu(String ids, String type);
}
