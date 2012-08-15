/**   
  * @Title: Template.java 
  * @Package com.tgyt.templateEngine 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:57:57 
  * @version V1.0   
  */

package com.tgyt.templateEngine;

/** 
 * @ClassName: Template 
 * @Description: 模板接口
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:57:57 
 *  
 */
public interface Template {
	
	public String getInputEncoding();
	public void setInputEncoding(String inputEncoding);

	/**
	 * 字符串资源前缀
	 */
	public static final String STR_PREFIX = "str:";
	
	public String getResource();
	public void setResource(String resource);
}