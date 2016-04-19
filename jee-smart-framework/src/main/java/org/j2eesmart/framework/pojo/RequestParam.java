package org.j2eesmart.framework.pojo;

import java.util.Map;

import org.j2eesmart.framework.util.CastUtil;

/**
 * 请求的参数
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午4:18:14
 */
public class RequestParam {
	private Map<String, Object> reqParam;

	public RequestParam(Map<String, Object> reqParam) {
		super();
		this.reqParam = reqParam;
	}

	/**
	 * 将参数的值转换为long
	 * 
	 * @param name
	 * @return
	 */
	public long getLongValue(String name) {
		return CastUtil.castLong(reqParam.get(name));
	}

	/**
	 * 将参数的值转换为String
	 * 
	 * @param name
	 * @return
	 */
	public String getStringValue(String name) {
		return CastUtil.castString(reqParam.get(name));
	}

	public Map<String, Object> getReqParam() {
		return reqParam;
	}

}
