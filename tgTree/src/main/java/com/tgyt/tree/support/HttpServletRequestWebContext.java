/**   
  * @Title: HttpServletRequestWebContext.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:19:26 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tgyt.common.tools.util.StringUtils;


/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class HttpServletRequestWebContext implements WebContext {
    private HttpServletRequest request;
    private Map parameterMap;
    private Locale locale;

    public HttpServletRequestWebContext(HttpServletRequest request) {
        this.request = request;
    }

    public Object getApplicationInitParameter(String name) {
        return request.getSession().getServletContext().getInitParameter(name);
    }

    public Object getApplicationAttribute(String name) {
        return request.getSession().getServletContext().getAttribute(name);
    }

    public void setApplicationAttribute(String name, Object value) {
        request.getSession().getServletContext().setAttribute(name, value);
    }

    public void removeApplicationAttribute(String name) {
        request.getSession().getServletContext().removeAttribute(name);
    }

    public Object getPageAttribute(String name) {
        return request.getAttribute(name);
    }

    public void setPageAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    public void removePageAttribute(String name) {
    	
        request.removeAttribute(name);
    }

    public String getParameter(String name) {
    	if ( name == null ){
    		return null;
    	}
        if (parameterMap != null) {
            String[] values = StringUtils.getValueAsArray(parameterMap.get(name));
            if (values != null && values.length > 0) {
                return values[0];
            }
        }
        System.out.println(request.getParameter(name)+ "333333333333");
        
        return request.getParameter(name);
    }

    public Map getParameterMap() {
        if (parameterMap != null) {
            return parameterMap;
        }

        return request.getParameterMap();
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Object getRequestAttribute(String name) {
        return request.getAttribute(name);
    }

    public void setRequestAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    public void removeRequestAttribute(String name) {
        request.removeAttribute(name);
    }

    public Object getSessionAttribute(String name) {
        return request.getSession().getAttribute(name);
    }

    public void setSessionAttribute(String name, Object value) {
        request.getSession().setAttribute(name, value);
    }

    public void removeSessionAttribute(String name) {
        request.getSession().removeAttribute(name);
    }

    public Writer getWriter() {
        return new StringWriter();
    }

    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }

        return request.getLocale();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getContextPath() {
        return request.getContextPath();
    }

    public String getRealPath(String path) {
        return request.getSession().getServletContext().getRealPath(path);
    }


	public InputStream getResourceAsStream(String pPath) {
		return request.getSession(true).getServletContext().getResourceAsStream(pPath);
	}

	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}
}
