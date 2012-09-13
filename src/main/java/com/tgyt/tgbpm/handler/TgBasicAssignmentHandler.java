/**   
  * @Title: TgBasicAssignmentHandler.java 
  * @Package com.tgyt.tgbpm.handler 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2012-1-12 下午1:45:50 
  * @version V1.0   
  */

package com.tgyt.tgbpm.handler;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.jbpm.pvm.internal.env.EnvironmentImpl;

import com.tgyt.tgbpm.exception.TaskNotAssignedException;
import com.tgyt.tgbpm.tools.factory.AssignmentHandlerFactory;
import com.tgyt.tgbpm.tools.modules.IAssignmentHandler;

/** 
 * @ClassName: TgBasicAssignmentHandler 
 * @Description:该类处理工作流的Task任务人员分配的基本定义类 
 * @author sunct sunchaotong18@163.com 
 * @date 2012-1-12 下午1:45:50 
 *  
 */
public class TgBasicAssignmentHandler implements AssignmentHandler {


	public void assign(Assignable assignable, OpenExecution execution) throws Exception {
//		//获得当前activity所属的流程定义ID
//		String processDefId = execution.getProcessDefinitionId();
//		//获得当前activity的name属性
//		String taskName = execution.getActivity().getName();
		//获取spring管理的分配工厂bean
		AssignmentHandlerFactory factory = EnvironmentImpl.getFromCurrent(AssignmentHandlerFactory.class);
		//获得任务分配实例
		IAssignmentHandler handler = factory.getAssignmentHandlerInstance();
		String assignee = handler.assign(execution);
		//如果候选人为空，就抛出异常
		if(assignee==null || "".equals(assignee)){
			throw new TaskNotAssignedException(execution.getActivity().getName()+"任务未找到分配人");
		}
		assignable.setAssignee(assignee);
	}

}
