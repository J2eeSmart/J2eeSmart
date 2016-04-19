package org.j2eesmart.framework.helper;

import java.util.Map;
import java.util.Set;

import org.j2eesmart.framework.util.ReflectionUtil;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Bean容器 助手
 * 
 * @author qinghua.wu
 * @since  1.0.0
 * @date 2016年4月19日 下午1:43:18
 */
public final class BeanHelper {
	private static final Map<Class<?>, Object> BEAN_MAP;

	static {
		BEAN_MAP = Maps.newHashMap();
		Set<Class<?>> allClass = Sets.newHashSet();
		allClass.addAll(ClassLoaderHelper.getControllerClassSet());
		allClass.addAll(ClassLoaderHelper.getServiceClassSet());
		for (Class<?> cls : allClass) {
			Object obj = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, obj);
		}
	}

	/**
	 * 获取Bean的映射
	 * 
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}

	/**
	 * 获取bean
	 * 
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:" + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
}
