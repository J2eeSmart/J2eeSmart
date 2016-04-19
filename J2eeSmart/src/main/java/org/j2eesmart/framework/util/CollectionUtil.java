package org.j2eesmart.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 集合工具类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 上午10:26:00
 */
public final class CollectionUtil {

	/**
	 * 判断集合是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}

	/**
	 * 判断数组是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}

	/**
	 * 判断集合是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断MAP是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return MapUtils.isEmpty(map);
	}

	/**
	 * 判断MAP是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
}
