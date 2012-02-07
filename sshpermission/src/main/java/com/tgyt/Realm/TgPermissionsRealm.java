package com.tgyt.Realm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tgyt.permissions.biz.IRoleAuthService;
import com.tgyt.permissions.biz.IUserService;
import com.tgyt.permissions.model.Groups;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.RoleAuth;
import com.tgyt.permissions.model.Users;




/** 
  * @ClassName: TgPermissionsRealm 
  * @Description: 实际执行认证的类
  * @author WangMing wang1988ming@qq.com
  * @date 2011-10-5 上午10:58:26 
  *  
  */
public class TgPermissionsRealm extends AuthorizingRealm{
	@Resource(name="roleAuthService")
	private IRoleAuthService roleAuthService;
	Users user;
	Set<Role> userRoles;
	Set<Resources> resources;
	@Autowired
	private IUserService userService;
	public TgPermissionsRealm(){
		setName("TgPermissionsRealm");
	}

	/* (non-Javadoc)
	 * <p>Title: doGetAuthorizationInfo</p> 
	 * <p>Description: 授权</p> 
	 * @param principals
	 * @return 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection) 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<Groups> userGroups = user.getGroups();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(user!=null){
			for(Groups group: userGroups){
				userRoles = group.getRoles();
				for(Role role : userRoles){
					info.addRole(role.getName());
					resources = role.getResources();
					for(Resources resource : resources){
						RoleAuth roleAuth = this.roleAuthService.find("from RoleAuth where roleId ="+role.getId()+" and resourceId ="+resource.getId());
						String[] actionString = roleAuth.getActions().split(",");
						for(String action : actionString){
							info.addStringPermission(action);
						}
						//info.addStringPermission(roleAuth.getActions());
					}
				}
			}
			return info;
		}
		return null;
	}


	/* (non-Javadoc)
	 * <p>Title: doGetAuthenticationInfo</p> 
	 * <p>Description: 认证</p> 
	 * @param authtoken
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authtoken) throws AuthenticationException {
		List<Resources> resourceList = new ArrayList<Resources>();
		UsernamePasswordToken token = (UsernamePasswordToken) authtoken;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String userName = token.getUsername();
		if(userName != null && !"".equals(userName)){
			user = this.userService.login(token.getUsername(), String.valueOf(token.getPassword()));

			if( user != null ){

				Set<Groups> userGroups = user.getGroups();
				//SecurityUtils.getSubject().getSession().setAttribute("userGroup", userGroups);
				for(Groups group: userGroups){
					//System.out.println("组名："+group.getName()+"\tid:"+group.getId());
					userRoles = group.getRoles();
					//SecurityUtils.getSubject().getSession().setAttribute("userRole", userRoles);
					for(Role role : userRoles){
						//System.out.println("角色名："+role.getName()+"\tId"+role.getId());
						info.addRole(role.getName());
						resources = role.getResources();
						//SecurityUtils.getSubject().getSession().setAttribute("userResource", resources);
						for(Resources resource : resources){
							//System.out.println("资源:"+resource.getName()+"\tid"+resource.getId());
							resourceList.add(resource);
							//RoleAuth roleAuth = this.roleAuthService.find("from RoleAuth where roleId ="+role.getId()+" and resourceId ="+resource.getId());
							//info.addStringPermission(roleAuth.getActions());
						}
					}
				}
				//将该用户的所有资源进行排序排列
				Collections.sort(resourceList, new Comparator<Resources>(){
					
					public int compare(Resources o1, Resources o2) {
						return o1.getId().compareTo(o2.getId());
					}
					
				});
				SecurityUtils.getSubject().getSession().setAttribute("resourceList", resourceList);
				return new SimpleAuthenticationInfo( user.getLogonid(),user.getPassword(), getName());  
			}
		}
		return null;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IRoleAuthService getRoleAuthService() {
		return roleAuthService;
	}
	public void setRoleAuthService(IRoleAuthService roleAuthService) {
		this.roleAuthService = roleAuthService;
	}

}
