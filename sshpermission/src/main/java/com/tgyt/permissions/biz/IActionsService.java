package com.tgyt.permissions.biz;

import java.util.List;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Actions;


/** 
  * @ClassName: IActionsService 
  * @Description: 操作管理服务接口 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:49:58 
  *  
  */
public interface IActionsService extends ServiceInterface<Actions> {

	/** 
	  * @Title: getActionsList 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param actions
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @param sort 排序字段
	  * @param @param order 排序顺序
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getActionsList(Actions actions,int pageNo,int pageSize,String sort,String order);
	
	/** 
	  * @Title: getMaxOrder 
	  * @Description: 获得数据库中记录最大排序值
	  * @param @return
	  * @return int
	  * @throws 
	  */
	public int getMaxOrder();
	
	/** 
	  * @Title: getAll 
	  * @Description: 获得操作信息的所有记录
	  * @param @return
	  * @return List<Actions>
	  * @throws 
	  */
	public List<Actions> getAll();
}
