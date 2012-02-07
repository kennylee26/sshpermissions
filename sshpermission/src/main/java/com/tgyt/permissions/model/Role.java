/**   
  * @Title: Role.java 
  * @Package com.tgyt.permissions.model 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author WangMing wang1988ming@qq.com 
  * @date 2011-9-23 下午1:42:08 
  * @version V1.0   
  */

package com.tgyt.permissions.model;

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
 * @ClassName: Role 
 * @Description: 权限实体类 
 * @author WangMing wang1988ming@qq.com
 * @date 2011-9-23 下午1:42:08 
 *  
 */
@Entity
@Table(name="c_role")
public class Role implements java.io.Serializable {
	private static final long serialVersionUID = -5911423238732456148L;
	private Integer id;
	private String name;
	private String enname;
	private String status;
	private Integer orderid;
	private String memo;
	
	private Set<Groups> groups = new HashSet<Groups>();
	
	private Set<Resources> resources = new HashSet<Resources>();
	@Id
	@Column(name="id",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",length=30,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="enname",length=30)
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	@Column(name="status",nullable=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="orderid")
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	@Column(name="memo",length=50)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Role() {
		super();
	}
	
	public Role(Integer id, String name, String enname, String status,
			Integer orderid, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.enname = enname;
		this.status = status;
		this.orderid = orderid;
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", enname=" + enname
				+ ", status=" + status + ", orderid=" + orderid + ", memo="
				+ memo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="c_grouprole",
			joinColumns={@JoinColumn(name="roleid")},
			inverseJoinColumns={@JoinColumn(name="groupid")}
	)
	public Set<Groups> getGroups() {
		return groups;
	}
	public void setGroups(Set<Groups> groups) {
		this.groups = groups;
	}
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="c_roleauth",
			joinColumns={@JoinColumn(name="roleid")},
			inverseJoinColumns={@JoinColumn(name="resourceid")}
	)
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
	
	
	
}
