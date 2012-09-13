package com.tgyt.permissions.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/** 
  * @ClassName: Groups 
  * @Description: 组实体类
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午10:02:29 
  *  
  */
@Entity
@Table(name = "c_group")
public class Groups implements Serializable {
	private String enName;
	private String groupType;
	private Integer id;
	private String memo;
	private String name;
	private String orderId;
	private Integer parentId;
	private String status;
	
	private Set<Users> users = new HashSet<Users>();
	
	private Set<Role> roles = new HashSet<Role>();
	
	@Column(name="enname",length=30)
	public String getEnName() {
		return enName;
	}
	@Column(name="grouptype",length=20)
	public String getGroupType() {
		return groupType;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
	public Integer getId() {
		return id;
	}
	@Column(name="memo")
	public String getMemo() {
		return memo;
	}
	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}
	@Column(name="orderid")
	public String getOrderId() {
		return orderId;
	}
	@Column(name="parentid")
	public Integer getParentId() {
		return parentId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(
			name="c_usergroup",
			joinColumns={@JoinColumn(name="groupid")},
			inverseJoinColumns={@JoinColumn(name="userid")}
	)
	public Set<Users> getUsers() {
		return users;
	}
	
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(
			name="c_grouprole",
			joinColumns={@JoinColumn(name="groupid")},
			inverseJoinColumns={@JoinColumn(name="roleid")}
	)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
