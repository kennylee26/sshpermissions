package com.tgyt.flowList.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.flowList.biz.ITypeFlowService;
import com.tgyt.flowList.model.TypeFlow;
import com.tgyt.framework.controls.struts2.BaseTg;

/** 
  * @ClassName: TypeFlowTg 
  * @Description: 流程类型与流程中间表维护
  * @author haoly  454688562@qq.com
  * @date 2012-4-17 下午3:57:19 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("listControl")
public class TypeFlowTg extends BaseTg {

	@Autowired
	private ITypeFlowService typeFlowService;
	
	private TypeFlow typeFlow;
	private int id;
	private String treeId;
	
	
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TypeFlow getTypeFlow() {
		return typeFlow;
	}
	public void setTypeFlow(TypeFlow typeFlow) {
		this.typeFlow = typeFlow;
	}
	
//	public void getItems() {
//
//
//		try {
//			Pagination pagination = this.typeFlowService.getFlowList(
//					typeFlow, page, rows, sort, order,null);
//			List list = pagination.getList();
//			String str = "";
//			if (list.size() != 0) {
//				str = JSONArray.fromObject(list).toString();
//			} else {
//				str = "[]";
//			}
//			outJsonPlainString(response,
//					"{\"total\":" + pagination.getTotalCount() + ",\"rows\":"
//							+ str + "}");
//		} catch (Exception e) {
//			e.printStackTrace();
//			outJsonPlainString(response, "{\"total\":0, \"rows\":[]}");
//		}
//	}
	public void save(){
		if (typeFlowService.save(typeFlow)) {
			responseJSON("{\"success\":true}");
		} else {
			responseJSON("{\"error\":true}");
		}
	}
	
	public void update(){
		if (typeFlowService.alter(typeFlow)) {
			responseJSON("{\"success\":true}");
		} else {
			responseJSON("{\"error\":true}");
		}
	}
	
	public void delete(){
		if (typeFlowService.delete(typeFlow)) {
			responseJSON("{\"success\":true}");
		} else {
			responseJSON("{\"error\":true}");
		}
	}
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
	/**
	 * 
	  * @Title: revertStatus 
	  * @Description: 用来修改当前流程的状态
	  * @param 
	  * @return void
	  * @throws
	 */
	public void revertStatus(){
		try{
			TypeFlow flow = this.typeFlowService.findById(id);
			if(ITypeFlowService.CLOSE_STATE.equals(flow.getFlag())){
				flow.setFlag(ITypeFlowService.OPEN_STATE);
			}else{
				flow.setFlag(ITypeFlowService.CLOSE_STATE);
			}
			this.typeFlowService.alter(flow);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	public void getFlowItems(){
		if(treeId!=null&&!"".equals(treeId)){
			id=Integer.parseInt(treeId);
		}
		try {
		List list = this.typeFlowService.getFlowList(typeFlow, id);
		String str = "";
		if (list.size() != 0) {
			str = JSONArray.fromObject(list).toString();
		} else {
			str = "[]";
		}
		outJsonPlainString(response,
				"{\"total\":" + list.size() + ",\"rows\":"
						+ str + "}");
	} catch (Exception e) {
		e.printStackTrace();
		outJsonPlainString(response, "{\"total\":0, \"rows\":[]}");
	}
	}
}
