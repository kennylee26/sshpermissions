package com.tgyt.permissions.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.json.FormatJSON;
import com.tgyt.common.tools.page.Pagination;
import com.tgyt.common.tools.util.ArgumentMemoryUtils;
import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IArgumentsService;
import com.tgyt.permissions.model.Arguments;


/** 
  * @ClassName: ArgumentsTg 
  * @Description:全局参数管理业务类
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:44:11 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("permissions.argumentsControl")
public class ArgumentsTg extends BaseTg {

	/** 
	  * @Fields argumentsService :参数信息服务接口 
	  */ 
	@Autowired
	private IArgumentsService argumentsService;
	
	
	/** 
	  * @Title: index 
	  * @Description:用于索引全局参数信息页面 
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index() {
		return SUCCESS;
	}

	/** 
	  * @Title: save 
	  * @Description: 保存一条参数信息记录 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		try {
			if(argument.getOrderid()==null){
				argument.setOrderid(this.argumentsService.getMaxOrder()+1);
			}
			this.argumentsService.save(this.getArgument());
			reloadMemory();
			outJsonPlainString(response,"{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response,"{\"error\":true}");
		}
	}
	/** 
	  * @Title: update 
	  * @Description:修改一条参数信息记录 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void update(){
		try {
			Arguments arg = this.getArgument();
			this.argumentsService.alter(arg);
			reloadMemory();
			outJsonPlainString(response,"{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response,"{\"error\":true}");
		}
	}
	/** 
	  * @Title: delete 
	  * @Description:删除一条参数信息记录
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void delete(){
		try{
			this.argumentsService.deleteById(id);
			 reloadMemory();
			outJsonPlainString(response,"{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			outJsonPlainString(response,"{\"error\":true}");
		}
	}
	/** 
	  * @Title: getItems 
	  * @Description:根据argument查询符合条件的记录 
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		Pagination pagination = this.argumentsService.getArgumentsList(this.getArgument(),page, rows,sort,order);
		List list = pagination.getList();
		JSONArray json = JSONArray.fromObject(list);
		try {
			outJsonString(response,"{\"total\":" + pagination.getTotalCount() + ",\"rows\":"+json.toString() + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	  * @Title: reloadMemory 
	  * @Description:新增修改或删除全局参数后重新读入内存 
	  * @param 
	  * @return void
	  * @throws 
	  */
	private void reloadMemory(){
		ArgumentMemoryUtils argumentUtils = ArgumentMemoryUtils.getInstance();
		List<Arguments> list = new ArrayList<Arguments>();
		list = this.argumentsService.findList("from Arguments");
		Map<String,String> argumentsMap = new HashMap<String ,String>();
		for(Iterator<Arguments> iter=list.iterator();iter.hasNext();){
			Arguments arg = iter.next();
			argumentsMap.put(arg.getName(), arg.getValue());
		}
		argumentUtils.setArgumentsMap(argumentsMap);
	}

	private Arguments argument;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Arguments getArgument() {
		return argument;
	}

	public void setArgument(Arguments argument) {
		this.argument = argument;
	}

}
