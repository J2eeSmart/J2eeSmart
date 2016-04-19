package org.j2eesmart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午1:07:24
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

	/**
	 * 请求映射
	 * 
	 * @return
	 */
	public String urlPath();

	/**
	 * 请求方法,默认为GET请求
	 * 
	 * @return
	 */
	public MethodType methodType() default MethodType.GET;
}
