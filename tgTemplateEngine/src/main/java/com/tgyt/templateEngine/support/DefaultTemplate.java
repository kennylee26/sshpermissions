/**   
  * @Title: DefaultTemplate.java 
  * @Package com.tgyt.templateEngine.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:15:36 
  * @version V1.0   
  */

package com.tgyt.templateEngine.support;

import com.tgyt.templateEngine.Template;

/** 
 * @ClassName: DefaultTemplate 
 * @Description: 默认模板
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:15:36 
 *  
 */
public class DefaultTemplate implements Template{
	
	/**
	 * 模板文件路径.
	 */
	private String resource = null;
	
	/**
	 * 输入编码方式,如果值为空，引擎会采用默认的编码方式
	 */
	private String inputEncoding = null;



	public String getInputEncoding() {
		if ( inputEncoding == null ){
			return System.getProperty("file.encoding");
		}else{
		  return inputEncoding;
		}
	}

	public void setInputEncoding(String inputEncoding) {
		this.inputEncoding = inputEncoding;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
