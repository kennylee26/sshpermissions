package com.tgyt.permissions.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.tgyt.permissions.biz.ILogService;
import com.tgyt.permissions.model.Logs;

/** 
  * @ClassName: LogTg 
  * @Description: 日志管理的控制层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:20:54 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("logControl")
public class LogTg extends BaseTg {
	/** 
	  * @Fields logService : ILogService接口spring自动注入
	  */ 
	@Autowired
	private ILogService logService;
	
	/** 
	  * @Fields logs : struts2.0自动接受
	  */ 
	private Logs logs;
	
	/** 
	  * @Fields page : 当前页
	  */ 
	private Integer page = 1;

	/** 
	  * @Fields rows : 一页显示多少条记录
	  */ 
	private Integer rows = 10;
	
	/** 
	  * @Fields id : 查看具体日志的id
	  */ 
	private String id;

	/** 
	  * @Title: index 
	  * @Description: 操作日志的首页
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return SUCCESS;
	}

	/** 
	  * @Title: getItems 
	  * @Description: 显示所有日志
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		Pagination p = logService.getPageList(this.logs, page, rows,sort,order);
		List<Logs> list = p.getList();
		List lists = doList(list);
		JSONArray jsonArray = JSONArray.fromObject(lists);
		String baseStr = "{\"total\":" + p.getTotalCount() + ",\"rows\":";
		baseStr = baseStr + jsonArray.toString() + "}";
		returnJsion(baseStr,response);
		
	}
	
	/** 
	  * @Title: look 
	  * @Description: 查找具体日志信息 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void look(){
		Logs logs = this.logService.findById(Integer.valueOf(id));
		List<Logs> list = new ArrayList<Logs>();
		if(null != logs){
			list.add(logs);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		returnJsion("{\"rows\":" + jsonArray.toString() + "}",response);
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
	  * @Title: doList 
	  * @Description: 解决struts2.0domal接受参数的方法
	  * @param @param list
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doList(List<Logs> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Logs log = list.get(i);
				map.put("opertype", log.getOpertype());
				map.put("content", log.getContent());
				map.put("cost", log.getCost() + "");
				map.put("createip", log.getCreateip());
				map.put("id", log.getId() + "");
				map.put("createuser", log.getCreateuser());
				map.put("createdate", log.getCreatedate());
				map.put("memo", log.getMemo());
				lists.add(map);
			}
		}
		return lists;
	}

	public Logs getLogs() {
		return logs;
	}
	public void setLogs(Logs logs) {
		this.logs = logs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
