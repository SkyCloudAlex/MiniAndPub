package com.eroad.project.util;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ConvertUtil {

	/**
	 * 对象转Map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Object> convertToMap(Object obj) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			boolean accessFlag = fields[i].isAccessible();
			fields[i].setAccessible(true);

			Object o = fields[i].get(obj);
			if (o != null)
				map.put(varName, o.toString());

			fields[i].setAccessible(accessFlag);
		}

		return map;
	}

}
