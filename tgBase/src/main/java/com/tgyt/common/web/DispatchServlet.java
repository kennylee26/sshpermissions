/**   
  * @Title: DispatchServlet.java 
  * @Package com.tgyt.common.web 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午10:29:32 
  * @version V1.0   
  */

package com.tgyt.common.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/** 
 * @ClassName: DispatchServlet 
 * @Description: 模拟dispatchAction，实现基本功能
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:29:32 
 *  
 */
public abstract class DispatchServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected ServletConfig servletConfig = null;
	
	 /**
     * The Class instance of this <code>DispatchServlet</code> class.
     */
    protected Class clazz = this.getClass();
    
    private final Log log = LogFactory.getLog( clazz );
	
    /**
     * The set of Method objects we have introspected for this class,
     * keyed by method name.  This collection is populated as different
     * methods are called, so that introspection needs to occur only
     * once per method name.
     */
    protected Map methods = new HashMap();


    /**
     * The set of argument type classes for the reflected method call.  These
     * are the same for all calls, so calculate them only once.
     */
    protected Class types[] = {    
        HttpServletRequest.class, HttpServletResponse.class 
     };
	
	
	public void init(ServletConfig pServletConfig) throws ServletException {
	  this.servletConfig = 	pServletConfig;
	}
	
	/**
	 * Identify the method name to be dispatched to.
	 * @return
	 */
	protected String getMethodParameter(){
		return "_actionType";
	}
	
	protected void service(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        String name = pRequest.getParameter(getMethodParameter());
        if ( name == null ){
        	final String MSG =
        		"没有包含参数名为:'" + getMethodParameter() + "'的参数";
        	log.error(MSG);
        	throw new ServletException(MSG);
        }
        try {
			dispatchMethod(pRequest, pResponse, name);
		} catch (Exception e) {
			log.error(e);
			throw new ServletException(e);
		}
    }
	

	  /**
     * Dispatch to the specified method.
     * @since Struts 1.1
     */
     protected void dispatchMethod(         HttpServletRequest request,
                                            HttpServletResponse response,
                                            String name) throws Exception {
        // Identify the method object to be dispatched to
        Method method = null;
        try {
            method = getMethod(name);
        } catch (NoSuchMethodException e) {
        	final String MSG =
        		this.clazz.getName() + "类没有定义方法:'"+name+"'";
            log.error(MSG, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               MSG);
            return;
        }

        
        try {
            Object args[] = { request, response };
             method.invoke(this, args);
        } catch (ClassCastException e) {
        	final String MSG =
        		"调用方法：'" + name + "'失败!";
            log.error(MSG, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               MSG);
            return; 
        } catch (IllegalAccessException e) {
        	final String MSG =
        		"调用方法：'" + name + "'失败!";
            log.error(MSG, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               MSG);
            return; 
        } catch (InvocationTargetException e) {
            // Rethrow the target exception if possible so that the
            // exception handling machinery can deal with it
            Throwable t = e.getTargetException();
            if (t instanceof Exception) {
                throw ((Exception) t);
            } else {
            	final String MSG =
            		"调用方法：'" + name + "'失败!";
                log.error(MSG, e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                   MSG);
            }
        }
    }


    /**
     * Introspect the current class to identify a method of the specified
     * name that accepts the same parameter types as the <code>execute</code>
     * method does.
     *
     * @param name Name of the method to be introspected
     *
     * @exception NoSuchMethodException if no such method can be found
     */
    protected Method getMethod(String name)
        throws NoSuchMethodException {

        synchronized (methods) {
            Method method = (Method) methods.get(name);
            if (method == null) {
                method = clazz.getMethod(name, types);
                methods.put(name, method);
            }
            return (method);
        }

    }
	
		
}