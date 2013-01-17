package com.tgyt.permissions.biz;

import java.util.List;
import java.util.Map;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Role;

/** 
  * @ClassName: IGroupService 
  * @Description: 组的业务层接口
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:43:07 
  *  
  */
public interface IGroupService extends ServiceInterface<Groups> {
	
	/** 
	  * @Title: getTree 
	  * @Description: 获取组树 
	  * @param id 组的id
	  * @return void
	  * @throws 
	  */
	public List<Map<String,Object>> getTree(String id);
	
	/** 
	  * @Title: removeGroupHaveRole 
	  * @Description: 移除该组的某些角色
	  * @param @param ids
	  * @param @param roleIds
	  * @return void
	  * @throws 
	  */
	public void removeGroupHaveRole(String id,String roleIds);
	
	/** 
	  * @Title: groupHaveRole 
	  * @Description: 查看该组所拥有的角色
	  * @param @return
	  * @return List<Role>
	  * @throws 
	  */
	public List<Role> groupHaveRole(String groupid);
	/** 
	  * @Title: getAll 
	  * @Description: 获取所有的组
	  * @param @return
	  * @return List<Groups>
	  * @throws 
	  */
	public List<Groups> getAll(); 
	/** 
	  * @Title: addUserToGroup 
	  * @Description: 往组里面添加具体用户
	  * @param @param userIds 用户的id组和
	  * @param @param groupId 组id
	  * @return void
	  * @throws 
	  */
	public void addUserToGroup(String userIds,String groupId);
	/** 
	  * @Title: delUserFromGroup 
	  * @Description:从组里面删除具体用户
	  * @param @param groupId 组id
	  * @param @param id 用户id
	  * @return void
	  * @throws 
	  */
	public void delUserFromGroup(String groupId,String id);
	
	/** 
	  * @Title: otherRoleFromGroup 
	  * @Description: 我要获取该组所没有的角色
	  * @param @param groupId 组id
	  * @return List<Role>
	  * @throws 
	  */
	public List<Role> otherRoleFromGroup(String groupId);
	

	/** 
	  * @Title: addRoleToGroup 
	  * @Description: 往组里添加角色
	  * @param @param roleIds 角色id的组和
	  * @param @param groupId 组id
	  * @return void
	  * @throws 
	  */
	public void addRoleToGroup(String roleIds,String groupId);
	
	public List<Groups> getGroups(String nameGroup);
}
