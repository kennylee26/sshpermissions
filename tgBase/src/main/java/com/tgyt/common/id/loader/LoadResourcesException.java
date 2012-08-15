/**   
  * @Title: LoadResourcesException.java 
  * @Package com.tgyt.common.id.loader 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:16:16 
  * @version V1.0   
  */

package com.tgyt.common.id.loader;

import com.tgyt.common.id.IDException;

/** 
 * @ClassName: LoadResourcesException 
 * @Description: 加载资源异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:16:16 
 *  
 */
public class LoadResourcesException extends IDException{

	private static final long serialVersionUID = 1L;

	public LoadResourcesException() {
		super();
	}

	public LoadResourcesException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoadResourcesException(String message) {
		super(message);
	}

	public LoadResourcesException(Throwable cause) {
		super(cause);
	}

}