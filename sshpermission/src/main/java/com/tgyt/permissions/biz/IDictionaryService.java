package com.tgyt.permissions.biz;

import java.util.List;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Dictionarys;

/** 
  * @ClassName: IDictionaryService 
  * @Description: 系统字典业务层接口
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:40:58 
  *  
  */
public interface IDictionaryService extends ServiceInterface<Dictionarys> {
	/** 
	  * @Title: findMax 
	  * @Description: 获取昵称后的值
	  * @param @param nickName 昵称  生成的代码
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String findMax(String nickName);
	/** 
	  * @Title: getPageList 
	  * @Description: 获取具体某页的记录
	  * @param @param dic 一条具体Dictionarys对象信息
	  * @param @param page 当前页
	  * @param @param rows 一页显示的内容
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Dictionarys dic,int page,int rows,String sort,String order);
	
	/** 
	  * @Title: getAllDictionary 
	  * @Description: 获取所有的系统字典的记录
	  * @param @return
	  * @return List<Dictionarys>
	  * @throws 
	  */
	public List<Dictionarys> getAllDictionary();
}
