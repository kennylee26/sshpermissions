package com.tgyt.flowList.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CFlowtype entity. @author MyEclipse Persistence Tools
 */
/** 
  * @ClassName: CFlowtype 
  * @Description: 工作流类型实体类
  * @author haoly  454688562@qq.com
  * @date 2012-4-17 上午10:41:03 
  *  
  */
@Entity
@Table(name = "c_flowtype", catalog = "tgpermission")
public class CFlowtype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typename;
	private Integer parentid;
	private Integer sort;
	private Integer list;

	// Constructors

	/** default constructor */
	public CFlowtype() {
	}

	/** full constructor */
	public CFlowtype(String typename, Integer parentid, Integer sort,
			Integer list) {
		this.typename = typename;
		this.parentid = parentid;
		this.sort = sort;
		this.list = list;
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

	@Column(name = "typename", length = 100)
	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(name = "parentid")
	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "list")
	public Integer getList() {
		return this.list;
	}

	public void setList(Integer list) {
		this.list = list;
	}

}