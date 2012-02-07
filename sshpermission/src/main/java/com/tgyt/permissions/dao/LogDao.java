package com.tgyt.permissions.dao;

import org.springframework.stereotype.Repository;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.permissions.model.Logs;

/** 
  * @ClassName: LogDao 
  * @Description: 日志Dao层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:55:31 
  *  
  */
@Repository
public class LogDao extends BaseDAO<Logs>{
	/** 
	  * @Title: getPageList 
	  * @Description: 获取具体某页的信息
	  * @param @param log 一条具体Log对象信息
	  * @param @param page 当前页
	  * @param @param rows 一页显示的多少条记录
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Logs log,int page,int rows,String sort,String order){
		StringBuffer sb = new StringBuffer("from Logs where 1=1 ");
		if(null != log){
			if(null != log.getOpertype() && !"".equals(log.getOpertype())){
				sb.append("and opertype like '%" + log.getOpertype() + "%' ");
			}
			if(null != log.getCreateuser() && !"".equals(log.getCreateuser())){
				sb.append("and createuser like '%" + log.getCreateuser() + "%'");
			}
			if(null != log.getCreatedate() && !"".equals(log.getCreatedate())){
				sb.append("and createdate like '%" + log.getCreatedate() + "%'");
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
		return this.getHandler().getPageList(Finder.create(sb.toString()), page, rows);
	}
}
