/**   
  * @Title: WebTreeBuilder.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:16:46 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import javax.servlet.http.HttpServletRequest;

import com.tgyt.tree.TreeBuilder;

/** 
 * @ClassName: WebTreeBuilder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:16:46 
 *  
 */
public interface WebTreeBuilder extends TreeBuilder{
	public void init(WebContext pWebContext);
	/**
	 * @deprecated, 使用 public void init(WebContext pWebContext);
	 * @param pRequest
	 */
	public void init(HttpServletRequest pRequest);
	public String getTreeScript();
}
