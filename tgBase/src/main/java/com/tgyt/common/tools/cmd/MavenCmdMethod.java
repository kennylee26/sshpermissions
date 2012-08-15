/**   
 * @Title: MavenCmdMethod.java 
 * @Package com.tgyt.common.tools.cmd.test 
 * @Description: 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:40:31 
 * @version V1.0   
 */
package com.tgyt.common.tools.cmd;

import java.io.File;
import java.text.MessageFormat;

/**
 * @ClassName: MavenCmdMethod
 * @Description: 用java文件去执行管理maven的dos命令
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:47:50
 * 
 */

public class MavenCmdMethod {

	/**
	 * @Title: loadDeparpandancy
	 * @Description: 用java代替dos命令去加载依赖关系
	 * @param
	 * @return void
	 * @throws
	 */
	public static void loadDeparpandancy() {
		RunCmd.pathCmd((new File(System.getProperty("user.dir"))).getParent(),
				CmdConstants.MAVEN_CMD_LOADDEPARPANDANCY);
	}

	/**
	 * @Title: importProject
	 * @Description: 导入工程的方法
	 * @param
	 * @return void
	 * @throws
	 */
	public static void importProject() {
		RunCmd.pathCmd((new File(System.getProperty("user.dir"))).getParent(),
				CmdConstants.MAVEN_CMD_IMPORTPROJECT);
	}

	/**
	 * @Title: cmdCreate
	 * @Description: 执行配置的命令
	 * @param @param packageName
	 * @param @param projectName
	 * @return void
	 * @throws
	 */
	public static void cmdCreate(String packageName, String projectName,
			String arg) {
		RunCmd.enterCmd((new File(System.getProperty("user.dir"))).getParent(),
				MavenCmdMethod.create(packageName, projectName, arg));
	}

	/**
	 * @Title: create
	 * @Description: 用传参的方式来构建命令
	 * @param @param packageName
	 * @param @param projectName
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String create(String packageName, String projectName,
			String arg) {
		MessageFormat form = new MessageFormat(
				CmdConstants.MAVEN_CMD_CREATEPROJECT);
		Object[] testArgs = { packageName, projectName, arg };
		return form.format(testArgs);
	}
}
