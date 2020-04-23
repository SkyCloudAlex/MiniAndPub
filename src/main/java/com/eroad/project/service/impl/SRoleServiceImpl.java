package com.eroad.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SRoleMapper;
import com.eroad.project.model.SRole;
import com.eroad.project.service.SRoleService;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SRoleServiceImpl extends AbstractService<SRole> implements SRoleService {
	@Resource
    private SRoleMapper roleMapper;

	@Override
	public List<SRole> getUserRole(String uId) {
		return roleMapper.getUserRole(uId);
	}

	@Override
	public List<Map<String, Object>> findAllRole() {
		return roleMapper.findAllRole();
	}

	@Override
	public List<Map<String, Object>> roleList() {
		return roleMapper.roleList();
	}

	@Override
	public void deleteRoles(String rIds) {
		String[] rIdArr = rIds.split(",");
		for (String rId: rIdArr) {
			roleMapper.deleteByPrimaryKey(rId);
		}
	}

}
