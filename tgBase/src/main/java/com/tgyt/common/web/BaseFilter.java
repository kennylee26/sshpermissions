/**   
  * @Title: BaseFilter.java 
  * @Package com.tgyt.common.web 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午10:23:45 
  * @version V1.0   
  */

package com.tgyt.common.web;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/** 
 * @ClassName: BaseFilter 
 * @Description: 基本过滤器
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:23:45 
 *  
 */
public abstract class BaseFilter implements Filter{
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	protected FilterConfig filterConfig = null;
	
	public void init(FilterConfig pFilterConfig) throws ServletException {
		Assert.notNull(pFilterConfig, "FilterConfig must not be null");		
        filterConfig = pFilterConfig;
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing filter '" + filterConfig.getFilterName() + "'");
		}
         doInit();
     	if (logger.isDebugEnabled()) {
			logger.debug("Filter '" + filterConfig.getFilterName() + "' configured successfully");
		}         
	}
	protected void doInit() throws ServletException{
	}
	 
	public void destroy() {
	}
}
