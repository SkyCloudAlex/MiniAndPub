package com.eroad.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SRoleMenuMapper;
import com.eroad.project.model.SRoleMenu;
import com.eroad.project.service.SRoleMenuService;

import tk.mybatis.mapper.entity.Condition;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SRoleMenuServiceImpl extends AbstractService<SRoleMenu> implements SRoleMenuService {
	@Resource
    private SRoleMenuMapper roleMenuMapper;

	@Override
	public void deleteByRId(String rId) {
		Condition condition = new Condition(SRoleMenu.class);
		condition.createCriteria().andEqualTo("rId", rId);
		
		roleMenuMapper.deleteByCondition(condition);
	}

	@Override
	public void deleteRoleMenu(String ids, String type) {
		String[] idArr = ids.split(",");
		
		if (idArr != null && idArr.length > 0) {
			for (String id: idArr) {
				Condition condition = new Condition(SRoleMenu.class);
				condition.createCriteria().andEqualTo(type, id);
				
				roleMenuMapper.deleteByCondition(condition);
			}
		}
	}

}
