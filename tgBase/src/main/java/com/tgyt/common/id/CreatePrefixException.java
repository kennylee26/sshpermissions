/**   
  * @Title: CreatePrefixException.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:50:52 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: CreatePrefixException 
 * @Description: 创建文件异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:50:52 
 *  
 */
public class CreatePrefixException extends IDException{
	
	private static final long serialVersionUID = 1L;

	public CreatePrefixException() {
		super();
	} 

	public CreatePrefixException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreatePrefixException(String message) {
		super(message);
	}

	public CreatePrefixException(Throwable cause) {
		super(cause);
	}
}
