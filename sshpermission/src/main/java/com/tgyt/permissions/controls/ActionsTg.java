package com.tgyt.permissions.controls;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.json.FormatJSON;
import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IActionsService;
import com.tgyt.permissions.model.Actions;


/** 
  * @ClassName: ActionsTg 
  * @Description: 操作管理控制类 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午5:04:21 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller(value="permissions.actionsTgControl")
public class ActionsTg extends BaseTg {

	/** 
	  * @Fields actionsService :操作信息的服务接口 
	  */ 
	@Autowired
	private IActionsService actionsService;
	
	/** 
	  * @Title: index 
	  * @Description: 索引进入操作信息页面  
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return SUCCESS;
	}
	
	/** 
	  * @Title: getItems 
	  * @Description: 根据action的条件获得指定列表 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		Pagination pagination = this.actionsService.getActionsList(this.getAction(), page, rows,sort,order);
		List list = pagination.getList();
		JSONArray json = JSONArray.fromObject(list);
		try {
			outJsonString(response,"{\"total\":" + pagination.getTotalCount() + ",\"rows\":"+json.toString() + "}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 
	  * @Title: save 
	  * @Description:保存一条操作信息记录 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		try{
			if(action.getOrderid()==null){
				action.setOrderid(this.actionsService.getMaxOrder()+1);
			}
			this.actionsService.save(action);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
			
		}
	}
	/** 
	  * @Title: update 
	  * @Description: 修改操作信息记录
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		try{
			this.actionsService.alter(action);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/** 
	  * @Title: delete 
	  * @Description: 删除指定ID的操作信息记录
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void delete(){
		try {
			this.actionsService.deleteById(id);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	private Integer id;
	private Actions action;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Actions getAction() {
		return action;
	}
	public void setAction(Actions action) {
		this.action = action;
	}
	
}
