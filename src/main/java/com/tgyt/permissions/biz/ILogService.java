package com.tgyt.permissions.biz;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Logs;

/** 
  * @ClassName: ILogService 
  * @Description: 日志业务层接口
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:45:16 
  *  
  */
public interface ILogService extends ServiceInterface<Logs>{
	/** 
	  * @Title: getPageList 
	  * @Description: 获取具体某页的记录
	  * @param @param log 一条具体log对象的信息
	  * @param @param page 当前页
	  * @param @param rows 一页显示的多少记录
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Logs log,int page,int rows,String sort,String order);
}
