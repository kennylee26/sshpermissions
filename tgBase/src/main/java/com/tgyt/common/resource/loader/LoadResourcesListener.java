/**   
  * @Title: LoadResourcesListener.java 
  * @Package com.tgyt.common.resource.loader 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午11:26:47 
  * @version V1.0   
  */

package com.tgyt.common.resource.loader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** 
 * @ClassName: LoadResourcesListener 
 * @Description: 加载资源文件
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午11:26:47 
 *  
 */
public class LoadResourcesListener implements ServletContextListener {
    
    
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		final String WEB_HOME = pServletContextEvent.getServletContext().getRealPath("/");
		ResourcesLoader.load(WEB_HOME);
	}

	public void contextDestroyed(ServletContextEvent pServletContextEvent) {
	}

}