/**   
  * @Title: TgBPMService.java 
  * @Package com.tgyt.tgbpm.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2011-12-19 下午2:24:07 
  * @version V1.0   
  */

package com.tgyt.tgbpm.biz;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ActivityImpl;

/** 
 * @ClassName: TgBPMService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunct sunchaotong18@163.com 
 * @date 2011-12-19 下午2:24:07 
 *  
 */
public interface ITgBPMService {

	public static Integer firstResult = 0;
	
	public static Integer maxResult = 10;
	
	public static String ASC = "asc";
	
	public static String DESC = "desc";
	/** 
	  * @Title: startProcess 
	  * @Description:开始一个工作流程实例
	  * @param @param processDefinitionKey 流程定义key
	  * @param @param processInstanceKey 流程实例key,为自定义的唯一的key
	  * @param @param variables 开始流程时传入的变量集合
	  * @param @return
	  * @param @throws Exception
	  * @return ProcessInstance		开启的流程实例
	  * @throws 
	  */
	public ProcessInstance startProcess(String processDefinitionKey,String processInstanceKey,Map<String,Object> variables)throws Exception;
	
	/** 
	  * @Title: startProcess 
	  * @Description:开始一个工作流实例 
	  * @param @param processDefinitionKey	流程定义Key
	  * @param @param processInstanceKey	流程实例key
	  * @param @return
	  * @param @throws Exception
	  * @return ProcessInstance	开启的流程实例
	  * @throws 
	  */
	public ProcessInstance startProcess(String processDefinitionKey,String processInstanceKey)throws Exception;
	
	/** 
	  * @Title: findPersonalTasks 
	  * @Description:查询指定用户的任务集合 
	  * @param @param userName	要查询的用户
	  * @param @return
	  * @param @throws Exception
	  * @return List<Task>
	  * @throws 
	  */
	public List<Task> findPersonalTasks(String userName)throws Exception;
	
	
	/** 
	  * @Title: completeTask 
	  * @Description:完成指定ID的任务 
	  * @param @param taskId	任务ID
	  * @param @param outcome	任务的下一个流向，
	  * @param @param variables
	  * @param @throws Exception
	  * @return void
	  * @throws 
	  */
	public void completeTask(String taskId,String outcome,Map<String,Object> variables)throws Exception;
	
	/** 
	  * @Title: completeTask 
	  * @Description:完成指定ID的任务
	  * @param @param taskId	任务ID
	  * @param @throws Exception
	  * @return void
	  * @throws 
	  */
	public void completeTask(String taskId)throws Exception;
	
	/** 
	  * @Title: completeTask 
	  * @Description:完成指定ID的任务
	  * @param @param taskId	任务ID
	  * @param @param outcome	任务的下一个流向
	  * @param @throws Exception
	  * @return void
	  * @throws 
	  */
	public void completeTask(String taskId,String outcome)throws Exception;
	
	/** 
	  * @Title: completeTask 
	  * @Description:完成指定ID的任务
	  * @param @param taskId	任务ID
	  * @param @param variables	变量集合
	  * @param @throws Exception
	  * @return void
	  * @throws 
	  */
	public void completeTask(String taskId,Map<String,Object> variables)throws Exception;
	
	
	/** 
	  * @Title: getProcessActivityNodes 
	  * @Description:获得指定流程实例ID的活动节点集合
	  * @param @param processInstanceId	要查询的流程ID
	  * @param @return
	  * @param @throws Exception
	  * @return List<ActivityCoordinates> 包含活动节点实体的集合
	  * @throws 
	  */
	public List<ActivityCoordinates> getProcessActivityNodes(String processInstanceId)throws Exception;
	
	
	/** 
	  * @Title: getResourceAsStream 
	  * @Description:根据指定流程实例ID获得对应的流程图流
	  * @param @param processInstanceId	流程实例ID
	  * @param @return
	  * @param @throws Exception
	  * @return InputStream	流程图流
	  * @throws 
	  */
	public InputStream getResourceAsStream(String processInstanceId)throws Exception;
	
	
	/** 
	  * @Title: getPersionalHistoryTask 
	  * @Description:获得指定用户的历史Task
	  * @param @param asigneeName	用户名
	  * @param @return	
	  * @param @throws Exception
	  * @return List<HistoryTask>
	  * @throws 
	  */
	public List<HistoryTask> getPersionalHistoryTask(String asigneeName,String taskId,Date beginDate,Date endDate,Integer page ,Integer rows,String order,String sort)throws Exception;
	
	/** 
	  * @Title: getHistoryActivityInstances 
	  * @Description:根据指定流程实例ID，获得指定流程实例已经过的节点的完整列表
	  * @param @param processInstanceId	流程实例ID
	  * @param @return
	  * @param @throws Exception
	  * @return List<HistoryActivityInstance>
	  * @throws 
	  */
	public List<HistoryActivityInstance> getHistoryActivityInstances(String processInstanceId)throws Exception;
	
	
	/** 
	  * @Title: findProcessInstanceById 
	  * @Description:根据流程实例ID，获得指定的流程实例
	  * @param @param processInstanceId
	  * @param @return
	  * @return ProcessInstance
	  * @throws 
	  */
	public ProcessInstance findProcessInstanceById(String processInstanceId)throws Exception;
	
	/** 
	  * @Title: getTask 
	  * @Description:根据流程执行ID和用户名，查找指定的Task 
	  * @param @param executionId	执行ID，通过processInstance可以获得
	  * @param @param userName	用户名
	  * @param @return
	  * @return Task
	  * @throws 
	  */
	public Task getTask(String processInstanceId,String userName)throws Exception;
	
	/** 
	  * @Title: asignTask 
	  * @Description:用户接受指定ID的Task任务 
	  * @param @param taskId	要接受的TaskID
	  * @param @param userName	用户名
	  * @param @throws Exception
	  * @return void
	  * @throws 
	  */
	public void asignTask(String taskId,String userName)throws Exception;
	
	/** 
	  * @Title: getVariable 
	  * @Description:获得指定任务ID的指定变量值
	  * @param @param taskId	要查的任务ID
	  * @param @param variableName	变量名称
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	  * @throws 
	  */
	public Object getVariable(String taskId,String variableName)throws Exception;
	
	/** 
	  * @Title: findTaskActivitiesByProcessDefId 
	  * @Description:获得指定流程定义ID的Task类型的activity 
	  * @param @param processDefinitionId	要查询的流程定义ID
	  * @param @return
	  * @param @throws Exception
	  * @return List<ActivityImpl>
	  * @throws 
	  */
	public List<ActivityImpl> findTaskActivitiesByProcessDefId(String processDefinitionId)throws Exception;
	
	
	/** 
	  * @Title: getPersionalTasks 
	  * @Description:获得指定用户的所有task集合
	  * @param @param userName
	  * @param @return
	  * @param @throws Exception
	  * @return List<Task>
	  * @throws 
	  */
	public List<Task> getPersionalTasks(String userName)throws Exception;
}
