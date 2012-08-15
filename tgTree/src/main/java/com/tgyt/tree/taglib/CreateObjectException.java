/**   
  * @Title: CreateObjectException.java 
  * @Package com.tgyt.tree.taglib 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:22:07 
  * @version V1.0   
  */

package com.tgyt.tree.taglib;

/** 
 * @ClassName: CreateObjectException 
 * @Description: 创建对象异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:22:07 
 *  
 */
public class CreateObjectException extends java.lang.RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CreateObjectException() {
		super();
	}

	public CreateObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateObjectException(String message) {
		super(message);
	}

	public CreateObjectException(Throwable cause) {
		super(cause);
	}

}