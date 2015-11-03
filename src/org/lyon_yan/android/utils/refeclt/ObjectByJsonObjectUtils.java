package org.lyon_yan.android.utils.refeclt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectByJsonObjectUtils {
	/**
	 * 加载jsonObject中的数据至object
	 * 
	 * @author Lyon_Yan <br>
	 *         <b>time</b>: 2015年10月29日 下午3:15:59
	 * @param object
	 *            不可为空
	 * @param jsonObject
	 *            不可为空
	 */
	public static void loadThisObjectByJsonObject(Object object,
			JSONObject jsonObject) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				String name = field.getName();
				if (jsonObject.has(name)) {
					try {
						Method method = object.getClass().getMethod(
								RefecltUtils.returnSetName(name),
								field.getType());
						method.invoke(object, jsonObject.get(name));
						name = null;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		fields = null;
	}

	/**
	 * 将object数据导出为jsonObject
	 * 
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月29日 下午3:17:28
	 * @param object
	 * @param ignore
	 *            忽略的属性名
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject toJsonObjectWithIgnore(Object object,
			String... ignore) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		Field[] fields = object.getClass().getDeclaredFields();
		List<String> list = Arrays.asList(ignore);
		for (Field field : fields) {
			String name = field.getName();
			try {
				Method method = object.getClass().getMethod(
						RefecltUtils.returnGetName(name));
				Object obj = method.invoke(object);
				if (!list.contains(obj)) {
					jsonObject.put(name, obj);
				}
				method = null;
				obj = null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			name = null;
		}
		fields = null;
		list = null;
		return jsonObject;
	}

	/**
	 * 将object数据导出为jsonObject
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月29日 下午3:18:42
	 * @param object
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject tojsonObject(Object object) throws JSONException {
		return toJsonObjectWithIgnore(object, null, "");
	}
}
