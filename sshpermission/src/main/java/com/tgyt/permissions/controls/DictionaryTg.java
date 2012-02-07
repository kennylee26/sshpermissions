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
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IDictionaryService;
import com.tgyt.permissions.model.Dictionarys;
import com.tgyt.permissions.model.Logs;


/** 
  * @ClassName: DictionaryTg 
  * @Description: 系统字典控制层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:03:03 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("dictionaryControl")
public class DictionaryTg extends BaseTg {
	/** 
	  * @Fields dictionaryService : IDictionaryService业务层接口注入
	  */ 
	@Autowired
	private IDictionaryService dictionaryService;
	
	/** 
	  * @Fields dic : struts2.0接受前台信息（domain接受）
	  */ 
	private Dictionarys dic;

	/** 
	  * @Fields nickName : 昵称
	  */ 
	private String nickName;
	
	/** 
	  * @Fields id : 删除，更新所需要的id，struts2.0接受
	  */ 
	private String id;

	/** 
	  * @Title: index 
	  * @Description: 系统字典首页
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return this.SUCCESS;
	}
	
	/** 
	  * @Title: getItems 
	  * @Description: 系统字典表所有常量信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		Pagination pagination = dictionaryService.getPageList(dic, page, rows,sort,order);
		List<Dictionarys> list = pagination.getList();
		List lists = doList(list);	
		JSONArray jsonArray = JSONArray.fromObject(lists);
		String baseStr = "{\"total\":" + pagination.getTotalCount() + ",\"rows\":";
		baseStr = baseStr + jsonArray.toString() + "}";
		returnJsion(baseStr,response);
	}

	/** 
	  * @Title: save 
	  * @Description: 保存一条系统字典表中的常量信息
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		String num = dictionaryService.findMax(dic.getNickName());
		dic.setValue(num);
		
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		dic.setCreateTime(format.format(new Date()));
		if(this.dictionaryService.save(this.dic)){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}

	/** 
	  * @Title: find 
	  * @Description: 查找一常量
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void find(){
		Dictionarys dic = this.dictionaryService.findById(Integer.valueOf(id));
		List<Dictionarys> list = new ArrayList<Dictionarys>();
		if(null != dic){
			list.add(dic);
		}
		JSONArray json = JSONArray.fromObject(list);
		returnJsion("{\"rows\":" +  json.toString() +"}", response);
	}

	/** 
	  * @Title: update 
	  * @Description:  更新常量
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		if(this.dictionaryService.alter(this.dic)){
			returnJsion("{\"success\":\"true\"}",response);
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}

	/** 
	  * @Title: del 
	  * @Description: 删除常量
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void del(){
		if(this.dictionaryService.deleteById(Integer.valueOf(id))){
			returnJsion("{\"success\":\"true\"}",response);	
		}else{
			returnJsion("{\"error\":\"true\"}",response);
		}
	}
	
	/** 
	  * @Title: returnJsion 
	  * @Description:  解决json问题
	  * @param @param baseStr 拼好的jsion串
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
	  * @param @param list 装Dictionarys的具体信息。
	  * @param @return
	  * @return List
	  * @throws 
	  */
	private List doList(List<Dictionarys> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Dictionarys dic = list.get(i);
				map.put("nickname", dic.getNickName());
				map.put("name", dic.getName());
				map.put("value", dic.getValue());
				map.put("createtime", dic.getCreateTime());
				map.put("type", dic.getType());
				map.put("updatable", dic.getUpdaTable());
				map.put("creator", dic.getCreator());
				map.put("status", dic.getStatus());
				map.put("orderid", dic.getOrderid());
				map.put("memo", dic.getMemo());
				map.put("id", dic.getId() + "");
				lists.add(map);
			}
		}
		return lists;
	}
	public Dictionarys getDic() {
		return dic;
	}
	public void setDic(Dictionarys dic) {
		this.dic = dic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
