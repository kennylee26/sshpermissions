/**   
 * @Title: ExcuteCmdException.java 
 * @Package com.tgyt.common.tools.cmd.test 
 * @Description: 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:40:31 
 * @version V1.0   
 */
package com.tgyt.common.tools.cmd;

/**
 * @ClassName: ExcuteCmdException
 * @Description: 执行命令异常
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:46:35
 * 
 */
public class ExcuteCmdException extends java.lang.RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcuteCmdException() {
		super();
	}

	/**
	 * Title: ExcuteCmdException Description: 用一个字符串去显示异常
	 * @param message
	 * @param cause
	 */
	public ExcuteCmdException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcuteCmdException(String message) {
		super(message);
	}

	public ExcuteCmdException(Throwable cause) {
		super(cause);
	}

}