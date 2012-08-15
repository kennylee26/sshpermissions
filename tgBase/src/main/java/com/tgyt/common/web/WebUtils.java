/**   
 * @Title: WebUtils.java 
 * @Package com.tgyt.common.web 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 上午10:32:11 
 * @version V1.0   
 */

package com.tgyt.common.web;

import org.springframework.util.Assert;

/**
 * @ClassName: WebUtils
 * @Description: 设置web根路径
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:32:11
 * 
 */
public class WebUtils {
	private WebUtils() {

	}

	private static String webAppRoot = null;

	public static String getWebAppRoot() {
		return webAppRoot;
	}

	static void setWebAppRoot(String pWebAppRoot) {
		Assert.notNull(pWebAppRoot, "webAppRoot must not be null");
		webAppRoot = pWebAppRoot;
	}

}