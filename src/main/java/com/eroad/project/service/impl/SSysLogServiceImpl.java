package com.eroad.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SSysLogMapper;
import com.eroad.project.model.SSysLog;
import com.eroad.project.service.SSysLogService;
import com.eroad.project.util.CommonUtil;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SSysLogServiceImpl extends AbstractService<SSysLog> implements SSysLogService {
	@Resource
    private SSysLogMapper sysLogMapper;

	@Override
	public boolean firstLogin(String uId) {
		SSysLog sysLog = new SSysLog();
		sysLog.setlContent("系统登录");
		sysLog.setuId(uId);
		int loginCount = sysLogMapper.selectCount(sysLog);
		
		if (loginCount > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void saveSysLog(String content, String uId) {
		SSysLog sysLog = new SSysLog();
		sysLog.setlId(CommonUtil.getUUID());
		sysLog.setlContent(content);
		sysLog.setlTime(new Date());
		sysLog.setuId(uId);
		sysLogMapper.insertSelective(sysLog);
	}

	@Override
	public List<Map<String, Object>> findAllSysLog() {
		return sysLogMapper.findAllSysLog();
	}

}
