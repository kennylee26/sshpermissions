/**   
 * @Title: BatPath.java 
 * @Package com.tgyt.common.tools.bat 
 * @Description: 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 下午3:39:19 
 * @version V1.0   
 */

package com.tgyt.common.tools.bat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import com.tgyt.common.tools.cmd.RunCmd;

/**
 * @ClassName: BatPath
 * @Description: 执行bat命令的方法
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 下午3:39:19
 * 
 */
public class BatPath {
	static Logger logger = Logger.getLogger(BatPath.class.getName());

	/**
	 * @Title: batPath
	 * @Description: 在某个指定的路径下去执行一个bat
	 * @param @param path
	 * @return void
	 * @throws
	 */
	public static void batPath(String path, String batName) {
		String line = "";
		try {
			String rootPath = System.getenv("SystemRoot");
			String[] command = new String[] {
					rootPath + "\\system32\\cmd.exe",
					"/K",
					(new File(getClassFilePath(BatPath.class))).getParent()
							+ "\\" + batName };
			File dir = new File(path);
			Process process = Runtime.getRuntime().exec(command, null, dir);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			while ((line = br.readLine()) != null) {
				logger.info(line);
			}
			process.getOutputStream().close();

		} catch (Exception e) {
			final String msg = "你输入的路径错误，请重新输入路径";
			throw new ExcuteBatException(msg, e);
		}
	}

	/**
	 * @Title: getClassFile
	 * @Description: 取得当前类所在的文件
	 * @param @param clazz
	 * @param @return
	 * @return File
	 * @throws
	 */

	public static File getClassFile(Class clazz) {
		URL path = clazz.getResource(clazz.getName().substring(
				clazz.getName().lastIndexOf(".") + 1)
				+ ".classs");
		if (path == null) {
			String name = clazz.getName().replaceAll("[.]", "/");
			path = clazz.getResource("/" + name + ".class");
		}
		return new File(path.getFile());
	}

	/**
	 * @Title: getClassFilePath
	 * @Description: 得到当前类的路径
	 * @param @param clazz
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getClassFilePath(Class clazz) {
		try {
			return java.net.URLDecoder.decode(getClassFile(clazz)
					.getAbsolutePath(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
