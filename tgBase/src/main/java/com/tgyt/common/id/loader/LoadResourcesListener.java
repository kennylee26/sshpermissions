/**   
  * @Title: LoadResourcesListener.java 
  * @Package com.tgyt.common.id.loader 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:17:09 
  * @version V1.0   
  */

package com.tgyt.common.id.loader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tgyt.common.resource.loader.ResourcesLoader;

/** 
 * @ClassName: LoadResourcesListener 
 * @Description: 加载资源接口
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:17:09 
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
