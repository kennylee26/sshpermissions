/**   
  * @Title: JspPageWebContext.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:52:18 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.io.InputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public final class JspPageWebContext implements WebContext {
    private PageContext pageContext;
    private Map parameterMap;
    private Locale locale;

    public JspPageWebContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public Object getApplicationInitParameter(String name) {
        return pageContext.getServletContext().getInitParameter(name);
    }

    public Object getApplicationAttribute(String name) {
        return pageContext.getServletContext().getAttribute(name);
    }

    public void setApplicationAttribute(String name, Object value) {
        pageContext.getServletContext().setAttribute(name, value);
    }

    public void removeApplicationAttribute(String name) {
        pageContext.getServletContext().removeAttribute(name);
    }

    public Object getPageAttribute(String name) {
        return pageContext.getAttribute(name);
    }

    public void setPageAttribute(String name, Object value) {
        pageContext.setAttribute(name, value);
    }

    public void removePageAttribute(String name) {
        pageContext.removeAttribute(name);
    }

    public String getParameter(String name) {
        return pageContext.getRequest().getParameter(name);
    }

    public Map getParameterMap() {
        if (parameterMap != null) {
            return parameterMap;
        }

        return pageContext.getRequest().getParameterMap();
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Object getRequestAttribute(String name) {
        return pageContext.getRequest().getAttribute(name);
    }

    public void setRequestAttribute(String name, Object value) {
        pageContext.getRequest().setAttribute(name, value);
    }

    public void removeRequestAttribute(String name) {
        pageContext.getRequest().removeAttribute(name);
    }

    public Object getSessionAttribute(String name) {
        return pageContext.getSession().getAttribute(name);
    }

    public void setSessionAttribute(String name, Object value) {
        pageContext.getSession().setAttribute(name, value);
    }

    public void removeSessionAttribute(String name) {
        pageContext.getSession().removeAttribute(name);
    }

    public Writer getWriter() {
        return pageContext.getOut();
    }

    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }

        return pageContext.getRequest().getLocale();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getContextPath() {
        ServletRequest request = pageContext.getRequest();
        if (request instanceof HttpServletRequest) {
            return ((HttpServletRequest) request).getContextPath();
        }

        throw new UnsupportedOperationException("There is no context path associated with the request.");
    }
    
    public String getRealPath(String path) {
        if (pageContext.getRequest() instanceof HttpServletRequest) {
            return ((HttpServletRequest) pageContext.getRequest()).getSession().getServletContext().getRealPath(path);
        }

        throw new UnsupportedOperationException("There is no real path associated with the request.");
    }


	public InputStream getResourceAsStream(String pPath) {
		return pageContext.getServletContext().getResourceAsStream(pPath);
	}

	public String getCharacterEncoding() {
        if (pageContext.getRequest() instanceof HttpServletRequest) {
            return ((HttpServletRequest) pageContext.getRequest()).getCharacterEncoding();
        }

        throw new UnsupportedOperationException("There is no real path associated with the request.");
	}
}
