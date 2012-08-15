/**   
  * @Title: InitVelocityEngineException.java 
  * @Package com.tgyt.templateEngine.velocity 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:21:45 
  * @version V1.0   
  */

package com.tgyt.templateEngine.velocity;

/** 
 * @ClassName: InitVelocityEngineException 
 * @Description: 初始化velocity异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:21:45 
 *  
 */
public class InitVelocityEngineException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InitVelocityEngineException() {
		super();
	}

	public InitVelocityEngineException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InitVelocityEngineException(String arg0) {
		super(arg0);
	}

	public InitVelocityEngineException(Throwable arg0) {
		super(arg0);
	}


}
