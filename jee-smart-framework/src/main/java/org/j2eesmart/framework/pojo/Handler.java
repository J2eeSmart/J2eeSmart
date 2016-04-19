package org.j2eesmart.framework.pojo;

import java.lang.reflect.Method;

/**
 * 封装Action的信息
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午2:43:02
 */
public class Handler {
	/**
	 * Controller的类
	 */
	private Class<?> controllerClass;

	/**
	 * Action方法
	 */
	private Method actionMethod;

	public Handler(Class<?> controllerClass, Method actionMethod) {
		super();
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

}
