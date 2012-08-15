/**   
  * @Title: MergeTemplateException.java 
  * @Package com.tgyt.templateEngine 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:56:45 
  * @version V1.0   
  */

package com.tgyt.templateEngine;

/** 
 * @ClassName: MergeTemplateException 
 * @Description: 合并模板异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:56:45 
 *  
 */
public class MergeTemplateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MergeTemplateException() {
		super();
	}

	public MergeTemplateException(String message, Throwable cause) {
		super(message, cause);
	}

	public MergeTemplateException(String message) {
		super(message);
	}

	public MergeTemplateException(Throwable cause) {
		super(cause);
	}


}
