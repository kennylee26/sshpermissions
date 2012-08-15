/**   
  * @Title: UncodeException.java 
  * @Package com.tgyt.tree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:59:00 
  * @version V1.0   
  */

package com.tgyt.tree;

/** 
 * @ClassName: UncodeException 
 * @Description: 可解码异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:59:00 
 *  
 */
public class UncodeException extends CreateTreeModelException{

	private static final long serialVersionUID = 1L;

	public UncodeException() {
		super("给节点解码异常");
	} 

	public UncodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UncodeException(String message) {
		super(message);
	}

	public UncodeException(Throwable cause) {
		super(cause);
	}
}
