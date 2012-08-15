/**   
  * @Title: CreateTreeModelException.java 
  * @Package com.tgyt.tree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:54:02 
  * @version V1.0   
  */

package com.tgyt.tree;

/** 
 * @ClassName: CreateTreeModelException 
 * @Description: 创建树异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:54:02 
 *  
 */
public class CreateTreeModelException extends TreeException{
	
	private static final long serialVersionUID = 1L;

	public CreateTreeModelException() {
		super("创建树异常");
	}

	public CreateTreeModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateTreeModelException(String message) {
		super(message);
	}

	public CreateTreeModelException(Throwable cause) {
		super(cause);
	}

}
