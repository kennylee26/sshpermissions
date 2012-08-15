/**   
 * @Title: ReadConfigation.java 
 * @Package com.tgyt.common.tools.configation 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-2 上午08:23:02 
 * @version V1.0   
 */

package com.tgyt.common.tools.configation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @ClassName: ReadConfigation
 * @Description: 动态读取加载配置文件
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-2 上午08:23:02
 * 
 */
public class ReadConfigation {

	/**
	 * @Fields PFILE : 属性文件全名
	 */
	private final String PFILE = "/tgConfig.properties";

	/**
	 * @Fields m_file : 对应于属性文件的文件对象变量
	 */
	private File m_file = null;

	/**
	 * @Fields m_lastModifiedTime : 属性文件的最后修改日期
	 */
	private long m_lastModifiedTime = 0;

	/**
	 * @Fields m_props : 属性文件所对应的属性对象变量
	 */
	private Properties m_props = null;

	/**
	 * @Fields m_instance : 本类可能存在的惟一的一个实例
	 */
	private static ReadConfigation m_instance = new ReadConfigation();

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 私有的构造子，用以保证外界无法直接实例化
	 * </p>
	 */
	private ReadConfigation() {

		String fileName = ReadConfigation.class.getResource(PFILE).getPath();
		fileName = fileName.substring(1, fileName.length());

		// for linux or unix
		if (!fileName.substring(0, 1).equals("/")
				&& !fileName.substring(1, 2).equals(":")) {
			fileName = "/" + fileName;
		}

		m_file = new File(fileName);
		m_lastModifiedTime = m_file.lastModified();
		if (m_lastModifiedTime == 0) {
			System.err.println(PFILE + " file does not exist!");
		}

		m_props = new Properties();
		try {
			m_props.load(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: getInstance
	 * @Description: 静态工厂方法
	 * @param
	 * @return 返还ReadConfigation 类的单一实例
	 * @return ReadConfigation
	 * @throws
	 */
	synchronized public static ReadConfigation getInstance() {
		return m_instance;
	}

	/**
	 * @Title: getConfigItem
	 * @Description: 读取一特定的属性项
	 * @param
	 * @param name
	 *            属性项的项名
	 * @param
	 * @param defaultVal
	 *            属性项的默认值
	 * @param
	 * @return 属性项的值（如此项存在）， 默认值（如此项不存在）
	 * @return String
	 * @throws
	 */
	public String getConfigItem(String name, String defaultVal) {
		long newTime = m_file.lastModified();
		// 检查属性文件是否被其他程序
		// 如果是，重新读取此文件

		if (newTime == 0) {
			// 属性文件不存在
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			// Get rid of the old properties
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;
		String val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	/**
	 * @Title: getConfigItem
	 * @Description: 读取一特定的属性项
	 * @param
	 * @param name
	 *            属性项的项名
	 * @param
	 * @return 属性项的值（如此项存在）， 空（如此项不存在）
	 * @return String
	 * @throws
	 */
	public String getConfigItem(String name) {
		return getConfigItem(name, "");
	}

}
