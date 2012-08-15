/**   
  * @Title: BuildTreeException.java 
  * @Package com.tgyt.tree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:52:29 
  * @version V1.0   
  */

package com.tgyt.tree;

/** 
 * @ClassName: BuildTreeException 
 * @Description: 构造树异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:52:29 
 *  
 */
public class BuildTreeException extends TreeException{
	private static final long serialVersionUID = 1L;
	 
	public BuildTreeException() {
		super("构造树异常");
	}

	public BuildTreeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildTreeException(String message) {
		super(message);
	}

	public BuildTreeException(Throwable cause) {
		super(cause);
	}
	
}
