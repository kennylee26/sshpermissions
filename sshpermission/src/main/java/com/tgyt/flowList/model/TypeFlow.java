package com.tgyt.flowList.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TypeFlow entity. @author MyEclipse Persistence Tools
 */
/** 
  * @ClassName: TypeFlow 
  * @Description: 工作流与类型中间表
  * @author haoly  454688562@qq.com
  * @date 2012-4-17 上午10:44:25 
  *  
  */
@Entity
@Table(name = "type_flow", catalog = "tgpermission")
public class TypeFlow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer typeid;
	private Integer flowid;

	// Constructors

	/** default constructor */
	public TypeFlow() {
	}

	/** full constructor */
	public TypeFlow(Integer typeid, Integer flowid) {
		this.typeid = typeid;
		this.flowid = flowid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "typeid")
	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	@Column(name = "flowid")
	public Integer getFlowid() {
		return this.flowid;
	}

	public void setFlowid(Integer flowid) {
		this.flowid = flowid;
	}

}