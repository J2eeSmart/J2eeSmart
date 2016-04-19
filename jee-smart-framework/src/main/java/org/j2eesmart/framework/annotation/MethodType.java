package org.j2eesmart.framework.annotation;

/**
 * 请求方法枚举
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月19日 下午1:08:09
 */
public enum MethodType {
	GET, POST;
	public static MethodType buildMethodType(String methodType) {
		if ("GET".equals(methodType.toUpperCase())) {
			return MethodType.GET;
		} else if ("POST".equals(methodType.toUpperCase())) {
			return MethodType.POST;
		}

		throw new RuntimeException("methodType：" + methodType + " 没有该HTTP方法请求.");
	}
}
