/**   
  * @Title: BpmTaskUserTg.java 
  * @Package com.tgyt.tgbpm.controls 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-1-12 上午11:12:14 
  * @version V1.0   
  */

package com.tgyt.tgbpm.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.jbpm.pvm.internal.model.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IGroupService;
import com.tgyt.permissions.biz.IRoleService;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.Users;
import com.tgyt.tgbpm.biz.ITgBPMService;
import com.tgyt.tgbpm.biz.ITgTaskUserService;
import com.tgyt.tgbpm.model.TgBpmTaskUser;
/** 
 * @ClassName: BpmTaskUserTg 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-1-12 上午11:12:14 
 *  
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("tgbpm.bpmTaskUser")
public class BpmTaskUserTg extends BaseTg{
	@Autowired
	private ITgBPMService tgBPMService;
	@Autowired
	private ITgTaskUserService taskUserService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IGroupService groupService;
	private String processId;
	private TgBpmTaskUser bpmTaskUser;
	public String index(){
		return SUCCESS;
	}
	
	/** 
	  * @Title: getTask 
	  * @Description: 根据流程id获得任务列表
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getTask(){
		try {
			//System.out.println("********************"+processId);
			List<ActivityImpl> taskList = tgBPMService.findTaskActivitiesByProcessDefId(processId);
			List lists = new ArrayList();	
			for(ActivityImpl activityImpl : taskList ){
				Map<String,String> map = new HashMap<String, String>();
				String taskName = activityImpl.getName();
				TgBpmTaskUser bpmTaskUser = taskUserService.findTask(processId,taskName);
				if(bpmTaskUser != null){
					map.put("processId", bpmTaskUser.getProcessId());
					map.put("taskName", bpmTaskUser.getTaskName());
					map.put("userNames", bpmTaskUser.getUserNames());
					map.put("userIds", bpmTaskUser.getUserIds());
				}else{
					map.put("processId", processId);
					map.put("taskName", taskName);
					map.put("userNames", "");
					map.put("userIds", "");
				}
				lists.add(map);
			}
			
			JSONArray jsonArray = JSONArray.fromObject(lists);
			String baseStr = "{\"total\":" + taskList.size()+ ",\"rows\":";
			baseStr = baseStr + jsonArray.toString() + "}";
			outJsonPlainString(response, baseStr);
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/** 
	  * @Title: saveAssign 
	  * @Description:保存任务 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void saveAssign(){
		try{
			String dataAssignStr = request.getParameter("dataAssignStr");
			String[] dataStr = dataAssignStr.split(";");
			for(int i=0;i<dataStr.length;i++){
				String[] assignStr = dataStr[i].split(":");
				TgBpmTaskUser bpmTaskUser = taskUserService.findTask(assignStr[0],assignStr[3]);
				if(bpmTaskUser!= null){
					if("!".equals(assignStr[1])){
						bpmTaskUser.setUserIds("");
					}else{
						bpmTaskUser.setUserIds(assignStr[1]);	
					}
					if("!".equals(assignStr[2])){
						bpmTaskUser.setUserNames("");
					}else{
						bpmTaskUser.setUserNames(assignStr[2]);	
					}
					taskUserService.alter(bpmTaskUser);
				}else{
					bpmTaskUser = new TgBpmTaskUser();
					bpmTaskUser.setProcessId(assignStr[0]);
					if("!".equals(assignStr[1])){
						bpmTaskUser.setUserIds("");
					}else{
						bpmTaskUser.setUserIds(assignStr[1]);	
					}
					if("!".equals(assignStr[2])){
						bpmTaskUser.setUserNames("");
					}else{
						bpmTaskUser.setUserNames(assignStr[2]);	
					}
					bpmTaskUser.setTaskName(assignStr[3]);
					taskUserService.save(bpmTaskUser);
				}
			}
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/** 
	  * @Title: getRoleData 
	  * @Description: 根据角色得到人员
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getRoleData(){
		try{
		List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		String roleData = request.getParameter("roleData");
		Role role = roleService.findById(Integer.parseInt(roleData));
		Set<Groups> roleSet = role.getGroups();
		for(Groups group : roleSet){
			Set<Users> users = group.getUsers();
			for(Users user : users){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", user.getId()+"");
				map.put("loginid", user.getLogonid());
				map.put("name", user.getName());
				maps.add(map);
			}
		}
			JSONArray json = JSONArray.fromObject(maps);
			outJsonUTFString(response, json.toString());
		}catch(Exception e){
			e.printStackTrace();
			outJsonUTFString(response, "[]");
		}
		}
	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the bpmTaskUser
	 */
	public TgBpmTaskUser getBpmTaskUser() {
		return bpmTaskUser;
	}

	/**
	 * @param bpmTaskUser the bpmTaskUser to set
	 */
	public void setBpmTaskUser(TgBpmTaskUser bpmTaskUser) {
		this.bpmTaskUser = bpmTaskUser;
	}


	
}
