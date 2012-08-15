/**   
  * @Title: MultiRootNodeException.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:53:00 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import com.tgyt.tree.CreateTreeModelException;

/** 
 * @ClassName: MultiRootNodeException 
 * @Description: 多根结点
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:53:00 
 *  
 */
public class MultiRootNodeException extends CreateTreeModelException{
	 
	private static final long serialVersionUID = 1L;

	public MultiRootNodeException() {
		super("存在多个跟节点");
	}

	public MultiRootNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MultiRootNodeException(String message) {
		super(message);
	}

	public MultiRootNodeException(Throwable cause) {
		super(cause);
	}


}