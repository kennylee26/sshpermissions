/**   
  * @Title: IRoleService.java 
  * @Package com.tgyt.permissions.biz 
  * @Description: 
  * @author WangMing wang1988ming@qq.com 
  * @date 2011-9-23 下午2:42:10 
  * @version V1.0   
  */

package com.tgyt.permissions.biz;

import java.util.List;
import java.util.Map;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.Systems;

/** 
 * @ClassName: IRoleService 
 * @Description: 角色服务层接口 
 * @author WangMing wang1988ming@qq.com
 * @date 2011-9-23 下午2:42:10 
 *  
 */
public interface IRoleService extends ServiceInterface<Role>{
	/** 
	  * @Title: addRole 
	  * @Description: 添加角色 
	  * @param @param role
	  * @param @return
	  * @return boolean
	  * @throws 
	  */
	public boolean addRole(Role role);
	/** 
	  * @Title: getPageList 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param role
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @param sort 排序字段
	  * @param @param order 排序顺序
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Role role,int pageNo,int pageSize,String sort,String order);
	
	/** 
	  * @Title: getAllActions 
	  * @Description: 获得所有操作信息记录
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getAllActions();
	
	/** 
	  * @Title: saveAuthorizate 
	  * @Description: 给角色添加资源 
	  * @param @param roldId
	  * @param @param resourcesIds
	  * @return void
	  * @throws 
	  */
	public void saveAuthorizate(Integer roldId,String resourcesIds);
	
	/** 
	  * @Title: getAllListTrees 
	  * @Description: 根据角色ID获得资源树，其中角色有权限的资源checkbox为true 
	  * @param @param roleId
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getAllListTrees(Integer roleId);
	
	/** 
	  * @Title: getTree 
	  * @Description: 根据传入的角色所能看到的资源map，在构造每棵资源树时，标识对应的资源checkbox为true
	  * @param @param maps
	  * @param @param system
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getTree(Map<Integer,Object> maps,Systems system);
	
	/** 
	  * @Title: saveResActions 
	  * @Description: 保存指定角色的资源对应的操作权限
	  * @param @param roleId
	  * @param @param resActions
	  * @return void
	  * @throws 
	  */
	public void saveResActions(Integer roleId,String resActions);
}
