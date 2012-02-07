/**   
  * @Title: ActionsService.java 
  * @Package com.tgyt.permissions.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:54:00 
  * @version V1.0   
  */

package com.tgyt.permissions.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.ActionsDAO;
import com.tgyt.permissions.model.Actions;

/** 
 * @ClassName: ActionsService 
 * @Description:操作管理服务类 
 * @author sunct sunchaotong18@163.com 
 * @date 2011-9-19 下午2:54:00 
 *  
 */
@Service
public class ActionsService extends BaseService<Actions> implements
		IActionsService {

	@Resource(name="actionsDAO")
	private ActionsDAO actionsDAO;
	public Pagination getActionsList(Actions actions, int pageNo, int pageSize,String sort,String order) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Actions where 1=1");
		if(actions!=null){
			String name = actions.getName();
			String enname = actions.getEnname();
			String methodName = actions.getMethodName();
			String icon = actions.getIcon();
			String memo = actions.getMemo();
			String status = actions.getStatus();
			Integer orderid = actions.getOrderid();
			if(name!=null && !"".equals(name)){
				hql.append(" and name like '%"+name+"%'");
			}
			if(enname!=null && !"".equals(enname)){
				hql.append(" and enname like '%"+enname+"%'");
			}
			if(methodName!=null && !"".equals(methodName)){
				hql.append(" and handler like '%"+methodName+"%'");
			}
			if(icon!=null && !"".equals(icon)){
				hql.append(" and icon like '%"+icon+"%'");
			}
			if(status!=null && !"".equals(status)){
				hql.append(" and status = '"+status+"'");
			}
			if(orderid!=null){
				hql.append(" and orderid = "+orderid);
			}
			if(memo!=null &&!"".equals(memo)){
				hql.append(" and memo like '"+memo+"'");
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
		return this.actionsDAO.getHandler().getPageList(new Finder(hql.toString()), pageNo, pageSize);
	}

	protected DAOInterface<Actions> getDAO() {
		// TODO Auto-generated method stub
		return this.actionsDAO;
	}

	public int getMaxOrder(){
		return this.actionsDAO.getMaxOrder();
	}
	
	public List<Actions> getAll(){
		return this.actionsDAO.getAll();
	}
}
