/**   
  * @Title: TreeException.java 
  * @Package com.tgyt.tree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:52:56 
  * @version V1.0   
  */

package com.tgyt.tree;

/** 
 * @ClassName: TreeException 
 * @Description: tree异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:52:56 
 *  
 */
public class TreeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TreeException() {
		super("构造树出错!");
	}

	public TreeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TreeException(String message) {
		super(message);
	}

	public TreeException(Throwable cause) {
		super(cause);
	}
}