/**   
  * @Title: LoadResourcesServlet.java 
  * @Package com.tgyt.common.id.loader 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:18:01 
  * @version V1.0   
  */

package com.tgyt.common.id.loader;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgyt.common.resource.loader.ResourcesLoader;

/** 
 * @ClassName: LoadResourcesServlet 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:18:01 
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

