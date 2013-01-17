/**   
  * @Title: IAssignmentHandler.java 
  * @Package com.tgyt.tgbpm.tools.modules 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2012-1-13 上午10:52:56 
  * @version V1.0   
  */

package com.tgyt.tgbpm.tools.modules;

import org.jbpm.api.model.OpenExecution;

/** 
 * @ClassName: IAssignmentHandler 
 * @Description:工作流人员分配总接口，在工厂类中装配此接口的实例 
 * @author sunct sunchaotong18@163.com 
 * @date 2012-1-13 上午10:52:56 
 *  
 */
public interface IAssignmentHandler {

	/** 
	  * @Title: assign 
	  * @Description:根据当前工单task任务实例，分配人员到指定task 
	  * @param @param execution	当前任务执行实例
	  * @param @return	
	  * @param @throws Exception
	  * @return String	返回分配之后的人员
	  * @throws 
	  */
	public String assign(OpenExecution execution)throws Exception;
}
