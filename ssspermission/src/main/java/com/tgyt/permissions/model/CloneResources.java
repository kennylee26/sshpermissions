/**   
  * @Title: CloneResources.java 
  * @Package com.tgyt.permissions.model 
  * @Description: 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-20 下午1:20:07 
  * @version V1.0   
  */

package com.tgyt.permissions.model;


/** 
 * @ClassName: CloneResources 
 * @Description: 该类是作为Resources的复制类使用，也就是在转换成json串时直接使用此类
 * @author sunct sunchaotong18@163.com 
 * @date 2011-9-20 下午1:20:07 
 *  
 */
public class CloneResources {

	private Integer id;
    private String parentId;
    private String parentName;
    private Integer systemId;
    private String systemName;
    private String name;
    private String enname;
    private String resourcetype;
    private String link;
    private String icon;
    private String iconopen;
    private String isopen;
    private String isleaf;
    private String status;
    private Integer orderid;
    private String memo;
    
	/** 
	  * <p>Title: </p> 
	  * <p>Description: </p>  
	  */ 
	
	public CloneResources() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	  * <p>Title: </p> 
	  * <p>Description: </p> 
	  * @param id
	  * @param parentId
	  * @param parentName
	  * @param systemId
	  * @param systemName
	  * @param name
	  * @param enname
	  * @param resourcetype
	  * @param link
	  * @param icon
	  * @param iconopen
	  * @param isopen
	  * @param isleaf
	  * @param status
	  * @param orderid
	  * @param memo 
	  */ 
	
	public CloneResources(Integer id, String parentId, String parentName,
			Integer systemId, String systemName, String name, String enname,
			String resourcetype, String link, String icon, String iconopen,
			String isopen, String isleaf, String status, Integer orderid,
			String memo) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.parentName = parentName;
		this.systemId = systemId;
		this.systemName = systemName;
		this.name = name;
		this.enname = enname;
		this.resourcetype = resourcetype;
		this.link = link;
		this.icon = icon;
		this.iconopen = iconopen;
		this.isopen = isopen;
		this.isleaf = isleaf;
		this.status = status;
		this.orderid = orderid;
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public String getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconopen() {
		return iconopen;
	}
	public void setIconopen(String iconopen) {
		this.iconopen = iconopen;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
    
}
