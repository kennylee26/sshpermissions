/**   
  * @Title: CreateIDException.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:48:41 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: CreateIDException 
 * @Description: 创建ID异常 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:48:41 
 *  
 */
public class CreateIDException extends IDException{

	private static final long serialVersionUID = 1L;

	public CreateIDException() {
		super();
	}

	public CreateIDException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateIDException(String message) {
		super(message);
	}

	public CreateIDException(Throwable cause) {
		super(cause);
	}

}