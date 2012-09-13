package com.tgyt.permissions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
  * @ClassName: Dictionarys 
  * @Description: 系统字典实体类
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午10:02:06 
  *  
  */
@Entity
@Table(name = "c_dictionarys")
public class Dictionarys implements java.io.Serializable{
	private String createTime;
	//创建人
	private String creator;
	private Integer id;
	private String memo;
	private String name;
	private String nickName;
	private String orderid;
	private String status;
	//常量排序
	private String type;
	
	//是否可编辑
	private String updaTable;
	private String value;
	
	public Dictionarys() {
		super();
	}
	public Dictionarys(Integer id, String nickName, String name, String value,
			String createTime, String type, String updaTable, String creator,
			String status, String orderid, String memo) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.name = name;
		this.value = value;
		this.createTime = createTime;
		this.type = type;
		this.updaTable = updaTable;
		this.creator = creator;
		this.status = status;
		this.orderid = orderid;
		this.memo = memo;
	}
	@Column(name="createtime",length=30)
	public String getCreateTime() {
		return createTime;
	}
	@Column(name="creator",length=30)
	public String getCreator() {
		return creator;
	}
	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name="memo",length=50)
	public String getMemo() {
		return memo;
	}
	@Column(name="name",length=30)
	public String getName() {
		return name;
	}
	@Column(name="nickname",length=30)
	public String getNickName() {
		return nickName;
	}
	@Column(name="orderid",length=10)
	public String getOrderid() {
		return orderid;
	}
	@Column(name="status",length=30)
	public String getStatus() {
		return status;
	}
	@Column(name="type",length=30)
	public String getType() {
		return type;
	}
	@Column(name="updatable",length=10)
	public String getUpdaTable() {
		return updaTable;
	}
	@Column(name="value",length=50)
	public String getValue() {
		return value;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUpdaTable(String updaTable) {
		this.updaTable = updaTable;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
