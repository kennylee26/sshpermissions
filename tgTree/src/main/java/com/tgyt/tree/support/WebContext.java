/**   
  * @Title: WebContext.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:17:13 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.io.InputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

/** 
 * @ClassName: WebContext 
 * @Description:
 * 改接口的设计来自jmesa 
 * 主页：http://code.google.com/p/jmesa/
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:17:13 
 *  
 */
public interface WebContext {
	
	public InputStream getResourceAsStream(String pPath);
    public Object getApplicationInitParameter(String name);

    public Object getApplicationAttribute(String name);

    public void setApplicationAttribute(String name, Object value);

    public void removeApplicationAttribute(String name);

    public Object getPageAttribute(String name);

    public void setPageAttribute(String name, Object value);

    public void removePageAttribute(String name);

    public String getParameter(String name);

    public Map getParameterMap();
    
    public String getCharacterEncoding();

    public void setParameterMap(Map parameterMap);

    public Object getRequestAttribute(String name);

    public void setRequestAttribute(String name, Object value);

    public void removeRequestAttribute(String name);

    public Object getSessionAttribute(String name);

    public void setSessionAttribute(String name, Object value);

    public void removeSessionAttribute(String name);

    public Writer getWriter();

    public Locale getLocale();

    public void setLocale(Locale locale);

    public String getContextPath();
    
    public String getRealPath(String path);
}
