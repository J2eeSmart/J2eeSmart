package org.j2eesmart.framework.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;

/**
 * 字符串工具类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 上午10:25:49
 */
public final class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}

	/**
	 * 判断字符串是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 分割字符串为Map</br>
	 * String str = "a=b;c=d";</br>
	 * Map<String,String> map = splitString(str,";","=");</br>
	 * 
	 * @param str
	 * @param dem
	 * @return
	 */
	public static Map<String, ? extends Object> splitString(String str, String dem1, String dem2) {
		Map<String, String> split = Splitter.on(dem1).omitEmptyStrings().withKeyValueSeparator(dem2).split(str);
		return split;
	}
}
