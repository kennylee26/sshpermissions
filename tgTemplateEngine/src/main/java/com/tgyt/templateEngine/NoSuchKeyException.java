/**   
  * @Title: NoSuchKeyException.java 
  * @Package com.tgyt.templateEngine 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:57:20 
  * @version V1.0   
  */

package com.tgyt.templateEngine;

/** 
 * @ClassName: NoSuchKeyException 
 * @Description: 没有对应的key异常 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:57:20 
 *  
 */
public class NoSuchKeyException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchKeyException() {
		super();
	}

	public NoSuchKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchKeyException(String pKey) {
		super("key:'" + pKey + "' 不存在!" );
	}

	public NoSuchKeyException(Throwable cause) {
		super(cause);
	}


}