/**   
  * @Title: TArticleTg.java 
  * @Package com.tgyt.lucene.contrl 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-8-7 上午9:53:08 
  * @version V1.0   
  */

package com.tgyt.lucene.controls;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.catalina.util.URLEncoder;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.lucene.biz.ITArticleService;
import com.tgyt.lucene.util.Searcher;

/** 
 * @ClassName: TArticleTg 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-8-7 上午9:53:08 
 *  
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller(value="lucene.tarticleTgControl")
public class TArticleTg extends BaseTg{
	@Autowired
	private ITArticleService articleService;
	
	private String keyword;
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
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
	  * @Title: createIndex 
	  * @Description: 为数据库中所有元素建立索引
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void createIndex(){
		try{
			String path=request.getSession().getServletContext().getRealPath("/"); 
			articleService.createIndex(path);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
			
		}
	}
	/** 
	  * @Title: searchIndex 
	  * @Description:查索引
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void searchIndex(){
		String path=request.getSession().getServletContext().getRealPath("/");
		List<Document> docs=articleService.searchIndex(keyword,path);
		List list = new ArrayList();
		for(Document doc : docs) {
			Map<String, String> strMap = new HashMap<String, String>();
			strMap.put("title",doc.get("title"));
			strMap.put("id",doc.get("id"));
			strMap.put("content",doc.get("content"));
            list.add(strMap);
        }
		String json = JSONArray.fromObject(list).toString();
		outJsonPlainString(response,json);
	}
	/** 
	  * @Title: paginationQuery 
	  * @Description: 显示结果
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void paginationQuery(){
		String path=request.getSession().getServletContext().getRealPath("/");
		List<Document> docs=articleService.paginationQuery(keyword, rows, page, path);
		List list = new ArrayList();
		for(Document doc : docs) {
			Map<String, String> strMap = new HashMap<String, String>();
			strMap.put("title",doc.get("title"));
			strMap.put("id",doc.get("id"));
			strMap.put("content",doc.get("content"));
            list.add(strMap);
        }
		String json = JSONArray.fromObject(list).toString();
		int totalCount = articleService.getCount(keyword, path);
		//System.out.println(json);
		String baseStr = "{\"total\":" + totalCount + ",\"rows\":";
		baseStr = baseStr + json + "}";
		outJsonPlainString(response,baseStr);
	}
	/** 
	  * @Title: getItems 
	  * @Description: 显示结果
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		String path=request.getSession().getServletContext().getRealPath("/");
		List list;
		String keyword = "";
		try {
			keyword = URLDecoder.decode(request.getParameter("keyword"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			list = articleService.searchHigh(keyword, rows, page, path);
			String json = JSONArray.fromObject(list).toString();
			int totalCount = articleService.getCount(keyword, path);
			System.out.println(json);
			String baseStr = "{\"total\":" + totalCount + ",\"rows\":";
			baseStr = baseStr + json + "}";
			outJsonPlainString(response,baseStr);
	}
}
