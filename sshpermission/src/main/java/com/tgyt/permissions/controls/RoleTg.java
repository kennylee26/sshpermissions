/**   
  * @Title: RoleTg.java 
  * @Package com.tgyt.permissions.controls 
  * @Description: 
  * @author WangMing wang1988ming@qq.com 
  * @date 2011-9-23 下午2:58:37 
  * @version V1.0   
  */

package com.tgyt.permissions.controls;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.json.FormatJSON;
import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IResourcesService;
import com.tgyt.permissions.biz.IRoleService;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Role;

/** 
 * @ClassName: RoleTg 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author WangMing wang1988ming@qq.com
 * @date 2011-9-23 下午2:58:37 
 *  
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller(value="roleTgControl")
public class RoleTg extends BaseTg {
	/** 
	  * @Fields roleService : 角色服务接口
	  */ 
	@Resource(name="roleService")
	private IRoleService roleService;
	/** 
	  * @Fields resourcesService : 资源服务接口
	  */ 
	@Autowired
	private IResourcesService resourcesService;
	
	/** 
	  * @Title: index 
	  * @Description:角色信息模块索引函数
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return SUCCESS;
	}
	
	/** 
	  * @Title: getItems 
	  * @Description:获得角色列表 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		Pagination pagination = this.roleService.getPageList(this.getRole(), page, rows,sort,order);
		List<Role> roleList = pagination.getList();
		JsonConfig config = new JsonConfig();   
		 config.setIgnoreDefaultExcludes(false);      
		 config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);    
//		 config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd")); //date processor register   
		 config.setExcludes(new String[]{//只要设置这个数组，指定过滤哪些字段。   
		   "resources",   
		 }); 
		try {
			outJsonPlainString(response,"{\"total\":" + pagination.getTotalCount() + ",\"rows\":"+JSONSerializer.toJSON(roleList,config).toString() + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	  * @Title: saveAuthorizate 
	  * @Description: 给指定ID的角色分配资源 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void saveAuthorizate(){
		try{
			this.roleService.saveAuthorizate(id,rids);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/** 
	  * @Title: getAllTrees 
	  * @Description:根据角色ID获得获得所有资源树，其中角色有权限的资源checkbox为true  
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getAllTrees(){
		try {
			JSONArray json = JSONArray.fromObject(this.roleService.getAllListTrees(id));
			outJsonString(response,json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"[]");
		}
	}
	/** 
	  * @Title: save 
	  * @Description:新增一条角色记录 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
			if(this.roleService.addRole(role)){
				outJsonPlainString(response, "{\"success\":true}");
			}else{
				outJsonPlainString(response, "{\"error\":true}");
			}
	}
	/** 
	  * @Title: update 
	  * @Description: 修改角色记录 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
			if(this.roleService.alter(role)){
				outJsonPlainString(response, "{\"success\":true}");
			}else{
				outJsonPlainString(response, "{\"error\":true}");
			}
		
	}
	/** 
	  * @Title: getActions 
	  * @Description: 获得所有操作信息模块中的记录，做为给角色的资源添加操作页面的第一行
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getActions(){
		try{
			JSONArray json = JSONArray.fromObject(this.roleService.getAllActions());
			outHtmlUTFString(response,json.toString());
		}catch(Exception e){
			outJsonPlainString(response,"[]");
		}
	}
	/** 
	  * @Title: getRAMappings 
	  * @Description:获得当前角色拥有的资源树
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getRAMappings(){
		try {
			Role role = this.roleService.findById(id);
			if(role==null){
				
				outJsonString(response,"[]");
			}
			List list = this.resourcesService.getRoleRAMappings(id,role.getResources());
			JSONArray json = JSONArray.fromObject(list);
			outJsonString(response,json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"[]");
		}
		
	}
	/** 
	  * @Title: getRAMappings 
	  * @Description:获得当前角色拥有的资源数组
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getResActMappings(){
		try {
			Role role = this.roleService.findById(id);
			if(role==null){
				outJsonString(response,"[]");
			}
			List<Map<String, Object>> list = this.resourcesService.getRoleResActMappings(role);
//			//将最后结果排序
//			Collections.sort(list, new Comparator<Map<String, Object>>(){
//				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//					return ((Integer)((Map<String, Object>)o1.get("res")).get("id"))-((Integer)((Map<String, Object>)o1.get("res")).get("id"));
//				}
//			});

			JSONArray json = JSONArray.fromObject(list);
//			outJsonString(response,json.toString());
			outJsonPlainString(response,"{\"total\":" + list.size() + ",\"rows\":"+json.toString() + "}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"{\"total\":0,\"rows\":[]}");
		}
	}
	/** 
	  * @Title: saveResActions 
	  * @Description:保存用户资源对应的操作 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void saveResActions(){
		try{
			this.roleService.saveResActions(id, roleActions);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/** 
	  * @Title: delete 
	  * @Description: 删除指定id的角色
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void delete(){
		role.setId(id);
		if(this.roleService.delete(role)){
			outJsonPlainString(response, "{\"success\":true}");
		}else{
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	private Role role;
	private int id;
	private String rids;
	private String roleActions;
	
	public String getRoleActions() {
		return roleActions;
	}

	public void setRoleActions(String roleActions) {
		this.roleActions = roleActions;
	}

	public String getRids() {
		return rids;
	}

	public void setRids(String rids) {
		this.rids = rids;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
