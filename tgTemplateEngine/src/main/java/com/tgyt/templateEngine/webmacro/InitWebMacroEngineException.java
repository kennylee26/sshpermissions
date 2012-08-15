/**   
  * @Title: InitWebMacroEngineException.java 
  * @Package com.tgyt.templateEngine.webmacro 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:25:08 
  * @version V1.0   
  */

package com.tgyt.templateEngine.webmacro;

/** 
 * @ClassName: InitWebMacroEngineException 
 * @Description: 初始化macro engine异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:25:08 
 *  
 */
public class InitWebMacroEngineException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InitWebMacroEngineException() {
		super();
	}

	public InitWebMacroEngineException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InitWebMacroEngineException(String arg0) {
		super(arg0);
	}

	public InitWebMacroEngineException(Throwable arg0) {
		super(arg0);
	}


}