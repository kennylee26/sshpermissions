package com.tgyt.permissions.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
  * @ClassName: Systems 
  * @Description: 系统信息实体类
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午10:03:39 
  *  
  */
@Entity
@Table(name = "c_system")
public class Systems implements Serializable {
	private String builddate;
	private String contextPath;
	private String ename;
	private String icon;
	private Integer id;
	private String logo;
	private String memo;
	private String name;
	private Integer order;
	private String status;
	private String tablePrefix;
	private String version;

	public Systems() {
		super();
	}

	public Systems(Integer id, String name, String ename, String contextPath,
			String tablePrefix, String logo, String icon, String version,
			String builddate, String status, Integer order, String memo) {
		this.id = id;
		this.name = name;
		this.ename = ename;
		this.contextPath = contextPath;
		this.tablePrefix = tablePrefix;
		this.logo = logo;
		this.icon = icon;
		this.version = version;
		this.builddate = builddate;
		this.status = status;
		this.order = order;
		this.memo = memo;
	}
	
	@Column(name="builddate",length=10)
	public String getBuilddate() {
		return builddate;
	}
	@Column(name="contextpath",length=50,nullable=false)
	public String getContextPath() {
		return contextPath;
	}
	@Column(name="enname",length=30)
	public String getEname() {
		return ename;
	}
	@Column(name="icon",length=30)
	public String getIcon() {
		return icon;
	}
	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name="logo",length=30)
	public String getLogo() {
		return logo;
	}
	@Column(name="memo",length=50)
	public String getMemo() {
		return memo;
	}
	@Column(name="name",nullable=false,length=30)
	public String getName() {
		return name;
	}
	@Column(name="orderid",length=10)
	public Integer getOrder() {
		return order;
	}
	@Column(name="status",nullable=false,length=10)
	public String getStatus() {
		return status;
	}
	@Column(name="tableprefix",nullable=false,length=50)
	public String getTablePrefix() {
		return tablePrefix;
	}
	@Column(name="version",length=10)
	public String getVersion() {
		return version;
	}

	public void setBuilddate(String builddate) {
		this.builddate = builddate;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
