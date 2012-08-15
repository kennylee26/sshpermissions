/**   
 * @Title: CharacterEncodingFilter.java 
 * @Package com.tgyt.common.web 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 上午10:28:29 
 * @version V1.0   
 */

package com.tgyt.common.web;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CharacterEncodingFilter
 * @Description: 基本过滤器类，默认是UTF-8
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:28:29
 * 
 */
public class CharacterEncodingFilter extends OncePerRequestFilter {

	public static final String DEFAULT_ENCODING = "utf-8";

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String strEncoding = filterConfig.getInitParameter("encoding");
		if (strEncoding == null) {//
			strEncoding = DEFAULT_ENCODING;
		}
		strEncoding = strEncoding.trim();
		if (strEncoding.length() == 0) {
			strEncoding = DEFAULT_ENCODING;
		}
		request.setCharacterEncoding(strEncoding);
		filterChain.doFilter(request, response);
	}

}