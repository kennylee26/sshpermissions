/**   
  * @Title: InitFreeMarkerEngineException.java 
  * @Package com.tgyt.templateEngine.freemarker 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:04:03 
  * @version V1.0   
  */

package com.tgyt.templateEngine.freemarker;

/** 
 * @ClassName: InitFreeMarkerEngineException 
 * @Description: 初始化freemarker engine异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:04:03 
 *  
 */
public class InitFreeMarkerEngineException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InitFreeMarkerEngineException() {
		super();
	}

	public InitFreeMarkerEngineException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InitFreeMarkerEngineException(String arg0) {
		super(arg0);
	}

	public InitFreeMarkerEngineException(Throwable arg0) {
		super(arg0);
	}
}
