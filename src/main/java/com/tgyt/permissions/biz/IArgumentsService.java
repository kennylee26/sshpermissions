package com.tgyt.permissions.biz;

import java.util.List;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Arguments;


/** 
  * @ClassName: IArgumentsService 
  * @Description: 全局参数管理接口 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:44:29 
  *  
  */
public interface IArgumentsService extends ServiceInterface<Arguments> {

	/** 
	  * @Title: getArgumentsList 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param argument
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @param sort 排序字段
	  * @param @param order 排序顺序
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getArgumentsList(Arguments argument,int pageNo,int pageSize,String sort,String order);
	
	/** 
	  * @Title: getMaxOrder 
	  * @Description: 获得数据库中的最大排序值 
	  * @param @return
	  * @return int
	  * @throws 
	  */
	public int getMaxOrder();
	
	/** 
	  * @Title: getAllArguments 
	  * @Description: 获取所有全局参数
	  * @param @return
	  * @return List<Arguments>
	  * @throws 
	  */
	public List<Arguments> getAllArguments();
}
