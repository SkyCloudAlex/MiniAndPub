package com.eroad.project.dao;

import java.util.List;
import java.util.Map;

import com.eroad.project.core.Mapper;
import com.eroad.project.model.SRole;

public interface SRoleMapper extends Mapper<SRole> {
	
	/**
	 * 获取用户关联角色
	 * @param uId
	 * @return
	 */
	List<SRole> getUserRole(String uId);

	/**
	 * 获取所有角色（带权限是否已配字段）
	 * @return
	 */
	List<Map<String, Object>> findAllRole();

	/**
	 * 可用角色列表
	 * @return
	 */
	List<Map<String, Object>> roleList();
}