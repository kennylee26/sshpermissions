package com.tgyt.permissions.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.permissions.biz.ISystemService;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.Users;

/** 
  * @ClassName: GroupDao 
  * @Description: 组Dao层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:53:56 
  *  
  */
@Repository
public class GroupDao extends BaseDAO<Groups> {
	
	public List<Map<String,Object>> getTree(String id){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		//存放每层资源的临时变量
		List<Map<String,Object>> temp = new ArrayList<Map<String,Object>>();
		//第一级资源
		List<Groups> types  =  this.getHandler().findListOfObj("from Groups where parentid is null");
		if(null == types || types.size() == 0){
			return nodes;
		}
		for(Groups type: types){
				Map<String,Object> node = new HashMap<String,Object>();
				node.put("id",  type.getId()+"");
				node.put("text", type.getName());
				nodes.add(node);
				temp.add(node);
		}
		//临时
		List<Map<String,Object>> doing = new ArrayList<Map<String,Object>>();
		doing.addAll(nodes);

		while(!doing.isEmpty()){
			temp = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> item: doing){
				List<Object> children = new ArrayList<Object>();
				String pid = (String)item.get("id");
				List<Groups> tempTypes  =  this.getHandler().findListOfObj("from Groups where parentid='" + pid + "'");
				for(Groups type: tempTypes){
					Map<String,Object> node = new HashMap<String,Object>();
					node.put("id",  type.getId()+"");
					node.put("text", type.getName());
					temp.add(node);
					children.add(node);
				}
				item.put("children", children.toArray(new Object[children.size()]));
			}
			doing = temp;
		}
		return nodes;
	}
	
	/** 
	  * @Title: removeGroupHaveRole 
	  * @Description: 移除该组的某些角色
	  * @param @param id
	  * @param @param roleIds
	  * @return void
	  * @throws 
	  */
	public void removeGroupHaveRole(String id, String roleIds){
		Groups group = (Groups) this.getHandler().findObjById(Groups.class, Integer.valueOf(id));
		Set<Role> roles = group.getRoles();
		
		String[] tem = roleIds.split(",");
		for(int i=0;i<tem.length;i++){
			Role role = (Role)this.getHandler().findObjById(Role.class, Integer.valueOf(tem[i].trim()));
			roles.remove(role);
		}
		
		group.setRoles(roles);
	}
	
	/** 
	  * @Title: groupHaveRole 
	  * @Description: 查看该组所拥有的角色
	  * @param @return
	  * @return List<Role>
	  * @throws 
	  */
	public List<Role> groupHaveRole(String groupid){
		List<Role> result = new ArrayList<Role>();
		Groups group = (Groups) this.getHandler().findObjById(Groups.class, Integer.valueOf(groupid));
		Set<Role> roles = group.getRoles();
		Iterator<Role> it = roles.iterator();
		while(it.hasNext()){
			result.add(it.next());
		}
		
		return result;
	}
	
	/** 
	  * @Title: addRoleToGroup 
	  * @Description: 往组里添加角色
	  * @param @param roleIds 角色id的组和
	  * @param @param groupId 组id
	  * @return void
	  * @throws 
	  */
	public void addRoleToGroup(String roleIds, String groupId){
		Groups group = (Groups) this.getHandler().findObjById(Groups.class, Integer.valueOf(groupId));
		Set<Role> groupRoles = group.getRoles();
		
		String[] ids = roleIds.split(",");
		for(int i=0;i<ids.length;i++){
			Role temp = (Role)this.getHandler().findObjById(Role.class, Integer.valueOf(ids[i].trim()));
			groupRoles.add(temp);
		}
		
		group.setRoles(groupRoles);
	}

	/** 
	  * @Title: getAll 
	  * @Description: 获取所有组信息
	  * @param @return
	  * @return List<Groups>
	  * @throws 
	  */
	public List<Groups> getAll() {
		StringBuffer sb = new StringBuffer("from Groups");
		return this.getHandler().findListOfObj(sb.toString());
	}
	
	/** 
	  * @Title: getGroups 
	  * @Description: 根据组名找组
	  * @param @param nameGroup
	  * @param @return
	  * @return List<Groups>
	  * @throws 
	  */
	public List<Groups> getGroups(String nameGroup) {
		StringBuffer sb = new StringBuffer("from Groups where 1=1");
			if(null !=nameGroup && !"".equals(nameGroup)){
				sb.append(" and name like'%" + nameGroup + "%'");
			}
		return this.getHandler().findListOfObj(sb.toString());
	}
	/** 
	  * @Title: addUserToGroup 
	  * @Description: 往组里面添加用户
	  * @param @param userIds 用户id组和
	  * @param @param groupId 组id
	  * @return void
	  * @throws 
	  */
	public void addUserToGroup(String userIds,String groupId) {
		
		String[] ids = userIds.split(",");
		Groups group = (Groups)this.getHandler().findObjById(Groups.class, Integer.valueOf(groupId));
		
		Set<Users> set = group.getUsers();
		for(int i=0;i<ids.length;i++){
			set.add((Users)this.getHandler().findObjById(Users.class, Integer.valueOf(ids[i].trim())));
		}
		
		group.setUsers(set);
	}
	/** 
	  * @Title: delUserFromGroup 
	  * @Description: 从组里面删除用户
	  * @param @param groupId 组id
	  * @param @param id 用户id (多个用户的id例如：“2,3,”)
	  * @return void
	  * @throws 
	  */
	public void delUserFromGroup(String groupId, String id){
		Groups group = (Groups)this.getHandler().findObjById(Groups.class, Integer.valueOf(groupId));
		//没有删除之前，该组所有拥有的所有用户
		Set<Users> all = group.getUsers();
		
		id = id.substring(0,id.lastIndexOf(","));
		
		String[] ids = id.split(",");
		//要删除组内的多个用户
		Set<Users> result = new HashSet<Users>();
		for(int i=0;i<ids.length;i++){
			Users user = (Users)this.getHandler().findObjById(Users.class, Integer.valueOf(ids[i]));
			result.add(user);
		}
		
		if(all.removeAll(result)){
			group.setUsers(all);
		}
	}
	
	/** 
	  * @Title: otherRoleFromGroup 
	  * @Description: 我要获取该组所没有的角色
	  * @param @param groupId 组id
	  * @return List<Role>
	  * @throws 
	  */
	public List<Role> otherRoleFromGroup(String groupId){
		Groups group = (Groups)this.getHandler().findObjById(Groups.class, Integer.valueOf(groupId));
		//该组所拥有的角色
		Set<Role> role = group.getRoles();
		
		List<Role> groupList = new ArrayList<Role>();
		Iterator<Role> it = role.iterator();
		while(it.hasNext()){
			groupList.add(it.next());
		}
		//获取所有的角色 可以使用的角色
		List<Role> list = this.getHandler().findListOfObj("from Role where status='status100'");
		
		list.removeAll(groupList);
		
		return list;
	}
	
}
