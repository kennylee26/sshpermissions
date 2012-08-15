/**   
  * @Title: CreateSequnceException.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:52:07 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: CreateSequnceException 
 * @Description: 创建序列异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:52:07 
 *  
 */
public class CreateSequnceException extends IDException{

	private static final long serialVersionUID = 1L;

	public CreateSequnceException() {
		super();
	}

	public CreateSequnceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateSequnceException(String message) {
		super(message);
	}

	public CreateSequnceException(Throwable cause) {
		super(cause);
	}

}