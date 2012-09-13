package com.tgyt.permissions.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.GroupDao;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Role;

@Service
@Transactional
public class GroupService extends BaseService<Groups> implements IGroupService {
	@Autowired
	private GroupDao groupDao;
	
	public List<Groups> getAll() {
		return groupDao.getAll();
	}

	@Override
	protected DAOInterface<Groups> getDAO() {
		return groupDao;
	}

	public void addUserToGroup(String userIds,String groupId) {
		 groupDao.addUserToGroup(userIds,groupId);
	}

	public void delUserFromGroup(String groupId, String id) {
		groupDao.delUserFromGroup(groupId, id);
	}
	
	public List<Role> otherRoleFromGroup(String groupId) {
		return groupDao.otherRoleFromGroup(groupId);
	}
	
	public void addRoleToGroup(String roleIds, String groupId) {
		this.groupDao.addRoleToGroup(roleIds, groupId);
	}
	
	public List<Role> groupHaveRole(String groupid) {
		return this.groupDao.groupHaveRole(groupid);
	}

	public void removeGroupHaveRole(String id, String roleIds) {
		this.groupDao.removeGroupHaveRole(id, roleIds);
	}
	
	public List<Map<String,Object>> getTree(String id) {
		return this.groupDao.getTree(id);
	}

	/* (non-Javadoc)
	 * <p>Title: getGroups</p> 
	 * <p>Description: </p> 
	 * @param nameGroup
	 * @return 
	 * @see com.tgyt.permissions.biz.IGroupService#getGroups(java.lang.String) 
	 */
	
	@Override
	public List<Groups> getGroups(String nameGroup) {
		return groupDao.getGroups(nameGroup);
	}

}
