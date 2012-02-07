package com.tgyt.permissions.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IGroupService;
import com.tgyt.permissions.biz.IUserService;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Users;

/** 
  * @ClassName: UserTg 
  * @Description: 用户控制层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:30:21 
  *  
  */
/** 
  * @ClassName: UserTg 
  * @Description: TODO(这里用一句话描述这个类的作用) 
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2011-11-26 上午10:18:01 
  *  
  */
/** 
  * @ClassName: UserTg 
  * @Description: TODO(这里用一句话描述这个类的作用) 
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2011-11-26 上午10:18:05 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("userControl")
public class UserTg extends BaseTg{
	/** 
	  * @Fields userService : IUserService接口spring注入
	  */ 
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	/** 
	  * @Fields user : struts2.0自动接收
	  */ 
	private Users user;
	
	/** 
	  * @Fields id : 删除、更新用户所需的id，用户添加到组里面的用户id
	  */ 
	private String id;
	
	/** 
	  * @Fields groupId : 用户添加到组里面的组id
	  */ 
	private String groupId;
	private String groupName;
	/** 
	  * @Fields backgroundImage : 修改背景图片时的图片名 
	  */ 
	private String backgroundImage;
	
	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	
	
	/** 
	  * @Title: index 
	  * @Description: 用户首页
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return this.SUCCESS;
	}
	
	/** 
	  * @Title: find 
	  * @Description: 根据用户id查找用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void find(){
		Users user = this.userService.findById(Integer.valueOf(id));
		List<Users> list = new ArrayList<Users>();
		if(null != user){
			list.add(user);
		}
		JSONArray jsonArray = JSONArray.fromObject(doOther(list));
		//现在一个用户只能有一个组
		Iterator<Groups> it = user.getGroups().iterator();
		
		
		while(it.hasNext()){
			Groups temp = it.next();
			returnJsion("{\"rows\":" + jsonArray.toString() + ",\"parentid\":\"" + temp.getId() + "\",\"parentName\":\"" + temp.getName() + "\"}",response);
			return;
		}
		returnJsion("{\"rows\":" + jsonArray.toString() + ",\"parentid\":\"\",\"parentName\":\"\"}",response);
		
	}
	
	/** 
	  * @Title: save 
	  * @Description: 保存用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		user.setRegisterdate(date);
		
		if(null != groupId && !"".equals(groupId)){
			if(this.userService.saveToGroup(groupId, user)){
				returnJsion("{\"success\":\"true\"}",response);
			}else{
				returnJsion("{\"error\":\"true\"}",response);
			}
		}else{
			if(this.userService.save(user)){
				returnJsion("{\"success\":\"true\"}",response);
			}else{
				returnJsion("{\"error\":\"true\"}",response);
			}
		}
		
		
	}
	
	/** 
	  * @Title: del 
	  * @Description: 根据id删除用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void del(){
		if(this.userService.deleteById(Integer.valueOf(id))){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}
	
	/** 
	  * @Title: getPage 
	  * @Description: 获取用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getPage(){
		Pagination p =  this.userService.getPageList(user, page, rows,sort,order);
		
		List<Users> list = p.getList();
		List lists = doOther(list);	
		JSONArray jsonArray = JSONArray.fromObject(lists);
		String baseStr = "{\"total\":" + p.getTotalCount() + ",\"rows\":";
		baseStr = baseStr + jsonArray.toString() + "}";
		returnJsion(baseStr,response);
	}
	
	/** 
	  * @Title: getGroupPage 
	  * @Description: 根据组名查人员
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getGroupPage(){
		if(groupName==null || "".equals(groupName)){
			Pagination p =  this.userService.getPageList(user, page, rows,sort,order);
			List<Users> list = p.getList();
			List lists = doOther(list);	
			JSONArray jsonArray = JSONArray.fromObject(lists);
			String baseStr = "{\"total\":" + p.getTotalCount() + ",\"rows\":";
			baseStr = baseStr + jsonArray.toString() + "}";
			returnJsion(baseStr,response);
		}else{
			List<Groups> groupList = groupService.getGroups(groupName);
			List<Users> list = new ArrayList<Users>();
			List lists = new ArrayList();
			Map<String,String> map = new HashMap<String,String>();
			for(Groups group : groupList){
				Set<Users> user =  group.getUsers();
				for(Users u : user){
					list.add(u);
				}
			}
			lists = doOther(list);
			JSONArray jsonArray = JSONArray.fromObject(lists);
			String baseStr = "{\"total\":" + lists.size() + ",\"rows\":";
			baseStr = baseStr + jsonArray.toString() + "}";
			returnJsion(baseStr,response);
		}
	}
	
	
	/** 
	  * @Title: update 
	  * @Description: 更新用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		if(null != groupId && !"".equals(groupId)){
			if(this.userService.alterToGroup(user,groupId)){
				returnJsion("{\"success\":\"true\"}",response);
			}else{
				returnJsion("{\"error\":\"true\"}",response);
			}
		}else{
			if(this.userService.alter(user)){
				returnJsion("{\"success\":\"true\"}",response);
			}else{
				returnJsion("{\"error\":\"true\"}",response);
			}
		}
		
		
	}
	/** 
	  * @Title: getAllUsers 
	  * @Description:获得所有用户的下拉列表JSON 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getAll(){
		try{
			List<Users> users = this.userService.getAll();
			List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
			for(Iterator<Users> iter=users.iterator();iter.hasNext();){
				Map<String,String> map = new HashMap<String,String>();
				Users user = iter.next();
				map.put("id", user.getId()+"");
				map.put("loginid", user.getLogonid());
				map.put("name", user.getName());
				maps.add(map);
			}
			JSONArray json = JSONArray.fromObject(maps);
			outJsonUTFString(response, json.toString());
		}catch(Exception e){
			e.printStackTrace();
			outJsonUTFString(response, "[]");
		}
	}
	/** 
	  * @Title: getOther 
	  * @Description: 往组里面添加用户的时候，除了本组里所有用户外，显示的用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getOther(){
		List<Users> all = this.userService.getAll();

		List<Users> grouPAll = this.userService.getGroupAll(Integer.valueOf(id));
		all.removeAll(grouPAll);
		
		JSONArray jsonArray = JSONArray.fromObject(doOther(all));
		returnJsion("{\"rows\":" + jsonArray.toString() +"}",response);
	}
	
	/** 
	  * @Title: getItems 
	  * @Description: 获取用户信息、获取具体组的用户信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		List<Users> list = null;
		if(null != id && !"".equals(id)){
			list = this.userService.getGroupAll(Integer.valueOf(id));
		}else{
			list = this.userService.getAll();
		}
		JSONArray json = JSONArray.fromObject(doList(list));
		String baseStr = "{\"total\":" + list.size() + ",\"rows\":";
		baseStr = baseStr + json.toString() + "}";
		returnJsion(baseStr,response);
	}
	
	/** 
	  * @Title: returnJsion 
	  * @Description: 返回jsion值
	  * @param @param string
	  * @param @param response
	  * @return void
	  * @throws 
	  */
	private void returnJsion(String string,HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(string);
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
	  * @param @param list
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doOther(List<Users> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Users user = list.get(i);
				map.put("address", user.getAddress());
				map.put("age", user.getAge());
				map.put("birthday", user.getBirthday());
				map.put("education", user.getEducation());
				map.put("employeddate", user.getEmployeddate());
				map.put("enname", user.getEnname());
				map.put("email", user.getEmail());
				map.put("lastlogoffdate", user.getLastlogoffdate());
				map.put("lastlogondate", user.getLastlogondate());
				map.put("lastlogonip", user.getLastlogonip());
				map.put("logonid", user.getLogonid());
				map.put("marriage", user.getMarriage());
				map.put("memo", user.getMemo());
				map.put("name", user.getName());
				
				map.put("groupName", (null != user.getGroups() && (user.getGroups().size() == 1))?((user.getGroups().iterator().next()).getName()) : "");
				
				map.put("jobmember", user.getJobmember());
				map.put("nativeplace", user.getNativeplace());
				map.put("orderid", user.getOrderid());
				map.put("password", user.getPassword());
				
				map.put("phone", user.getPhone());
				map.put("position", user.getPosition());
				map.put("registerdate", user.getRegisterdate());
				map.put("sex", user.getSex());
				map.put("status", user.getStatus());
				map.put("usertype", user.getUsertype());
				
				map.put("id", user.getId() + "");
				
				
				lists.add(map);
			}
		}
		return lists;
	}
	
	/** 
	  * @Title: doList 
	  * @Description: 解决struts2.0domal接受参数的方法
	  * @param @param list
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doList(List<Users> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Users user = list.get(i);
				map.put("address", user.getAddress());
				map.put("age", user.getAge());
				map.put("birthday", user.getBirthday());
				map.put("education", user.getEducation());
				map.put("employeddate", user.getEmployeddate());
				map.put("enname", user.getEnname());
				map.put("email", user.getEmail());
				map.put("lastlogoffdate", user.getLastlogoffdate());
				map.put("lastlogondate", user.getLastlogondate());
				map.put("lastlogonip", user.getLastlogonip());
				map.put("logonid", user.getLogonid());
				map.put("marriage", user.getMarriage());
				map.put("memo", user.getMemo());
				map.put("name", user.getName());
				
				map.put("nativeplace", user.getNativeplace());
				map.put("orderid", user.getOrderid());
				map.put("password", user.getPassword());
				
				map.put("phone", user.getPhone());
				map.put("position", user.getPosition());
				map.put("registerdate", user.getRegisterdate());
				map.put("sex", user.getSex());
				map.put("status", user.getStatus());
				map.put("usertype", user.getUsertype());
				
				map.put("id", user.getId() + "");
				
				
				lists.add(map);
			}
		}
		return lists;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	

}	
