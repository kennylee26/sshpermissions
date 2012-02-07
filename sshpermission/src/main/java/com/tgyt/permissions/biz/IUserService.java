package com.tgyt.permissions.biz;

import java.util.List;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.Systems;
import com.tgyt.permissions.model.Users;

/** 
  * @ClassName: IUserService 
  * @Description: 用户业务层接口
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:48:58 
  *  
  */
public interface IUserService extends ServiceInterface<Users> {
	
	public boolean alterToGroup(Users user,String groupId);
	/** 
	  * @Title: getPageList 
	  * @Description: 获取具体某页的用户信息
	  * @param @param sys 一条具体用户Users对象的信息
	  * @param @param page 当前页
	  * @param @param rows 一页显示的记录
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Users sys,int page,int rows,String sort,String order);
	/** 
	  * @Title: getAll 
	  * @Description: 获取所有的用户信息
	  * @param @return
	  * @return List<Users>
	  * @throws 
	  */
	public List<Users> getAll();
	/** 
	  * @Title: getGroupAll 
	  * @Description: 获取某组下的所有用户信息
	  * @param @param groupId 组id
	  * @param @return
	  * @return List<Users>
	  * @throws 
	  */
	public List<Users> getGroupAll(Integer groupId);
	/** 
	  * @Title: saveToGroup 
	  * @Description: 往组里面添加用户
	  * @param @param groupId  组id
	  * @param @param user 具体用户对象
	  * @param @return
	  * @return Boolean
	  * @throws 
	  */
	public Boolean saveToGroup(String groupId,Users user);
	public Users login(String userName,String password);
	
	public String returnRoleName(String userId);
}
