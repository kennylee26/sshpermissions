/**   
  * @Title: RoleDao.java 
  * @Package com.tgyt.permissions.dao 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author WangMing wang1988ming@qq.com 
  * @date 2011-9-23 下午2:04:26 
  * @version V1.0   
  */

package com.tgyt.permissions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.permissions.model.Role;

/** 
 * @ClassName: RoleDao 
 * @Description: 角色Dao 
 * @author WangMing wang1988ming@qq.com
 * @date 2011-9-23 下午2:04:26 
 *  
 */
@Repository(value="roleDao")
public class RoleDao extends BaseDAO<Role>{
	/** 
	  * @Title: getPageList 
	  * @Description: 根据当前页数与行数得到分页信息
	  * @param  role
	  * @param  pageNo
	  * @param  pageSize
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Role role,int pageNo,int pageSize,String sort,String order){
		StringBuffer hql = new StringBuffer("from Role where 1=1 ");
		if(role!=null){
			String name = role.getName();
			String enname = role.getEnname();
			String status = role.getStatus();
			Integer orderid = role.getOrderid();
			String memo = role.getMemo();
			if(!name.equals("")){
				hql.append(" and name like '%"+name+"%'");
			}
			if(!enname.equals("")){
				hql.append(" and enname like '%"+enname+"%'");
			}
			if(status!=null && !"".equals(status)){
				hql.append(" and status='"+status+"'");
			}
			if(orderid != null){
				hql.append(" and orderid="+orderid);
			}
			if(!memo.equals("")){
				hql.append(" and memo like '%"+memo+"%'");
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
		return this.getHandler().getPageList(Finder.create(hql.toString()), pageNo, pageSize);
	}
	/** 
	  * @Title: addRole 
	  * @Description: 增加一条角色信息到数据库 
	  * @param  role
	  * @return boolean 增加成功返回 true 失败返回false
	  * @throws 
	  */
	/*public boolean addRole(Role role){
		return this.getHandler().saveObj(role);
	}*/
	/** 
	  * @Title: updateRole 
	  * @Description: 根据角色Id更新数据库里面角色信息 
	  * @param  role
	  * @return boolean  更新成功返回 true 失败返回false
	  * @throws 
	  */
/*	public boolean updateRole(Role role){
		return this.getHandler().alterObj(role);
	}*/
	/** 
	  * @Title: deleteRole 
	  * @Description: 根据角色Id删除数据库里面角色信息 
	  * @param  role
	  * @return boolean
	  * @throws 
	  */
	/*public boolean deleteRole(Role role){
		return this.getHandler().deleteObj(role);
	}*/
	/** 
	  * @Title: getAllRole 
	  * @Description: 返回数据库中所有权限信息的集合 
	  * @return List<Role>
	  * @throws 
	  */
	public List<Role> getAllRole(){
		return this.findList("FROM Role");
	}
	/** 
	  * @Title: getMaxOrderId 
	  * @Description: 得到数据库中orderid字段的最大值。 
	  * @return int
	  * @throws 
	  */
	public int getMaxOrderId(){
		return (Integer) this.getHandler().findObj("SELECT MAX(orderid) FROM Role");
	}
}
