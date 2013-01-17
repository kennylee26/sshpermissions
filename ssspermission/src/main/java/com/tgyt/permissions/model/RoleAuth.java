/**   
  * @Title: RoleAuth.java 
  * @Package com.tgyt.permissions.model 
  * @Description: 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-30 上午10:30:29 
  * @version V1.0   
  */

package com.tgyt.permissions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: RoleAuth 
 * @Description:角色权限实体
 * @author sunct sunchaotong18@163.com 
 * @date 2011-9-30 上午10:30:29 
 *  
 */
@Entity
@Table(name="c_roleauth")
public class RoleAuth {

	private Integer id;
	private Integer roleId;
	private Integer resourceId;
	private String actions;
	
	public RoleAuth() {
		super();
	}
	
	public RoleAuth(Integer id, Integer roleId, Integer resourceId,
			String actions) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.resourceId = resourceId;
		this.actions = actions;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="roleid")
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	@Column(name="resourceid")
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	@Column(name="actions")
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	
}
