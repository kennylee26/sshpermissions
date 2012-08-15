/**   
  * @Title: WebTreeNode.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:25:10 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.util.LinkedHashMap;
import java.util.Map;

/** 
 * @ClassName: WebTreeNode 
 * @Description: web tree 结点
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:25:10 
 *  
 */
public class WebTreeNode extends DefaultNode{
	private static final long serialVersionUID = 1L;
    //注意：该ID要是合法标识符号,因为TreeBuilder可能使用该ID作为js变量 
	private String id;	

	public WebTreeNode() {
		super();
	}

	public WebTreeNode(String pName) {
		super(pName);
	}
	
	public WebTreeNode(String pName, Object pUserData) {
		super(pName, pUserData);
	}
	public WebTreeNode(String pName, String pId){
		super(pName);		
		if( pId == null ){
			throw new NullPointerException("节点ID不能为空");
		}
		this.id = pId;
	}
	
	public WebTreeNode(String pName, String pId, Object pUserData) {
		super(pName, pUserData);
		if( pId == null ){
			throw new NullPointerException("节点ID不能为空");
		}
		this.id = pId; 
	}
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	
	/** 
	  * @Fields state : 节点状态 
	  */ 
	private String state = "closed";
	/**
	 * 获取节点图标，
	 */
	private String icon = "";
		
	/**
	 * 获取节点打开时的图标
	 */
	private String openIcon = "";
		
    /**
	 * 获取单击节点时的动作,可以是javascript,也可以是超级连接 如: javascript:orgClick(); /tg/a.jsp
	 */	
	private String action = "";
	
	private String target = "";
	
	/**
	 * 是否允许拖出
	 */
	private boolean dragable = false;
	
	/**
	 * 是否允许拖入
	 */
	private boolean dropable = false;
	
	/**
	 * 节点的提示信息
	 */
	private String tip = "";
	
	/**
	 * 节点的扩展属性
	 */
	private LinkedHashMap userAttributes = new LinkedHashMap();
	
	/**
	 * 单选按纽
	 */
	public static final String RADIO = "radio";
	
	/**
	 * 复选按纽
	 */
	public static final String CHECKBOX = "checkbox";
	/**
	 * none,节点旁边没有任何按纽.
	 */
	public static final String NONE = "none";

	/**
	 * 节点属性.目前只有3种属性radio,checkbox和none
	 * 这个属性需特别说明：一个树的最终形态是由TreeBuilder来决定的。
	 * 所以如果采用CheckXTreeBuilder或RadioXTreeBuilder构造树时
	 * ，该属性是没有用的。只有当一棵树中既有check节点又有radio节点或
	 * 普通节点时，该属性才有效.
	 * 
	 */
	private String nodeProperty = NONE;
	
	/**
	 * 是否被选种
	 */
	private boolean selected = false;
	/**
	 * 是否被禁用
	 */
	private boolean disabled = false;
	
	/**
	 * 选种时的值 
	 */
	private String value = "";
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(String openIcon) {
		this.openIcon = openIcon;
	}
	

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getNodeProperty() {
		return nodeProperty;
	}

	public void setNodeProperty(String nodeProperty) {
		this.nodeProperty = nodeProperty;
	}
	
	public void setAttribute(String pKey, String pValue){
		userAttributes.put(pKey, pValue);	
	}
	
	public String getAttribute(String pKey){
		return (String)userAttributes.get(pKey);
	}
	
	public Map getUserAttributes(){
		return new LinkedHashMap( this.userAttributes );
	}
	
	public boolean equals(Object obj) {
		if ( obj instanceof WebTreeNode == false ){
			return false;
		}
		WebTreeNode objNode = (WebTreeNode)obj;
		if ( this.id == null){
		   return super.equals(obj);
		} else {
			return this.id.equals(objNode.id);
		}
	}

	public int hashCode() {
       if ( this.id == null ){
		 return super.hashCode();
       }else{
    	 return this.id.hashCode();  
       }
	}


	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean isDragable() {
		return dragable;
	}

	public void setDragable(boolean dragable) {
		this.dragable = dragable;
	}

	public boolean isDropable() {
		return dropable;
	}

	public void setDropable(boolean dropable) {
		this.dropable = dropable;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("name=").append(this.getName());
		sb.append("id=").append(this.id);
		return sb.toString();
	}

}