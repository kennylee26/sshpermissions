/**   
  * @Title: FormatSequenceExcepiton.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:52:41 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: FormatSequenceExcepiton 
 * @Description: 格式化序列异常
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:52:41 
 *  
 */
public class FormatSequenceExcepiton extends IDException{

	public FormatSequenceExcepiton() {
		super("格式化序号异常!");
	}

	public FormatSequenceExcepiton(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatSequenceExcepiton(String message) {
		super(message);
	}

	public FormatSequenceExcepiton(Throwable cause) {
		super(cause);
	}

}