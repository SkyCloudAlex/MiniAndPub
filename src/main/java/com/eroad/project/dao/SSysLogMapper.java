package com.eroad.project.dao;

import java.util.List;
import java.util.Map;

import com.eroad.project.core.Mapper;
import com.eroad.project.model.SSysLog;

public interface SSysLogMapper extends Mapper<SSysLog> {
	
	/**
	 * 运管日志列表
	 * @return
	 */
	List<Map<String, Object>> findAllSysLog();
}