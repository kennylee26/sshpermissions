package com.tgyt.permissions.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.ISystemService;
import com.tgyt.permissions.model.Systems;

/** 
  * @ClassName: SystemTg 
  * @Description: 系统信息控制层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:26:41 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("sys.MessageControl")
public class SystemTg extends BaseTg {
	
	/** 
	  * @Fields systemService : ISystemService接口spring自动注入
	  */ 
	@Autowired
	private ISystemService systemService;
	
	/** 
	  * @Fields system : struts2.0自动接收
	  */ 
	private Systems system;
	
	/** 
	  * @Fields id : 要删除的id号,同时也是查询系统信息的id号
	  */ 
	private String id;
	

	/** 
	  * @Title: index 
	  * @Description: 系统信息首页
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return SUCCESS;
	}

	/** 
	  * @Title: getItems 
	  * @Description: 系统全部列表
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		
		Pagination p = systemService.getPageList(this.system,page, rows,sort,order);
		List<Systems> list = p.getList();
		List lists = doList(list);
		JSONArray jsonArray = JSONArray.fromObject(lists);
	    String baseStr = "{\"total\":" + p.getTotalCount() + ",\"rows\":";
		baseStr = baseStr + jsonArray.toString() + "}";
		returnJsion(baseStr,response);
	}

	/** 
	  * @Title: save 
	  * @Description: 添加一条系统记录
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		system.setBuilddate(format.format(new Date()));
		if(systemService.save(system)){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}

	/** 
	  * @Title: find 
	  * @Description: 获取一套详细系统信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void  find(){
		Systems sys = systemService.findById(Integer.valueOf(id));
		List<Systems> list = new ArrayList<Systems>();
		if(null != sys){
			list.add(sys);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		returnJsion("{\"rows\":" + jsonArray.toString() +"}",response);
	}
	
	/** 
	  * @Title: update 
	  * @Description:  更新一套系统信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		if(systemService.alter(system)){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}
	
	/** 
	  * @Title: del 
	  * @Description: 删除一套系统
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void del(){
		
		if(systemService.deleteById(Integer.valueOf(id))){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
		
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
	  * @Title: doList 
	  * @Description:  解决struts2.0domal接受参数的方法
	  * @param @param list
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doList(List<Systems> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Systems sys = list.get(i);
				map.put("builddate", sys.getBuilddate());
				map.put("contextPath", sys.getContextPath());
				map.put("ename", sys.getEname());
				map.put("icon", sys.getIcon());
				map.put("id", sys.getId() + "");
				map.put("logo", sys.getLogo());
				map.put("memo", sys.getMemo());
				map.put("name", sys.getName());
				map.put("order", sys.getOrder() + "");
				map.put("status", sys.getStatus());
				map.put("tablePrefix", sys.getTablePrefix());
				map.put("version", sys.getVersion());
				lists.add(map);
			}
		}
		return lists;
	}

	public Systems getSystem() {
		return system;
	}

	public void setSystem(Systems system) {
		this.system = system;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
