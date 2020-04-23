package com.eroad.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eroad.project.core.Mapper;
import com.eroad.project.model.SOrganization;

public interface SOrganizationMapper extends Mapper<SOrganization> {
	
	/**
	 * 取同一父节点下最大编号
	 * @param oParentId
	 * @return
	 */
	int getMaxOId(String oParentId);
	
	/**
	 * 获取上一组织机构（按orgSort排序）
	 * @param oParentId
	 * @param oSort
	 * @return
	 */
	SOrganization getPreviousOrganization(@Param("oParentId")String oParentId, @Param("oSort")String oSort);
	
	/**
	 * 获取下一组织机构（按orgSort排序）
	 * @param oParentId
	 * @param oSort
	 * @return
	 */
	SOrganization getNextOrganization(@Param("oParentId")String oParentId, @Param("oSort")String oSort);

	/**
	 * 可用公司列表
	 * @return
	 */
	List<Map<String, Object>> getCoList();

	/**
	 * 可用部门列表
	 * @param orgId
	 * @return
	 */
	List<Map<String, Object>> getDeptList(String oParentId);

}