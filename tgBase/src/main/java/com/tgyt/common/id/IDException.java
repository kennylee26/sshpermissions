/**   
  * @Title: IDException.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:53:10 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: IDException 
 * @Description: id生成异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:53:10 
 *  
 */
public class IDException extends java.lang.RuntimeException{

	private static final long serialVersionUID = 1L;

	public IDException() {
		super("ID异常!");
	}

	public IDException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDException(String message) {
		super(message);
	}

	public IDException(Throwable cause) {
		super(cause);
	}

}