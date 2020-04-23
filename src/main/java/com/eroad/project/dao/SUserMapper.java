package com.eroad.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eroad.project.core.Mapper;
import com.eroad.project.model.SUser;

public interface SUserMapper extends Mapper<SUser> {
	
	/**
	 * 用户列表
	 * @param coId
	 * @param deptId
	 * @param rId
	 * @return
	 */
	List<Map<String, Object>> findAllUser(@Param("coId")String coId, @Param("deptId")String deptId, @Param("rId")String rId);

	/**
	 * 通过ID查询用户
	 */
	Map<String, Object> findUserById(String uId);
}