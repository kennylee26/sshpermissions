package com.tgyt.permissions.biz;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.UsersDao;
import com.tgyt.permissions.model.Users;
@Service
@Transactional
public class UserService extends BaseService<Users> implements IUserService {
	@Autowired
	private UsersDao userDao;
	
	@Override
	protected DAOInterface<Users> getDAO() {
		return userDao;
	}
	
	public String returnRoleName(String userId){
		return userDao.returnRoleName(userId);
	}

	public List<Users> getAll() {
		return userDao.getAll();
	}

	public UsersDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}

	public List<Users> getGroupAll(Integer groupId) {
		return this.userDao.getGroupAll(groupId);
	}

	public Pagination getPageList(Users sys, int page, int rows,String sort,String order) {
		return this.userDao.getPageList(sys, page, rows,sort,order);
	}

	public Boolean saveToGroup(String groupId, Users user) {
		return this.userDao.saveToGroup(groupId, user);
	}

	public Users login(String userName, String password) {
		return this.userDao.login(userName, password);
	}

	public boolean alterToGroup(Users user, String groupId) {
		return this.userDao.alterToGroup(user,groupId);
	}
	public List<Users> findByLoginIds(String ids){
		return this.userDao.findByLoginIds(ids);
	}
	

}
