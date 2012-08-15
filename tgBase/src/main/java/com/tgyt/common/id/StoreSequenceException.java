/**   
  * @Title: StoreSequenceException.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:57:34 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: StoreSequenceException 
 * @Description: 保存序列异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:57:34 
 *  
 */
public class StoreSequenceException extends IDException{

	private static final long serialVersionUID = 1L;

	public StoreSequenceException() {
		super();
	}

	public StoreSequenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public StoreSequenceException(String message) {
		super(message);
	}

	public StoreSequenceException(Throwable cause) {
		super(cause);
	}

}
