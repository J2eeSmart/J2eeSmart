package org.j2eesmart.framework.helper;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.j2eesmart.framework.annotation.Action;
import org.j2eesmart.framework.annotation.MethodType;
import org.j2eesmart.framework.pojo.Handler;
import org.j2eesmart.framework.pojo.SmartRequest;
import org.j2eesmart.framework.util.CollectionUtil;

import com.google.common.collect.Maps;

/**
 * 控制器助手类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午2:47:06
 */
public class ControllerHelper {
	/**
	 * 请求和映射之间的关系
	 */
	private static final Map<SmartRequest, Handler> ACTION_MAP = Maps.newHashMap();

	static {
		Set<Class<?>> controllerClassSet = ClassLoaderHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			// 遍历所有的Controller类
			for (Class<?> controller : controllerClassSet) {
				// 获取controller定义的方法
				Method[] declaredMethods = controller.getDeclaredMethods();
				if (CollectionUtil.isNotEmpty(declaredMethods)) {
					for (Method method : declaredMethods) {
						// 方法使用Action注解
						if (method.isAnnotationPresent(Action.class)) {
							Action action = method.getAnnotation(Action.class);
							String urlPath = action.urlPath();
							MethodType methodType = action.methodType();
							SmartRequest request = new SmartRequest(methodType, urlPath);
							Handler handler = new Handler(controller, method);
							ACTION_MAP.put(request, handler);
						}
					}
				}
			}
		}
	}

	/**
	 * 获取处理类
	 * 
	 * @param urlPath
	 * @param methodType
	 * @return
	 */
	public static Handler getHandler(String urlPath, MethodType methodType) {
		SmartRequest request = new SmartRequest(methodType, urlPath);
		return ACTION_MAP.get(request);
	}
}
