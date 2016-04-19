package org.j2eesmart.framework.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.j2eesmart.framework.helper.ConfigHelper;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * Jsp视图
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月19日 下午4:09:48
 */
public class JspView implements View {
	/**
	 * jsp路径
	 */
	private String jspPath;

	/**
	 * 模型数据
	 */
	private Map<String, Object> model = Maps.newHashMap();

	public JspView(String jspPath) {
		super();
		this.jspPath = jspPath;
	}

	/**
	 * 添加数据模型
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JspView addModel(String key, Object value) {
		model.put(key, value);
		return this;
	}

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Preconditions.checkNotNull(jspPath, "jsp path can not null.");
		// if (jspPath.startsWith("/")) {
		// resp.sendRedirect(req.getContextPath() + jspPath);
		// } else {
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			req.setAttribute(entry.getKey(), entry.getValue());
		}
		req.getRequestDispatcher(ConfigHelper.getAppJspPath() + jspPath).forward(req, resp);
		// }
	}

}
