package org.j2eesmart.framework.helper;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.j2eesmart.framework.annotation.Controller;
import org.j2eesmart.framework.annotation.Service;
import org.j2eesmart.framework.util.ClassLoaderUtil;

import com.google.common.collect.Sets;

/**
 * 类加载助手
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午1:14:44
 */
public final class ClassLoaderHelper {
	private static final Set<Class<?>> CLASS_SET;

	static {
		String basePackageName = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassLoaderUtil.getClassSet(basePackageName);
	}

	/**
	 * 获取基础应用包下所有的类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	 * 获取所有的Service注解类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet() {
		return getAnnotationClassSet(Service.class);
	}

	/**
	 * 获取所有的Controller注解类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet() {
		return getAnnotationClassSet(Controller.class);
	}

	/**
	 * 获取所有所有注解类(Service、Controller)
	 * 
	 * @param annotation
	 * @return
	 */
	public static Set<Class<?>> getAnnotationClassSet(Class<? extends Annotation> annotation) {
		Set<Class<?>> classSet = Sets.newHashSet();
		for (Class<?> clazz : CLASS_SET) {
			if (clazz.isAnnotationPresent(annotation)) {
				classSet.add(clazz);
			}
		}
		return classSet;
	}
}
