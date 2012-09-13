package com.tgyt.permissions.controls;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.biz.IUserService;
import com.tgyt.permissions.common.HasParent;
import com.tgyt.permissions.model.CloneResources;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Users;
import com.tgyt.permissions.tree.Utils;
import com.tgyt.tree.Node;
import com.tgyt.tree.UncodeException;
import com.tgyt.tree.UserDataUncoder;
import com.tgyt.tree.support.AbstractWebTreeModelCreator;
import com.tgyt.tree.support.WebTreeNode;

/** 
  * @ClassName: LoginTg 
  * @Description: 用户登录
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-29 上午10:43:55 
  *  
  */
@Scope("prototype")
@Controller(value="loginTgControl")
public class LoginTg extends BaseTg{
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String rememberMe;
		public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	/**
	 * @return the agentname
	 */
	public String getAgentname() {
		return agentname;
	}
	/**
	 * @param agentname the agentname to set
	 */
	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}
	private String agentname;
	private String username;
	private String password;
	@Autowired
	private IUserService userService;
	
	/** 
	  * @Title: login 
	  * @Description: 用户登录方法
	  * @param @return
	  * @return String
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	public String login() {
		UsernamePasswordToken token=null;
		List<Users> userList = null;
		if(agentname == null||"".equals(agentname)){
			token = new UsernamePasswordToken(username, password, Boolean.valueOf(rememberMe));
		}else{
			userList = userService.findList("from Users where logonid ='"+agentname+"'");
			for (int i = 0; i < userList.size(); i++) {
				System.out.println(userList.get(i).getLogonid());
				token = new UsernamePasswordToken(userList.get(i).getLogonid(), userList.get(i).getPassword(), Boolean.valueOf(rememberMe));
			}
		}
		Subject user = SecurityUtils.getSubject();
		try {
			user.login(token);
			HasParent.isHasParent();
			List<Resources> list = (List<Resources>) SecurityUtils.getSubject().getSession().getAttribute("resourceList");
			
			HasParent.returnCurrentObj(null,request);
//			List<Resources> xxx = HasParent.returnCurrentObj(null,request);
//			System.out.println(request.getAttribute("jsonOne"));
//			System.out.println(request.getAttribute("jsonTwo"));
			
			
			List<CloneResources> resource = new ArrayList<CloneResources>();
			
			for(int i=0;i<list.size();i++){
				Resources temp = (Resources)list.get(i);
				CloneResources cloe = new CloneResources();
				cloe.setId(temp.getId());
				cloe.setParentId(null == temp.getParent() ? null:temp.getParent().getId()+"");
				cloe.setName(temp.getName());
				cloe.setIcon(temp.getIcon());
				cloe.setLink(temp.getLink());
				resource.add(cloe);
			}
			
			// 业务数据解码器，从业务数据中分解出id和parentid
			UserDataUncoder orgUncoder = new UserDataUncoder() {
				public Object getID(Object pUserData) throws UncodeException {
					CloneResources org = (CloneResources) pUserData;
					return org.getId();
				}

				public Object getParentID(Object pUserData) throws UncodeException {
					CloneResources org = (CloneResources) pUserData;
					return null == org.getParentId()?null:Integer.valueOf(org.getParentId());
				}
			};

			// Tree模型构造器，用于生成树模型
			AbstractWebTreeModelCreator treeModelCreator = new AbstractWebTreeModelCreator() {
				// 该方法负责将业务数据映射到树型节点
				protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
					CloneResources org = (CloneResources) pUserData;
					WebTreeNode result = new WebTreeNode(org.getName(), "node"+org.getId());
					result.setIcon(org.getIcon());
					result.setAction(org.getLink());
					return result;
				}
			};
			
			String temp = Utils.getTree(orgUncoder, treeModelCreator, resource, request);
			request.setAttribute("treescript", temp);
			
			return "success";
		} catch (Exception e) {
			System.out.println("验证出错！");
			return "error";
		}
		
		
		
	}
	public String logout(){
			SecurityUtils.getSubject().logout();
			return "error";
	}
	

}
