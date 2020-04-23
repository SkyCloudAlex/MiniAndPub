package com.eroad.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SUserMapper;
import com.eroad.project.model.SUser;
import com.eroad.project.service.SUserService;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SUserServiceImpl extends AbstractService<SUser> implements SUserService {
	@Resource
    private SUserMapper userMapper;

	@Override
	public SUser getUserByMobileAndPwd(String uMobileNo, String encodeUPassword) {
		Condition condition = new Condition(SUser.class);
    	Example.Criteria criteria = condition.createCriteria();
    	criteria.andEqualTo("uMobileNo", uMobileNo);
    	criteria.andEqualTo("uPassword", encodeUPassword);
    	
    	List<SUser> userList = userMapper.selectByCondition(condition);
    	if (userList != null && userList.size() > 0) {
    		return userList.get(0);
    	} else {
    		return null;
    	}
	}

	@Override
	public List<Map<String, Object>> findAllUser(String coId, String deptId, String rId) {
		return userMapper.findAllUser(coId, deptId, rId);
	}

	@Override
	public Map<String, Object> findUserById(String uId) {
		return userMapper.findUserById(uId);
	}

	@Override
	public void deleteUsers(String uIds) {
		String[] uIdArr = uIds.split(",");
		for (String uId: uIdArr) {
			userMapper.deleteByPrimaryKey(uId);
		}
	}
}
