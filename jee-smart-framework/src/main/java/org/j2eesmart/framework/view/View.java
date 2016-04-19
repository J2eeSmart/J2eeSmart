package org.j2eesmart.framework.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图接口
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月19日 下午4:25:43
 */
public interface View {
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
