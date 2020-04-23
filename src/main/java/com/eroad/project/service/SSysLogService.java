package com.eroad.project.service;
import java.util.List;
import java.util.Map;

import com.eroad.project.core.Service;
import com.eroad.project.model.SSysLog;


/**
 * Created by cyt on 2018/12/11.
 */
public interface SSysLogService extends Service<SSysLog> {
	/**
	 * 是否首次登录
	 * @param uId
	 * @return
	 */
	boolean firstLogin(String uId);

	/**
	 * 保存运管日志
	 * @param string
	 * @param uId
	 */
	void saveSysLog(String string, String uId);

	/**
	 * 运管日志列表
	 * @return
	 */
	List<Map<String, Object>> findAllSysLog();
}
