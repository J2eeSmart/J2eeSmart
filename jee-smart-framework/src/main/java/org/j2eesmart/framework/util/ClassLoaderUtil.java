package org.j2eesmart.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * 类加载工具类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月19日 上午10:50:03
 */
public final class ClassLoaderUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderUtil.class);

	public static void main(String[] args) {
		ClassLoaderUtil.getClassSet("org.j2eesmart.framework");
	}

	/**
	 * 获取类加载器
	 * 
	 * @return ClassLoader
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	/**
	 * 加载类(提高加载性能可以将initialize设置为false)
	 * 
	 * @param className
	 * @param initialize
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean initialize) {
		Class<?> cls;
		try {
			cls = Class.forName(className, initialize, getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure.", e);
			throw new RuntimeException(e);
		}
		return cls;
	}

	/**
	 * 获取指定包下所有的类
	 * 
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName) {
		Preconditions.checkNotNull(packageName, "packageName can not null.");
		Set<Class<?>> classSet = Sets.newHashSet();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if ("file".equals(protocol)) {
						// 删除空格
						String packagePath = url.getPath().replaceAll("%20", "");
						addClass(classSet, packagePath, packageName);
					} else if ("jar".equals(protocol)) {
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if (jarURLConnection != null) {
							JarFile jarFile = jarURLConnection.getJarFile();
							if (jarFile != null) {
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while (jarEntries.hasMoreElements()) {
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if (jarEntryName.endsWith(".class")) {
										String className = jarEntryName.substring(0, jarEntryName.lastIndexOf('.'))
												.replaceAll("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("get class set failure", e);
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});

		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf('.'));
				if (StringUtil.isNotEmpty(className)) {
					className = packageName + "." + className;
				}
				doAddClass(classSet, className);
			} else {
				String subPackagePath = fileName;
				if (StringUtil.isNotEmpty(subPackagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtil.isNotEmpty(subPackageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}
}
