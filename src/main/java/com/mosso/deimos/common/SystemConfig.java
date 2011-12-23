package com.mosso.deimos.common;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liubin
 *
 */
public class SystemConfig {
	
	// 记录日志
	protected static Logger logger = LoggerFactory.getLogger(SystemConfig.class);
	
	private static final String CONFIG_FILE = "config.properties";
	
	private SystemConfig() {}
	
	private static Properties props = new Properties();
	
	public static final String WORKSPACE_DIR = "workspaceDir";
	
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE));
		} catch (IOException e) {
			logger.error("读取初始化文件失败: " + CONFIG_FILE, e);
		}
	}
	
	/**
	 * 得到配置值
	 * @param configKey
	 * @return
	 */
	public static String getConfigValue(String configKey) {
		return props.getProperty(configKey);
	}
}
