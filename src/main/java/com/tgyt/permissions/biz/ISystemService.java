package com.tgyt.permissions.biz;
import java.util.List;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Systems;

/** 
  * @ClassName: ISystemService 
  * @Description: 系统信息业务层接口
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:46:47 
  *  
  */
public interface ISystemService extends ServiceInterface<Systems> {
	/** 
	  * @Fields TREE_NAME : 在构造树的时候用于标识当前节点的类型 
	  */
	String TREE_NAME = "SYSTEM";
	/** 
	  * @Title: getPageList 
	  * @Description:  获取具体某页的记录
	  * @param @param sys 一条具体Systems对象的信息
	  * @param @param page 当前页
	  * @param @param rows 一页显示的多少记录
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Systems sys,int page,int rows,String sort,String order);
	/** 
	  * @Title: getAll 
	  * @Description: 获取所有的系统信息
	  * @param @return
	  * @return List<Systems>
	  * @throws 
	  */
	public List<Systems> getAll();
}
