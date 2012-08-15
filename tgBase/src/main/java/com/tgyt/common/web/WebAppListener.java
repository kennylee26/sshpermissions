/**   
  * @Title: WebAppListener.java 
  * @Package com.tgyt.common.web 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午10:31:20 
  * @version V1.0   
  */

package com.tgyt.common.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.Assert;
/** 
 * @ClassName: WebAppListener 
 * @Description: 设置web应用根路径 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:31:20 
 *  
 */
public class WebAppListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		Assert.notNull(event, "ServletContext must not be null");
		final String webRootPath = event.getServletContext().getRealPath("/");
		WebUtils.setWebAppRoot(webRootPath);
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}