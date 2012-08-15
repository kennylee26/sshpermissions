/**   
  * @Title: NodeTag.java 
  * @Package com.tgyt.tree.taglib 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:23:44 
  * @version V1.0   
  */

package com.tgyt.tree.taglib;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.tree.support.WebTreeDynamicNode;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: NodeTag 
 * @Description: 节点标签
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:23:44 
 *  
 */
public class NodeTag extends BodyTagSupport implements DynamicAttributes {
	
	public int doStartTag() throws JspException {
		return NodeTag.EVAL_BODY_INCLUDE;
	}


	private final Log logger = LogFactory.getLog( this.getClass() );
	private static final long serialVersionUID = 1L;
	
    //注意：该ID要是合法标识符号,因为TreeBuilder可能使用该ID作为js变量 
	private String id = "";
	private String parentId = "";

	/**
	 * 动态属性
	 */
	private java.util.LinkedHashMap dynamicAttributes = new java.util.LinkedHashMap();
	
	//负责导入子树的URL
	private String subTreeURL = "";
	
	/**
	 * 节点类型,现在内置default,dynamic 2种,
	 */
	private String cls = "default";
	/**
	 * 节点名称
	 */
	private String name = null;
	/**
	 * 获取节点图标，
	 */
	private String icon = "";
		
	/**
	 * 获取节点打开时的图标
	 */
	private String openIcon = "";
		
    /**
	 * 获取单击节点时的动作,可以是javascript,也可以是超级连接 如: javascript:orgClick(); /e3/a.jsp
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
	
	private Map userAttributes = new HashMap();
	
	public void setAttribute(String pKey, String pValue){
		userAttributes.put(pKey, pValue);
	}
	
	public void setProperty(Object pObj, String pProperty, Object pValue) throws JspException{
		try {
			BeanUtils.setProperty(pObj, pProperty, pValue);
		} catch (Exception ex){
			final String msg =
				"设置节点：" + pObj.getClass().getName() + "的属性:" + pProperty + "失败！" +
				"属性值为:" + pValue;
			logger.error(msg, ex);
			throw new JspException(msg, ex);
		}
		
	}
	

	public int doEndTag() throws JspException {
		WebTreeNode webNode = NodeFactory.getInstance(this.cls);
		webNode.setId(this.id);
		webNode.setTarget(this.target);
		webNode.setIcon(this.icon);
		webNode.setName(this.name);
		webNode.setOpenIcon(this.openIcon);
		webNode.setAction(this.action);
		webNode.setTarget(this.target);
		webNode.setDragable(this.dragable);
		webNode.setDropable(this.dropable);
		webNode.setTip(this.tip);
		webNode.setNodeProperty(this.nodeProperty);
		webNode.setSelected(this.selected);
		webNode.setDisabled(this.disabled);
		webNode.setValue(this.value);
		java.util.Iterator keys = dynamicAttributes.keySet().iterator();
		//设置动态属性
		while( keys.hasNext() ){
			Object key = keys.next();
			Object value = dynamicAttributes.get(key);
			this.setProperty(webNode, (String)key, value);
		}
		if ( webNode instanceof WebTreeDynamicNode ){
			((WebTreeDynamicNode)webNode).setSubTreeURL(this.subTreeURL);
		}
		
		//设置用户属性
		java.util.Iterator userAttrs = userAttributes.keySet().iterator();
		while( userAttrs.hasNext() ){
			Object key = userAttrs.next();
			Object value = userAttributes.get(key);
			webNode.setAttribute((String)key, (String)value);
		}
		userAttributes.clear();
		
		TreeTag treeTag = (TreeTag) findAncestorWithClass(this, TreeTag.class);
		webNode.setUserData( treeTag.getCurrUserData() );//设置节点业务数据
		treeTag.addNode(webNode);
		treeTag.addIdParentIds(this.id, this.parentId);
		return super.doEndTag();
	}
	public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
		dynamicAttributes.put(name, value);
	}
	public java.util.LinkedHashMap getDynamicAttributes() {
		return dynamicAttributes;
	}
	public void setDynamicAttributes(java.util.LinkedHashMap dynamicAttributes) {
		this.dynamicAttributes = dynamicAttributes;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getNodeProperty() {
		return nodeProperty;
	}
	public void setNodeProperty(String nodeProperty) {
		this.nodeProperty = nodeProperty;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	public String getSubTreeURL() {
		return subTreeURL;
	}


	public void setSubTreeURL(String subTreeURL) {
		this.subTreeURL = subTreeURL;
	}


	public String getId() {
		return id;
	}


	/**
	 * 给节点ID添加前缀，这样id就可以以数字开头
	 */
	private final String NODE_PREFIX = "e3";
	
	public void setId(String id) {		
		this.id = NODE_PREFIX + id;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		if ( parentId != null ){
		  this.parentId = NODE_PREFIX + parentId;
		} else {
			this.parentId = null;
		}
	}


	public void release() {
		this.cls = "default";
		this.id = "";
		this.parentId = "";
		this.target = "";
		this.icon = "";
		this.openIcon = "";
		this.name = null;
		this.action = "";
		this.selected = false;
		this.disabled = false;
		this.dragable = false;
		this.dropable = false;
		this.tip = "";
		this.subTreeURL = "";
		this.nodeProperty = NONE;
		this.value = "";
		this.dynamicAttributes.clear();
		this.dynamicAttributes = null;
		super.release();
	}


	public String getCls() {
		return cls;
	}


	public void setCls(String cls) {
		this.cls = cls;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
