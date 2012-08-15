/**   
  * @Title: SystemUtils.java 
  * @Package com.tgyt.common.tools.util 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午10:53:30 
  * @version V1.0   
  */

package com.tgyt.common.tools.util;

/** 
 * @ClassName: SystemUtils 
 * @Description: 获取工程路径
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:53:30 
 *  
 */
public class SystemUtils {
	/**
	 * 获取项目文件路径
	 * @return 项目文件路径
	 */
	public static String getPrjPath(){
		return System.getProperty("user.dir");
	}
}
