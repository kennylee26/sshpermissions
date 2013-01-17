/**   
  * @Title: ResourcesTg.java 
  * @Package com.tgyt.permissions.controls 
  * @Description: 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-20 下午1:52:27 
  * @version V1.0   
  */

package com.tgyt.permissions.controls;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IActionsService;
import com.tgyt.permissions.biz.IResourcesService;
import com.tgyt.permissions.biz.ISystemService;
import com.tgyt.permissions.model.Actions;
import com.tgyt.permissions.model.CloneResources;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Systems;

/** 
  * @ClassName: ResourcesTg 
  * @Description: 资源管理ACTION类
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-21 下午1:03:49 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller(value="permissions.resourcesTgControl")
public class ResourcesTg extends BaseTg {

	/** 
	  * @Fields resourcesService : 资源服务接口
	  */ 
	@Autowired
	private IResourcesService resourcesService;
	/** 
	  * @Fields systemService : 系统服务接口
	  */ 
	@Autowired
	private ISystemService systemService;
	
	@Autowired
	private IActionsService actionsService;
	/** 
	  * @Title: index 
	  * @Description:索引进入资源信息页面 
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		try {
			request.setAttribute("allTrees",this.resourcesService.getAllHTMLTrees(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"");
		}
		return SUCCESS;
	}
	/** 
	  * @Title: getAllTrees 
	  * @Description:返回所有系统下的资源树JSON 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getAllTrees(){
		try {
			JSONArray json = JSONArray.fromObject(this.resourcesService.getAllListTrees());
			outJsonString(response,json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"[]");
		}
		
	}
	public void getAllHTMLTrees(){
		try {
			outHtmlUTFString(response,this.resourcesService.getAllHTMLTrees(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"");
		}
		
	}
	/** 
	  * @Title: getItems 
	  * @Description: 根据查询条件分页查询资源列表 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		Pagination pagination = this.resourcesService.getResourcesList(this.getResource(),treeId, page, rows,sort,order);
		try {
			List list = pagination.getList();
			String str = "";
			if(list.size()!=0){
				list =this.resourcesService.convert(list);
				str = JSONArray.fromObject(list).toString();
			}else{
				str="[]";
			}
			outJsonPlainString(response,"{\"total\":" + pagination.getTotalCount() + ",\"rows\":"+str + "}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outJsonPlainString(response,"{\"total\":0, \"rows\":[]}");
		}
	}
	/** 
	  * @Title: save 
	  * @Description: 保存一条资源记录
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		try {
			this.resourcesService.save(this.resourcesService.convert(resource));
			outJsonPlainString(response, "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		} 
	}
	
	
	/** 
	  * @Title: saveActions 
	  * @Description: 保存操作权限
	  * @param     设定文件 
	  * @return void    返回类型 
	  * @throws 
	  */
	public void saveActions(){
		String[] strs=actions.split(",");
		try {
			Resources res=this.resourcesService.findById(id);
			Set<Actions> resSet=res.getResActions();
			resSet.clear();
			for(int i=0;i<strs.length;i++){
				Actions action=actionsService.findById(Integer.parseInt(strs[i]));
				resSet.add(action);
			}
			res.setResActions(resSet);
			this.resourcesService.alter(res);
			outJsonPlainString(response, "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		} 
	}
	
	public void getSelectedAction(){
		try {
			Resources res=this.resourcesService.findById(id);
			Set<Actions> resSet=res.getResActions();
			StringBuilder str=new StringBuilder();
			int i=0;
			for (Actions object : resSet) {
				if(i<resSet.size()-1){
					str.append(object.getId().toString()+",");
				}
				else{
					str.append(object.getId().toString());
				}
				i++;
			}
			System.out.println(str.toString());
			outJsonPlainString(response, "{\"actionIds\":\""+str.toString()+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/** 
	  * @Title: update 
	  * @Description:修改资源信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		try {
			this.resourcesService.alter(this.resourcesService.convert(resource));
			request.setAttribute("allTrees",this.resourcesService.getAllHTMLTrees(request));
			outJsonPlainString(response, "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		} 
	}
	
	/** 
	  * @Title: delete 
	  * @Description:删除资源信息 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void delete(){
		try {
			this.resourcesService.deleteSubResources(id);
			request.setAttribute("allTrees",this.resourcesService.getAllHTMLTrees(request));
			outJsonPlainString(response, "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		} 
	}
	/** 
	  * @Title: getTree 
	  * @Description: 获取指定系统下的JSON资源树 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getTree(){
		try {
		    Systems tem = 	this.systemService.findById(systemId);
			List tree = this.resourcesService.getTree(tem,id);
			outJsonPlainString(response, JSONArray.fromObject(tree).toString());
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response, "[]");
		}
	}
	
	/** 
	  * @Title: getSystems 
	  * @Description: 返回系统列表
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getSystems(){
		List<Systems> systems = systemService.findList("from Systems");
		JSONArray json = JSONArray.fromObject(systems);
		outJsonString(response, json.toString());
	}
	
	private CloneResources resource;
	private Integer id;
	private String treeId;
	private Integer systemId;
	
	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public CloneResources getResource() {
		return resource;
	}
	public void setResource(CloneResources resource) {
		this.resource = resource;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String actions;
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	
	
}
