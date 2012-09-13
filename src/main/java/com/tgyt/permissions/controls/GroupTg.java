package com.tgyt.permissions.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IGroupService;
import com.tgyt.permissions.common.HasParent;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.Systems;
import com.tgyt.permissions.model.Users;
import com.tgyt.permissions.tree.Utils;
import com.tgyt.tree.Node;
import com.tgyt.tree.UncodeException;
import com.tgyt.tree.UserDataUncoder;
import com.tgyt.tree.support.AbstractWebTreeModelCreator;
import com.tgyt.tree.support.WebTreeNode;


/** 
  * @ClassName: GroupTg 
  * @Description: 组信息控制层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:12:04 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("groupControl")
public class GroupTg extends BaseTg {
	/** 
	  * @Fields groupService : IGroupService接口spring注入 
	  */ 
	@Autowired
	private IGroupService groupService;
	
	/** 
	  * @Fields group : struts2.0自动接受
	  */ 
	private Groups group;
	
	/** 
	  * @Fields id : 要删除的id
	  */ 
	private String id;
	/** 
	  * @Fields userIds : 需要加到组里面的用户id
	  */ 
	private String userIds;
	/** 
	  * @Fields roleIds : 需要加到组里面的角色id
	  */ 
	private String roleIds;
	/** 
	  * @Fields groupid : 被加到的组
	  */ 
	private String groupid;
	
	
	public void getTree(){
		List<Map<String,Object>> list = this.groupService.getTree(id);
		outJsonPlainString(response, JSONArray.fromObject(list).toString());
	}
	
	/** 
	  * @Title: removeGroupHaveRole 
	  * @Description: 移除该组的某些角色
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void removeGroupHaveRole(){
		if(null != roleIds){
			this.groupService.removeGroupHaveRole(groupid, roleIds);
		}
		returnJsion("{\"success\":\"true\"}",response);
	}
	
	/** 
	  * @Title: groupHaveRole 
	  * @Description: 查看该组所拥有的角色
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void groupHaveRole(){
		List<Role> list = this.groupService.groupHaveRole(groupid);
		JSONArray jsonArray = JSONArray.fromObject(doRole(list));
		returnJsion(jsonArray.toString(),response);
	}
	
	/** 
	  * @Title: addRoleToGroup 
	  * @Description: 往组里添加角色
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void addRoleTo(){
		if(roleIds != null){
			this.groupService.addRoleToGroup(roleIds, groupid);
		}
		
		returnJsion("{\"success\":\"true\"}",response);
	}
	
	/** 
	  * @Title: otherRoleFromGroup 
	  * @Description: 我要获取该组所没有的角色
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void otherRoleFrom(){
		List<Role> list = this.groupService.otherRoleFromGroup(groupid);
		JSONArray jsonArray = JSONArray.fromObject(doRole(list));
		returnJsion(jsonArray.toString(),response);
	}
	
	/** 
	  * @Title: delUserFromGroup 
	  * @Description: 从组里删除用户
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void delUserFrom(){
		this.groupService.delUserFromGroup(groupid, id);
		returnJsion("{\"success\":\"true\"}",response);
	}
	
	/** 
	  * @Title: addUserToGroup 
	  * @Description: 往组里添加用户
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void addUserTo(){
		if(null != userIds){
			this.groupService.addUserToGroup(userIds, groupid);
		}
		returnJsion("{\"success\":\"true\"}",response);
	}
	
	/** 
	  * @Title: all 
	  * @Description: 获得所有的组
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void all(){
		List<Groups> list = groupService.getAll();
		JSONArray jsonArray = JSONArray.fromObject(doOther(list));
		returnJsion(jsonArray.toString(),response);
	}
	
	/** 
	  * @Title: update 
	  * @Description: 更新组的信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		if(this.groupService.alter(group)){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}
	
	/** 
	  * @Title: find 
	  * @Description: 根据id查找组的信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void find(){
		Groups group = this.groupService.findById(Integer.valueOf(id));
		List<Groups> list = new ArrayList<Groups>();
		if(null != group){
			list.add(group);
		}
		JSONArray jsonArray = JSONArray.fromObject(doOther(list));
		
		if(null != group.getParentId()){
			Groups temp = this.groupService.findById(Integer.valueOf(group.getParentId()));
			returnJsion("{\"rows\":" + jsonArray.toString() + ",\"parentid\":\"" + temp.getId() + "\",\"parentName\":\"" + temp.getName() + "\"}",response);
		}else{
			returnJsion("{\"rows\":" + jsonArray.toString() + ",\"parentid\":\"\"}",response);
		}
	}
	
	/** 
	  * @Title: del 
	  * @Description: 根据id删除组的信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void del(){
		if(this.groupService.deleteById(Integer.valueOf(id))){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}
	
	/** 
	  * @Title: save 
	  * @Description: 保存一条组的记录
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		if(this.groupService.save(group)){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}
	
	/** 
	  * @Title: list 
	  * @Description: 获取所有组，并且用tree来显示
	  * @param @return
	  * @return String 一段<html>代码
	  * @throws 
	  */
	public void list(){
		List<Groups> list = groupService.getAll();
		// 业务数据解码器，从业务数据中分解出id和parentid
		UserDataUncoder orgUncoder = new UserDataUncoder() {
			public Object getID(Object pUserData) throws UncodeException {
				Groups org = (Groups) pUserData;
				return org.getId();
			}

			public Object getParentID(Object pUserData) throws UncodeException {
				Groups org = (Groups) pUserData;
				return org.getParentId();
			}
		};

		// Tree模型构造器，用于生成树模型
		AbstractWebTreeModelCreator treeModelCreator = new AbstractWebTreeModelCreator() {
			// 该方法负责将业务数据映射到树型节点
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Groups org = (Groups) pUserData;
				WebTreeNode result = new WebTreeNode(org.getName(), "node"+org.getId(),org);

				return result;
			}
		};
		String temp = Utils.showeasyuiTree(orgUncoder, treeModelCreator, list, request);
		outHtmlUTFString(response,temp);
	}

	/** 
	  * @Title: index 
	  * @Description: 组的首页信息
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		List<Groups> list = groupService.getAll();
		// 业务数据解码器，从业务数据中分解出id和parentid
		UserDataUncoder orgUncoder = new UserDataUncoder() {
			public Object getID(Object pUserData) throws UncodeException {
				Groups org = (Groups) pUserData;
				return org.getId();
			}

			public Object getParentID(Object pUserData) throws UncodeException {
				Groups org = (Groups) pUserData;
				return org.getParentId();
			}
		};

		// Tree模型构造器，用于生成树模型
		AbstractWebTreeModelCreator treeModelCreator = new AbstractWebTreeModelCreator() {
			// 该方法负责将业务数据映射到树型节点
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Groups org = (Groups) pUserData;
				WebTreeNode result = new WebTreeNode(org.getName(), "node"+org.getId(),org);
				return result;
			}
		};
		String temp = Utils.showeasyuiTree(orgUncoder, treeModelCreator, list, request);
		request.setAttribute("treeScript", temp);
		return this.SUCCESS;
	}

	/** 
	  * @Title: returnJsion 
	  * @Description: 解决json问题
	  * @param @param baseStr
	  * @param @param response
	  * @return void
	  * @throws 
	  */
	private void returnJsion(String baseStr, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(baseStr);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (out != null) {
				out.close();
			}
		}
		
	}
	
	/** 
	  * @Title: doOther 
	  * @Description: 解决struts2.0domal接受参数的方法
	  * @param @param list 装Groups的具体信息。
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doOther(List<Groups> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Groups group = list.get(i);
				map.put("name", group.getName());
				map.put("id", group.getId() + "");
				map.put("enName", group.getEnName());
				map.put("groupType", group.getGroupType());
				map.put("orderId", group.getOrderId());
				map.put("memo", group.getMemo());
				map.put("status", group.getStatus());
				lists.add(map);
			}
		}else{
			Map<String,String> map = new HashMap<String, String>();
			map.put("error", "error");
			lists.add(map);
		}
		return lists;
	}

	/** 
	  * @Title: doRole 
	  * @Description: 解决struts2.0domal接受参数的方法
	  * @param @param list 装Role的具体信息。
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doRole(List<Role> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Role group = list.get(i);
				map.put("name", group.getName());
				map.put("id", group.getId() + "");
				map.put("memo", group.getMemo());
				lists.add(map);
			}
		}
		return lists;
	}
	
	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IGroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
