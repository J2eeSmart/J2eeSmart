package org.j2eesmart.framework;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.j2eesmart.framework.helper.ConfigHelper;

/**
 * 中央控制器
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午3:10:52
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 7756105913244297128L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化框架
		InitFramework.init();

		ServletContext servletContext = config.getServletContext();
		// 注册处理JSP的Servlet
		ServletRegistration jspRegistration = servletContext.getServletRegistration("jsp");
		jspRegistration.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 注册处理静态文件的Servlet
		ServletRegistration defaultRegistration = servletContext.getServletRegistration("default");
		defaultRegistration.addMapping(ConfigHelper.getAppAssertPath() + "*");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

}
