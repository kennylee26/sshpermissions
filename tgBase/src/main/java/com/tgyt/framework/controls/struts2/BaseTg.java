/**   
  * @Title: BaseTg.java 
  * @Package com.tgyt.framework.controls.struts2 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-2 下午12:04:23 
  * @version V1.0   
  */

package com.tgyt.framework.controls.struts2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tgyt.common.tools.util.DictionaryMemoryUtils;


/** 
 * @ClassName: BaseTg 
 * @Description: 基础control包
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-2 下午12:04:23 
 *  
 */
public class BaseTg extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	protected Integer page=0;
	protected Integer rows=10;
	protected String nickName = null;
	protected String sort = null;
	protected String order = null;
	/** 
	  * @Fields request : 得到request对象
	  */ 
	protected HttpServletRequest request = ServletActionContext.getRequest();
	/** 
	  * @Fields response : 得到response对象
	  */ 
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	
	/** 
	  * @Title: setSort 
	  * @Description: 获得排序字段值
	  * @param @param sort
	  * @return void
	  * @throws 
	  */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
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
	/**
	 * @return the request
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	/**
	 * @return the response
	 */
	protected HttpServletResponse getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	protected void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	/** 
	  * @Title: outJsonUTFString 
	  * @Description: 以json格式返回
	  * @param @param response 输出
	  * @param @param json json格式字符串
	  * @return void
	  * @throws 
	  */
	protected void outJsonUTFString(HttpServletResponse response, String json) {
		response.setContentType("text/javascript;charset=UTF-8");
		try {
			outString(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 
	  * @Title: outHtmlUTFString 
	  * @Description: 放回HTML格式的数据 
	  * @param @param response
	  * @param @param json
	  * @return void
	  * @throws 
	  */
	protected void outHtmlUTFString(HttpServletResponse response, String json){
		response.setContentType("text/html;charset=UTF-8");
		try {
			outString(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/** 
	  * @Title: outJsonPlainString 
	  * @Description: ContentType为text/plain 
	  * @param @param response 输出
	  * @param @param json json格式字符串
	  * @return void
	  * @throws 
	  */
	protected void outJsonPlainString(HttpServletResponse response, String json){
		response.setContentType("text/plain;charset=UTF-8");
		try {
			outString(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 
	  * @Title: outJsonString 
	  * @Description: ContentType为text/json格式 
	  * @param @param response
	  * @param @param json
	  * @return void
	  * @throws 
	  */
	protected void outJsonString(HttpServletResponse response, String json){
		response.setContentType("text/json;charset=UTF-8");
		try {
			outString(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 
	  * @Title: outXMLString 
	  * @Description: 以xml形式返回
	  * @param @param response 输出
	  * @param @param xmlStr xml串
	  * @return void
	  * @throws 
	  */
	protected void outXMLString(HttpServletResponse response, String xmlStr) {
		response.setContentType("application/xml;charset=UTF-8");
		try {
			outString(response, xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	  * @Title: outString 
	  * @Description: 输出字符串
	  * @param response 返回
	  * @param str 传回的字符串
	  * @return void
	  * @throws 
	  */
	private void outString(HttpServletResponse response, String str) {
		try {
			PrintWriter out = response.getWriter();
			// out.write(str);
			out.println(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	  * @Title: outDicJsonByNickname 
	  * @Description:根据传入的nickName获得对应记录的JSON格式输出
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void outDicJsonByNickname(){
		DictionaryMemoryUtils dictionaryUtils = DictionaryMemoryUtils.getInstance();
		Map<String,String> maps = dictionaryUtils.getDictionarysMap().get(nickName);
		if(null!=maps){
			List<Dic> dics = new ArrayList<Dic>();
			Set<String> keys = maps.keySet();
			for(Iterator<String> iter=keys.iterator();iter.hasNext();){
				String value = iter.next();
				Dic dic = new Dic();
				dic.setValue(value);
				dic.setName(maps.get(value));
				dics.add(dic);
			}
			JSONArray json = JSONArray.fromObject(dics);
			this.outJsonPlainString(response, json.toString());
		}else{
			this.outJsonPlainString(response, "[]");
		}
	}

	/** 
	  * @ClassName: Dic 
	  * @Description: 内部类，用来存储字典记录 
	  * @author sunct sunchaotong18@163.com 
	  * @date 2011-10-8 下午1:48:29 
	  *  
	  */
	public class Dic{
		private String name;
		private String value;
		public Dic(){
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}
		
	}
}
