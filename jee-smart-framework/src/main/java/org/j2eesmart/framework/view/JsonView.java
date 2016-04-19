package org.j2eesmart.framework.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.j2eesmart.framework.util.JsonUtil;

/**
 * Json视图
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 下午4:10:19
 */
public class JsonView implements View {

	private String contentType = "application/json";
	private String charEncoding = "UTF-8";
	private Object data;

	public JsonView(Object data) {
		super();
		this.data = data;
	}

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(contentType);
		resp.setCharacterEncoding(charEncoding);

		PrintWriter writer = resp.getWriter();
		String json = JsonUtil.toJson(this.data);
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
