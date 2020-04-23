package com.eroad.project.service;
import java.util.List;
import java.util.Map;

import com.eroad.project.core.Service;
import com.eroad.project.model.SMenu;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SMenuService extends Service<SMenu> {
	/**
	 * 获取用户关联菜单
	 * @param uId
	 * @return
	 */
	List<SMenu> getUserMenu(String uId);
	
	/**
	 * 菜单上移、下移
	 * @param mId
	 * @param pos up/down
	 */
	void move(String mId, String pos);

	/**
	 * 获取新菜单编号
	 * @param mParentId
	 * @return
	 */
	String getNewMId(String mParentId);

	/**
	 * 获取角色关联菜单
	 * @param rId
	 * @return
	 */
	List<Map<String, Object>> getRoleMenu(String rId);
}
