/**   
  * @Title: TaskNotAssignedException.java 
  * @Package com.tgyt.tgbpm.exception 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2012-1-13 下午4:28:07 
  * @version V1.0   
  */

package com.tgyt.tgbpm.exception;

/** 
 * @ClassName: TaskNotAssignedException 
 * @Description:工作流Task未分配处理人员时报的异常
 * @author sunct sunchaotong18@163.com 
 * @date 2012-1-13 下午4:28:07 
 *  
 */
public class TaskNotAssignedException extends Exception {

	public TaskNotAssignedException() {
	}


	public TaskNotAssignedException(String message) {
		super(message);
	}

	public TaskNotAssignedException(Throwable cause) {
		super(cause);
	}


	public TaskNotAssignedException(String message, Throwable cause) {
		super(message, cause);
	}

}
