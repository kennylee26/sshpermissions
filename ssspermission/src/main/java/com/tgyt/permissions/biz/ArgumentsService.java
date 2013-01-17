package com.tgyt.permissions.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.ArgumentsDAO;
import com.tgyt.permissions.model.Arguments;


/** 
  * @ClassName: ArgumentsService 
  * @Description: 全局参数管理服务类 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:44:20 
  *  
  */
@Service
public class ArgumentsService extends BaseService<Arguments> implements
		IArgumentsService {

	@Resource(name="argumentsDAO")
	private ArgumentsDAO argumentsDAO;
	
	protected DAOInterface<Arguments> getDAO() {
		return argumentsDAO;
	}

	public Pagination getArgumentsList(Arguments argument, int pageNo, int pageSize ,String sort,String order) {
		StringBuffer hql = new StringBuffer("from Arguments where 1=1 ");
		if(null!=argument){
			String memo = argument.getMemo();
			String name = argument.getName();
			String value = argument.getValue();
			Integer orderid = argument.getOrderid();
			if(memo!=null && !"".equals(memo)){
				hql.append(" and memo like '%"+memo+"%'");
			}
			if(name!=null && !"".equals(name)){
				hql.append(" and name like '%"+name+"%'");
			}
			if(value!=null && !"".equals(value)){
				hql.append(" and value like '%"+value+"%'");
			}
			if(orderid!=null && !"".equals(orderid)){
				hql.append(" and orderid="+orderid);
			}
		}
		if(sort!=null && !"".equals(sort)){
			hql.append(" order by "+sort);
			if(order!=null && !"".equals(order)){
				hql.append(" "+order);
			}else{
				hql.append(" desc");
			}
		}
		return this.argumentsDAO.getHandler().getPageList(new Finder(hql.toString()), pageNo, pageSize);
	}

	public int getMaxOrder() {
		return this.argumentsDAO.getMaxOrder();
	}

	public List<Arguments> getAllArguments() {
		return argumentsDAO.getAllArguments();
	}

	

}
