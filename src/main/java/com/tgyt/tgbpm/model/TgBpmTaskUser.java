/**   
  * @Title: TgBpmTaskUser.java 
  * @Package com.tgyt.tgbpm.model 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-1-12 上午10:12:14 
  * @version V1.0   
  */

package com.tgyt.tgbpm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
/** 
 * @ClassName: TgBpmTaskUser 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-1-12 上午10:12:14 
 *  
 */
@Entity
@Table(name="tg_bpm_task_user")
public class TgBpmTaskUser implements java.io.Serializable{
	private Integer id;
	private String processId;
	private String taskName;
	private String userNames;
	private String curentUser;
	private String nextUser;
	private String userIds;
	/** 
	  * <p>Title: </p> 
	  * <p>Description: </p>  
	  */ 
	
	public TgBpmTaskUser() {
		super();
	}


	
	/** 
	  * <p>Title: </p> 
	  * <p>Description: </p> 
	  * @param id
	  * @param processId
	  * @param taskName
	  * @param userNames
	  * @param curentUser
	  * @param nextUser
	  * @param userIds 
	  */ 
	
	public TgBpmTaskUser(Integer id, String processId, String taskName,
			String userNames, String curentUser, String nextUser, String userIds) {
		super();
		this.id = id;
		this.processId = processId;
		this.taskName = taskName;
		this.userNames = userNames;
		this.curentUser = curentUser;
		this.nextUser = nextUser;
		this.userIds = userIds;
	}



	/**
	 * @return the id
	 */
	  @Id 
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  @Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the processId
	 */
	@Column(name="processId",length=100)
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @return the taskName
	 */
	@Column(name="taskName",length=200)
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return the userNames
	 */
	@Column(name="userNames",length=2000)
	public String getUserNames() {
		return userNames;
	}
	/**
	 * @param userNames the userNames to set
	 */
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	/**
	 * @return the curentUser
	 */
	@Column(name="curentUser",length=200)
	public String getCurentUser() {
		return curentUser;
	}
	/**
	 * @param curentUser the curentUser to set
	 */
	public void setCurentUser(String curentUser) {
		this.curentUser = curentUser;
	}
	/**
	 * @return the nextUser
	 */
	@Column(name="nextUser",length=200)
	public String getNextUser() {
		return nextUser;
	}
	/**
	 * @param nextUser the nextUser to set
	 */
	public void setNextUser(String nextUser) {
		this.nextUser = nextUser;
	}



	/**
	 * @return the userIds
	 */
	@Column(name="userIds",length=400)
	public String getUserIds() {
		return userIds;
	}



	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	

}