package com.eroad.project.service;
import java.util.List;
import java.util.Map;

import com.eroad.project.core.Service;
import com.eroad.project.model.SOrganization;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SOrganizationService extends Service<SOrganization> {
	/**
	 * 获取新组织结构编号
	 * @param oParentId
	 * @return
	 */
	String getNewOId(String oParentId);
	
	/**
	 * 组织机构上移、下移
	 * @param oId
	 * @param pos up/down
	 */
	void move(String oId, String pos);

	/**
	 * 可用公司列表
	 * @return
	 */
	List<Map<String, Object>> getCoList();

	/**
	 * 可用部门列表
	 * @return
	 */
	List<Map<String, Object>> getDeptList(String oParentId);

}
