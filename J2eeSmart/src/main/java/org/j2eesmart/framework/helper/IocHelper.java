package org.j2eesmart.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.j2eesmart.framework.annotation.Inject;
import org.j2eesmart.framework.util.CollectionUtil;
import org.j2eesmart.framework.util.ReflectionUtil;

/**
 * 依赖注入助手
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午2:05:26
 */
public final class IocHelper {
	static {
		// 获取所有的Bean映射
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				// 获取Bean的所有的Field
				Field[] fields = beanClass.getDeclaredFields();
				if (CollectionUtil.isNotEmpty(fields)) {
					for (Field field : fields) {
						if (field.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = field.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
