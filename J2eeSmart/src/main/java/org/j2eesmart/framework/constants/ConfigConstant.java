package org.j2eesmart.framework.constants;

/**
 * 提供相关配置项常量
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 上午11:30:53
 */
public interface ConfigConstant {

	/**
	 * 框架配置文件
	 */
	String CONFIG_FILE = "smart.properties";
	/**
	 * JDBC数据库驱动
	 */
	String JDBC_DRIVER = "smart.framework.jdbc.driver";
	/**
	 * 数据库URL
	 */
	String JDBC_URL = "smart.framework.jdbc.url";
	/**
	 * 数据库用户名
	 */
	String JDBC_USERNAME = "smart.framework.jdbc.username";
	/**
	 * 数据库密码
	 */
	String JDBC_PASSWORD = "smart.framework.jdbc.password";
	/**
	 * 框架扫描基础包
	 */
	String APP_BASE_PACKAGE = "smart.framework.app.base_package";
	/**
	 * jsp界面路径
	 */
	String APP_JSP_PATH = "smart.framework.app.jsp_path";
	/**
	 * 静态文件路径
	 */
	String APP_ASSERT_PATH = "smart.framework.app.assert_path";
}
