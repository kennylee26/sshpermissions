/**   
  * @Title: TgBPMService.java 
  * @Package com.tgyt.tgbpm.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2011-12-19 下午4:31:38 
  * @version V1.0   
  */

package com.tgyt.tgbpm.biz;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.history.HistoryTaskQuery;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: TgBPMService 
 * @Description:工作流业务实现类 
 * @author sunct sunchaotong18@163.com 
 * @date 2011-12-19 下午4:31:38 
 *  
 */
@Service
public class TgBPMService  implements ITgBPMService {


	@Resource(name="processEngine")
	private ProcessEngine processEngine ;

	public ProcessInstance startProcess(String processDefinitionKey,
			String processInstanceKey, Map<String, Object> variables)
			throws Exception {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceByKey(processDefinitionKey, variables, processInstanceKey);
	}

	public List<Task> findPersonalTasks(String userName) throws Exception {
		if(userName==null || "".equals(userName) ){
			throw new Exception("未输入要查询的用户");
		}
		TaskService taskService = processEngine.getTaskService();
		List<Task> taskList = taskService.findPersonalTasks(userName);
		return taskList;
	}

	public void completeTask(String taskId, String outcome,
			Map<String, Object> variables) throws Exception {
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId,outcome, variables);
	}

	public ProcessInstance startProcess(String processDefinitionKey,
			String processInstanceKey) throws Exception {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceByKey(processDefinitionKey, processInstanceKey);
	}
	public void completeTask(String taskId) throws Exception {
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId);
	}

	public void completeTask(String taskId, String outcome) throws Exception {
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId,outcome);
	}

	public void completeTask(String taskId, Map<String, Object> variables)
			throws Exception {
		processEngine.getHistoryService().createHistoryTaskQuery().assignee("").orderDesc(HistoryTaskQuery.PROPERTY_CREATETIME).list();
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId,variables);
	}

	public List<ActivityCoordinates> getProcessActivityNodes(
			String processInstanceId) throws Exception {
		//获得部署service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessInstance processInstance = findProcessInstanceById(processInstanceId);
		//根据指定的流程实例，获得活动节点名称集合
		Set<String> activityNames = processInstance.findActiveActivityNames();
		List<ActivityCoordinates> acts = new ArrayList<ActivityCoordinates>();
		String processDefinitionId = processInstance.getProcessDefinitionId();
		if(activityNames!=null){
			for(Iterator<String> iter= activityNames.iterator();iter.hasNext();){
				ActivityCoordinates ac = repositoryService.getActivityCoordinates(processDefinitionId,iter.next());
				acts.add(ac);
			}
		}
		return acts;
	}

	public InputStream getResourceAsStream(String processInstanceId)
			throws Exception {
		//获得部署service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		//获得流程实例
		ProcessInstance processInstance = findProcessInstanceById(processInstanceId);
		//得到流程定义ID
		String processDefinitionId = processInstance.getProcessDefinitionId();
		//根据流程ID查询流程定义
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery().processDefinitionId(
						processDefinitionId).uniqueResult();
		//得到该流程定义图的流
		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),processDefinition.getImageResourceName());
		return inputStream;
	}

	
	/** 
	  * @Title: findProcessInstanceById 
	  * @Description:根据流程实例ID，获得对应的流程实例 
	  * @param @param processInstanceId
	  * @param @return
	  * @return ProcessInstance
	  * @throws 
	  */
	public ProcessInstance findProcessInstanceById(String processInstanceId)throws Exception{
		//获得执行service
		ExecutionService executionService = processEngine.getExecutionService();
		//得到指定ID的流程实例，如果ID为null，抛出异常
		ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
		return processInstance;
	}

	public List<HistoryTask> getPersionalHistoryTask(String asigneeName,String taskId,
			Date beginDate,Date endDate,Integer firstResult ,Integer maxResults,
			String order,String sort)throws Exception {
		HistoryTaskQuery historyTaskQuery = processEngine.getHistoryService().createHistoryTaskQuery();
		if(asigneeName==null || "".equals(asigneeName)){
			return null;
		}
		historyTaskQuery.assignee(asigneeName);
		if(taskId!=null && !"".equals(taskId)){
			historyTaskQuery.taskId(taskId);
		}
		if(beginDate==null){
			historyTaskQuery.startedAfter(beginDate);
		}
		if(endDate==null){
			historyTaskQuery.startedBefore(endDate);
		}
		if(firstResult==null){
			firstResult = TgBPMService.firstResult;
		}
		if(maxResults == null){
			maxResults = TgBPMService.maxResult;
		}
		if(null!=sort && !"".equals(sort)){
			if(TgBPMService.ASC.equals(order)){
				historyTaskQuery.page(firstResult, maxResults).orderAsc(sort);
			}else{
				historyTaskQuery.page(firstResult, maxResults).orderDesc(sort);
			}
		}
		return historyTaskQuery.list();
	}
	public List<HistoryActivityInstance> getHistoryActivityInstances(
			String processInstanceId) throws Exception {
		if(processInstanceId==null || "".equals(processInstanceId)){
			return null;
		}
		return processEngine.getHistoryService().createHistoryActivityInstanceQuery()
				.processInstanceId(processInstanceId).list();
	}
	
	
	public Task getTask(String processInstanceId,String userName)throws Exception{
		ProcessInstance processInstance = findProcessInstanceById(processInstanceId);
		if(processInstance==null){
			return null;
		}
		TaskService taskService = processEngine.getTaskService();
		TaskQuery query = taskService.createTaskQuery();
		if(processInstanceId==null || "".equals(processInstanceId)){
			throw new IllegalArgumentException("进程ID为NULL");
		}
		//根据流程实例ID查找
		query = query.processInstanceId(processInstance.getId());
		if(userName!=null && !"".equals(userName)){
			query = query.assignee(userName);
		}
		
		return query.uniqueResult();
	}
	
	public void asignTask(String taskId,String userName)throws Exception{
		TaskService taskService = processEngine.getTaskService();
		taskService.assignTask(taskId, userName);
	}
	
	public Object getVariable(String taskId,String variableName)throws Exception{
		TaskService taskService = processEngine.getTaskService();
		return taskService.getVariable(taskId, variableName);
	}
	
	public List<ActivityImpl> findTaskActivitiesByProcessDefId(String processDefinitionId)throws Exception{
		List<ProcessDefinition> processDef = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).list();
		if(processDef==null || processDef.size()!=1){
			throw new  Exception("未找到指定流程定义ID的实例");
		}
		List<ActivityImpl> activitis = (List<ActivityImpl>)((ProcessDefinitionImpl)processDef.iterator().next()).getActivities();
		List<ActivityImpl> taskActivitis = new ArrayList<ActivityImpl>();
		if(activitis!=null && activitis.size()>0){
			for(Iterator<ActivityImpl> iter=activitis.iterator();iter.hasNext();){
				ActivityImpl act = iter.next();
				if("task".equals(act.getType())){
//					TaskActivity taskActivity = (TaskActivity)act.getActivityBehaviour();
					taskActivitis.add(act);
				}
			}
		}
		return taskActivitis;
	}
	
	public List<Task> getPersionalTasks(String userName)throws Exception{
		TaskService taskService = processEngine.getTaskService();
		TaskQuery query = taskService.createTaskQuery();
		if(userName==null || "".equals(userName)){
			throw new IllegalArgumentException("请指定要查找的用户名！");
		}
		query.assignee(userName);
		return query.list();
	}
}
