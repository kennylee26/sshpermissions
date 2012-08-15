/**   
  * @Title: RequestUtil.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:54:32 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/** 
 * @ClassName: RequestUtil 
 * @Description: 请求处理类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:54:32 
 *  
 */
public class RequestUtil {
    /**
     * Maps lowercase JSP scope names to their PageContext integer constant values. 
     */
    private static Map scopes = new HashMap();
	
	private RequestUtil(){
	}
	  static {
	        scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
	        scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
	        scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
	        scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
	    }		
	
	  /**
	     * Converts the scope name into its corresponding PageContext constant value.
	     * @param pScopeName Can be "page", "request", "session", or "application" in any
	     * case.
	     * @return The constant representing the scope (ie. PageContext.REQUEST_SCOPE).
	     * @throws JspException if the scopeName is not a valid name.
	     * @since Struts 1.1
	     */
	    public static int getScope(String pScopeName) throws JspException {
			Integer scope = (Integer) scopes.get(pScopeName.toLowerCase());
			if (scope == null) {
				throw new JspException("Invalid bean scope:" + pScopeName);
			}
			return scope.intValue();
	    }
		
		 /**
	     * Locate and return the specified bean, from an optionally specified
	     * scope, in the specified page context.  If no such bean is found,
	     * return <code>null</code> instead.  If an exception is thrown, it will
	     * have already been saved via a call to <code>saveException()</code>.
	     *
	     * @param pageContext Page context to be searched
	     * @param name Name of the bean to be retrieved
	     * @param scopeName Scope to be searched (page, request, session, application)
	     *  or <code>null</code> to use <code>findAttribute()</code> instead
	     * @return JavaBean in the specified page context
	     * @exception JspException if an invalid scope name
	     *  is requested
	     */
	    public static Object lookup(PageContext pageContext, String name, String scopeName)
	        throws JspException {
	        if (scopeName == null) {
	        	return pageContext.findAttribute(name);
	        }
	        try {
	        	return pageContext.getAttribute(name, getScope(scopeName));
	        
	        } catch (JspException e) {
	        	throw e;
	        }

	    }	
		
		/**
		 * 
		 * @param pValue
		 * @return
		 */
		public static  boolean isEmpty(String pValue){
			if ( pValue == null){
				return true;
			}
			if ( "null".equalsIgnoreCase(pValue.trim()) ){
				return true;
			}
			
			if ( "".equalsIgnoreCase(pValue.trim()) ){
				return true;
			}
			
			return false;
			
		}
		
		/**
		 * 获取url路径
		 * @param pUrl
		 * @param pRequest
		 * @return
		 */
		public static String getUrl(String pUrl, HttpServletRequest pRequest){
			if ( pUrl == null ){
				return null;
			}
			if ( pRequest == null ){
				return null;
			}
			String contextPath = pRequest.getContextPath();
			/**
			 * @todo: 1:判断url格式，是否跨域url,如果是，不需要加contextpath
			 *        2:
			 */
            return contextPath + adjustUrl(pUrl);			
		}
		
		public static String getUrl(String pUrl, WebContext pWebContext){
			if ( pUrl == null ){
				return null;
			}
			if ( pWebContext == null ){
				return null;
			}
			String contextPath = pWebContext.getContextPath();
			/**
			 * @todo: 1:判断url格式，是否跨域url,如果是，不需要加contextpath
			 *        2:
			 */
            return contextPath + adjustUrl(pUrl);			
		}
		

		/**
		 * 给未添加"/"url加上"/"
		 * @param pUrl
		 * @return
		 */
		public static String adjustUrl(String pUrl){
			if ( pUrl == null ){
				return null;
			}
			//如果pUrl没有带"/"，则加上.
			final String SPLIT = "/";
			  int index = pUrl.indexOf(SPLIT);
			   if ( index < 0 ){
				   return  SPLIT + pUrl;
			   }else{
				   return  pUrl;
			   }			
		}
		
		
}
