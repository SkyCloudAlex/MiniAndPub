package com.eroad.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SUserRoleMapper;
import com.eroad.project.model.SUserRole;
import com.eroad.project.service.SUserRoleService;

import tk.mybatis.mapper.entity.Condition;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SUserRoleServiceImpl extends AbstractService<SUserRole> implements SUserRoleService {
	@Resource
    private SUserRoleMapper userRoleMapper;

	@Override
	public void deleteUserRole(String ids, String type) {
		String[] idArr = ids.split(",");
		
		for (String id: idArr) {
			Condition condition = new Condition(SUserRole.class);
			condition.createCriteria().andEqualTo(type, id);
			
			userRoleMapper.deleteByCondition(condition);
		}
	}

}
