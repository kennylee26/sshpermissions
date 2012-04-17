package com.tgyt.flowList.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * TypeFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "type_flow", catalog = "tgpermission")
public class TypeFlow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private Integer typeid;
	private Integer flowid;
	private String flowname;
	private String flowdescribe;

	// Constructors

	/** default constructor */
	public TypeFlow() {
	}

	/** full constructor */
	public TypeFlow(Integer typeid, Integer flowid, String flowname,
			String flowdescribe) {
		this.typeid = typeid;
		this.flowid = flowid;
		this.flowname = flowname;
		this.flowdescribe = flowdescribe;
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

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	@Column(name = "flowname", length = 100)
	public String getFlowname() {
		return this.flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	@Column(name = "flowdescribe", length = 400)
	public String getFlowdescribe() {
		return this.flowdescribe;
	}

	public void setFlowdescribe(String flowdescribe) {
		this.flowdescribe = flowdescribe;
	}

}