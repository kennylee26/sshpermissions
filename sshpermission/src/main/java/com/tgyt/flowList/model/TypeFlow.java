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
@Table(name = "type_flow")
public class TypeFlow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private Integer typeid;
	private Integer flowid;
	private String flowname;
	private String flowdescribe;
	private String flag;

	// Constructors

	/** default constructor */
	public TypeFlow() {
	}

	/** full constructor */
	public TypeFlow(Integer typeid, Integer flowid, String flowname,Integer version,
			String flowdescribe,String flag) {
		this.typeid = typeid;
		this.flowid = flowid;
		this.flowname = flowname;
		this.flowdescribe = flowdescribe;
		this.flag=flag;
		this.version=version;
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
	@Column(name = "flag", length = 10)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}