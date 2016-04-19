package org.j2eesmart.framework.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.j2eesmart.framework.annotation.MethodType;

/**
 * 封装框架的请求信息
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午2:18:00
 */
public class SmartRequest {
	/**
	 * 请求方法类型
	 */
	public MethodType methodType;

	/**
	 * 请求方法路径
	 */
	public String urlPath;

	public SmartRequest(MethodType methodType, String urlPath) {
		super();
		this.methodType = methodType;
		this.urlPath = urlPath;
	}

	public MethodType getMethodType() {
		return methodType;
	}

	public String getUrlPath() {
		return urlPath;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
