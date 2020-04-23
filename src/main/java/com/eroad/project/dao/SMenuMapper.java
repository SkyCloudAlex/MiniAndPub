package com.eroad.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eroad.project.core.Mapper;
import com.eroad.project.model.SMenu;

public interface SMenuMapper extends Mapper<SMenu> {
	
	/**
	 * 获取用户关联菜单
	 * @param uId
	 * @return
	 */
	List<SMenu> getUserMenu(String uId);

	/**
	 * 获取上一菜单（按mSort排序）
	 * @param mParentId
	 * @param mSort
	 * @return
	 */
	SMenu getPreviousMenu(@Param("mParentId")String mParentId, @Param("mSort")String mSort);
	
	/**
	 * 获取下一菜单（按mSort排序）
	 * @param mParentId
	 * @param mSort
	 * @return
	 */
	SMenu getNextMenu(@Param("mParentId")String mParentId, @Param("mSort")String mSort);

	/**
	 * 取同一父节点下最大编号
	 * @param mParentId
	 * @return
	 */
	int getMaxMId(String mParentId);

	/**
	 * 获取角色关联菜单
	 * @param rId
	 * @return
	 */
	List<Map<String, Object>> getRoleMenu(String rId);
}