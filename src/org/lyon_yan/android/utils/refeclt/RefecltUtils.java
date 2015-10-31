package org.lyon_yan.android.utils.refeclt;

/**
 * refelect工具包
 * 
 * @author Lyon_Yan <br/>
 *         <b>time</b>: 2015年10月31日 下午3:35:27
 */
public class RefecltUtils {

	private static final String SET_ = "set";

	/**
	 * 将属性的首字符大写，方便构造get，set方法
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月31日 下午3:35:46
	 * @param name
	 * @return
	 */
	public static String returnSetName(String name) {// 将属性的首字符大写，方便构造get，set方法
		return SET_ + name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}
