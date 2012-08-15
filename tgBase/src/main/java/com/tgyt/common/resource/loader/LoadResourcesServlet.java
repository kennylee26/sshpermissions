/**   
  * @Title: LoadResourcesServlet.java 
  * @Package com.tgyt.common.resource.loader 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:33:55 
  * @version V1.0   
  */

package com.tgyt.common.resource.loader;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 
 * @ClassName: LoadResourcesServlet 
 * @Description: 资源加载servlet
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:33:55 
 *  
 */
public class LoadResourcesServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		final String WEB_HOME = this.getServletContext().getRealPath("/");
		ResourcesLoader.load(WEB_HOME);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getServletContext().log(
				"Attempt to call service method on LoadResourcesServlet as [" +
				request.getRequestURI() + "] was ignored");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}
	

}
