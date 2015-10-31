package org.lyon_yan.android.utils.refeclt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectByMapUtils {
	/**
	 * 加载map中的数据至object
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月29日 下午3:15:59
	 * @param object
	 *            不可为空
	 * @param map
	 *            不可为空
	 */
	public static void loadThisObjectByMap(Object object,
			Map<String, Object> map) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				String name = field.getName();
				if (map.containsKey(name)) {
					try {
						name = name.substring(0, 1).toUpperCase()
								+ name.substring(1); // 将属性的首字符大写，方便构造get，set方法
						Method method = object.getClass().getMethod(
								"set" + name, field.getType());
						method.invoke(object, map.get(name));
						name = null;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		fields = null;
	}

	/**
	 * 将object数据导出为Map
	 * 
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月29日 下午3:17:28
	 * @param object
	 * @param ignore
	 *            忽略的属性名
	 * @return
	 */
	public static Map<String, Object> toMapWithIgnore(Object object,
			String... ignore) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		List<String> list = Arrays.asList(ignore);
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(object);
				if (!list.contains(obj)) {
					map.put(field.getName(), obj);
				}
				obj = null;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		fields = null;
		list = null;
		return map;
	}

	/**
	 * 将object数据导出为Map
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月29日 下午3:18:42
	 * @param object
	 * @return
	 */
	public static Map<String, Object> toMap(Object object) {
		return toMapWithIgnore(object, null, "");
	}
}
