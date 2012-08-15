/**   
  * @Title: InitSequenceGeneratorException.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:54:15 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: InitSequenceGeneratorException 
 * @Description: 自动序列生成异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:54:15 
 *  
 */
public class InitSequenceGeneratorException extends IDException{

	private static final long serialVersionUID = 1L;

	public InitSequenceGeneratorException() {
		super();
	}

	public InitSequenceGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitSequenceGeneratorException(String message) {
		super(message);
	}

	public InitSequenceGeneratorException(Throwable cause) {
		super(cause);
	}

}