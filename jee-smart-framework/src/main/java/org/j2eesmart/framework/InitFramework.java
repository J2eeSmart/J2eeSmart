package org.j2eesmart.framework;

import org.j2eesmart.framework.helper.BeanHelper;
import org.j2eesmart.framework.helper.ClassLoaderHelper;
import org.j2eesmart.framework.helper.ControllerHelper;
import org.j2eesmart.framework.helper.IocHelper;
import org.j2eesmart.framework.util.ClassLoaderUtil;

/**
 * 初始化框架类
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月19日 下午1:20:41
 */
public final class InitFramework {

	/**
	 * 初始化框架
	 */
	public static void init() {
		Class<?>[] classList = { ClassLoaderHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class };
		for (Class<?> cls : classList) {
			ClassLoaderUtil.loadClass(cls.getName(), true);
		}
	}

}
