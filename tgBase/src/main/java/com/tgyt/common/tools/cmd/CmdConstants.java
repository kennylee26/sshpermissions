/**   
 * @Title: CmdConstants.java 
 * @Package com.tgyt.common.tools.cmd.test 
 * @Description: 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:40:31 
 * @version V1.0   
 */
package com.tgyt.common.tools.cmd;

import com.tgyt.Constants;

/**
 * @ClassName: CmdConstants
 * @Description: 用java文件去执行管理maven的dos命令
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:45:58
 * 
 */
public interface CmdConstants extends Constants {
	/**
	 * 找到pom.xml來執行处理依赖关系命令
	 */
	String MAVEN_CMD_LOADDEPARPANDANCY = "mvn eclipse:eclipse -Dwtpversion=1.5";
	/**
	 * 创建工程的常量
	 */
	String MAVEN_CMD_CREATEPROJECT = "mvn archetype:generate -DgroupId={0} -DartifactId={1} -DarchetypeArtifactId=maven-archetype-{2} -Dversion=1.0";
	/**
	 * 导入工程的常量
	 */
	String MAVEN_CMD_IMPORTPROJECT = "mvn eclipse:eclipse -Dwtpversion=1.0";

}
