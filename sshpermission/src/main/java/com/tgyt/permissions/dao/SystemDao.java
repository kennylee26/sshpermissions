package com.tgyt.permissions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.permissions.model.Systems;

/** 
  * @ClassName: SystemDao 
  * @Description: 系统信息Dao层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:56:53 
  *  
  */
@Repository
public class SystemDao extends BaseDAO<Systems> {
	
	/** 
	  * @Title: getPageList 
	  * @Description: 获取具体某页的信息
	  * @param @param sys 一条具体Systems对象的信息
	  * @param @param page 当前页
	  * @param @param rows 一页显示多少记录
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Systems sys,int page, int rows,String sort,String order) {
		StringBuffer sb = new StringBuffer("from Systems where 1=1");
		if(null != sys){
			if(null != sys.getName() && !"".equals(sys.getName())){
				sb.append(" and name like'%" + sys.getName() + "%'");
			}
			if(null != sys.getEname() && !"".equals(sys.getEname())){
				sb.append(" and ename like'%" + sys.getEname() + "%'");
			}
			if(null != sys.getBuilddate() && !"".equals(sys.getBuilddate())){
				sb.append(" and builddate like'%" + sys.getBuilddate() + "%'");
			}
		}
		if(sort!=null && !"".equals(sort)){
			sb.append(" order by "+sort);
			if(order!=null && !"".equals(order)){
				sb.append(" "+order);
			}else{
				sb.append(" desc");
			}
		}
		return super.getHandler().getPageList(Finder.create(sb.toString()), page, rows);
	}
	
	/** 
	  * @Title: getAll 
	  * @Description: 获取所有的系统信息
	  * @param @return
	  * @return List<Systems>
	  * @throws 
	  */
	public List<Systems> getAll(){
		StringBuffer sb = new StringBuffer("from Systems");
		
		return this.getHandler().findListOfObj(sb.toString());
	}
	
}
