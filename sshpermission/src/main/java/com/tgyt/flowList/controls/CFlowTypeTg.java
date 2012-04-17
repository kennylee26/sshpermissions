package com.tgyt.flowList.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.flowList.biz.ICFlowTypeService;
import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.framework.controls.struts2.BaseTg;

/** 
  * @ClassName: CFlowTypeTg 
  * @Description: 工作流类型
  * @author haoly  454688562@qq.com
  * @date 2012-4-12 下午2:11:39 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("flowTypeControl")
public class CFlowTypeTg extends BaseTg {
	@Autowired
	private ICFlowTypeService flowTypeService;
	
	private CFlowtype flowType;
	private String id;
	private String parentid;
	private String typename;
	
	
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CFlowtype getFlowType() {
		return flowType;
	}

	public void setFlowType(CFlowtype flowType) {
		this.flowType = flowType;
	}



	/** 
	  * @Title: getTree 
	  * @Description: 获得流程类型树
	  * @param     设定文件 
	  * @return void    返回类型 
	  * @throws 
	  */
	public void getTree() {
		List tree = this.flowTypeService.getTree();
		JSONArray jsonArray = JSONArray.fromObject(tree);
		System.out.println(jsonArray.toString());
		outJsonPlainString(response,jsonArray.toString());
	}

	/**
	 * @Title: save
	 * @Description: 对树的内容进行保存
	 * @param
	 * @return void
	 * @throws
	 */
	public void saveType() {
		
		CFlowtype flowtype =new CFlowtype();
		flowtype.setParentid(Integer.parseInt(parentid));
		flowtype.setTypename(typename);
		flowTypeService.save(flowtype);
		responseJSON("{\"id\":" + flowtype.getId()
				+ ",\"text\":\"" + flowtype.getTypename()
				+ "\",\"action\":\"append\",\"success\":true}");

	}
	/**
	 * @Title: update
	 * @Description:修改一条数据
	 * @param @return
	 * @return String
	 * @throws
	 */
	public void updateType() {
		CFlowtype temp = flowTypeService.find("from CFlowtype where id ="+this.id);
		temp.setTypename(typename);
		if (flowTypeService.alter(temp)) {
			responseJSON("{\"id\":" + temp.getId() + ",\"text\":\""
					+ temp.getTypename()
					+ "\",\"action\":\"update\",\"success\":true}");
		} else {
			responseJSON("{\"id\":" + temp.getId() + ",\"text\":\""
					+ temp.getTypename()
					+ "\",\"action\":\"update\",\"error\":true}");
		}
	}

	/**
	 * @Title: delete
	 * @Description: 删除节点及其字节点
	 * @param @return
	 * @return String
	 * @throws
	 */
	public void delteType() {
		this.flowType=flowTypeService.find("from CFlowtype where id ="+this.id);
		System.out.println(flowType) ;
		if (flowTypeService.delete(flowType)) {
			responseJSON("{\"success\":true}");
		} else {
			responseJSON("{\"error\":true}");
		}
	}

	/**
	 * @Title: responseJSON
	 * @Description: 向前台发送数据
	 * @param @param content
	 * @return void
	 * @throws
	 */
	private void responseJSON(String content) {
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
