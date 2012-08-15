/**   
  * @Title: LoadResourcesException.java 
  * @Package com.tgyt.common.resource.loader 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午11:25:58 
  * @version V1.0   
  */

package com.tgyt.common.resource.loader;

/** 
 * @ClassName: LoadResourcesException 
 * @Description: 加载资源文件异常 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午11:25:58 
 *  
 */
public class LoadResourcesException extends RuntimeException {

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
