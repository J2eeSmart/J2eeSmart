package org.j2eesmart.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.j2eesmart.framework.annotation.MethodType;
import org.j2eesmart.framework.helper.BeanHelper;
import org.j2eesmart.framework.helper.ConfigHelper;
import org.j2eesmart.framework.helper.ControllerHelper;
import org.j2eesmart.framework.pojo.Handler;
import org.j2eesmart.framework.pojo.RequestParam;
import org.j2eesmart.framework.util.CodecUtil;
import org.j2eesmart.framework.util.ReflectionUtil;
import org.j2eesmart.framework.util.StreamUtil;
import org.j2eesmart.framework.util.StringUtil;
import org.j2eesmart.framework.view.View;

import com.google.common.collect.Maps;

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

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求方法和请求路径
		String reqMethod = req.getMethod();
		MethodType requestMethod = MethodType.buildMethodType(reqMethod);
		String requestPath = req.getPathInfo();
		// 获取Action Handler
		Handler handler = ControllerHelper.getHandler(requestPath, requestMethod);

		if (handler != null) {
			// 获取Controller和实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerInstance = BeanHelper.getBean(controllerClass);

			// 构建参数
			Map<String, Object> paramMap = Maps.newHashMap();
			Enumeration<String> parameterNames = req.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = req.getParameter(name);
				paramMap.put(name, value);
			}

			String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
			if (StringUtil.isNotEmpty(body)) {
				paramMap = (Map<String, Object>) StringUtil.splitString(body, "&", "=");
			}

			RequestParam param = new RequestParam(paramMap);
			// 调用Action的方法
			Method controllerMetod = handler.getActionMethod();
			Object result = ReflectionUtil.invokeMethod(controllerInstance, controllerMetod, param);
			View view = (View) result;
			view.process(req, resp);
		}
	}

}
