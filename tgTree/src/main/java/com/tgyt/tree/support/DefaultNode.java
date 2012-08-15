/**   
  * @Title: DefaultNode.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:25:51 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tgyt.tree.Node;

/** 
 * @ClassName: DefaultNode 
 * @Description: 默认结点
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:25:51 
 *  
 */
public class DefaultNode implements Node, Serializable{
	private static final long serialVersionUID = 1L;
	private Node parent;      //父亲节点，跟节点的父亲为null
	private Object userData;  //用户数据
	private List children = new ArrayList();
	private String name;

	
	public DefaultNode(){
		name = "no name";
	}
	
	public DefaultNode(String pName){
		if ( pName == null ){
			throw new NullPointerException("节点名称不能为空null");
		}
		name = pName;
	}
	
	public DefaultNode(String pName, Object pUserData){
		this(pName);
		this.userData = pUserData;
	}
	
	public String toString(){
		if ( name == null ){
			return super.toString();	
		} else {
			return name.toString();
		}		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void detachNode(Node pNode){
		if ( pNode == null ){
			return;
		}
		//不包含pNode
		if ( children.contains(pNode) == false ){
			return;
		}
		children.remove(pNode);		
		pNode.setParent(null);

	}
	
	public Object getUserData() {
		return userData;
	}
	public void setUserData(Object userData) {
		this.userData = userData;
	}
	public Node getParent() {
		return parent;
	}
	
	public boolean isRoot(){
		return this.parent == null ? true : false;
	}
	public void setParent(Node pParentNode) {
		if ( pParentNode == this.parent ){
			return;
		}
		if ( this.parent != null ){//解除跟以前父亲菜单的关系.
			this.parent.detachNode(this);
		}
		if (pParentNode != null) {
			int index = pParentNode.getIndex(this);
			if (index == -1) {
				pParentNode.addNode(this);
			}
		}
		this.parent = pParentNode;
	}
	public void addNode(Node pNode) {
		if ( pNode == null ){
			return;
		}
		if (children.contains(pNode) == false) {
			children.add(pNode);
			pNode.setParent(this);
		}
	}
	
	public boolean isHaveChildren(){
		return !this.children.isEmpty();
	}


	public Iterator getChildren() {
		return this.children.iterator();
	}

	public boolean isLeaf() {
		return this.children.size() == 0 ? true : false;
	}

	public int getChildCount() {
		return this.children.size();
	}

	public Node getChildAt(int pChildIndex) {
		return (Node)this.children.get(pChildIndex);
	}

	public int getIndex(Node pChildIndex) {
		return this.children.indexOf(pChildIndex);
	}
	
	 public boolean isFirst(){
		 Node parentNode = this.getParent();
		 if ( parentNode == null ){
			 return true;
		 }
		 int index = parentNode.getIndex(this);
		 if ( index == 0 ){
			 return true;
		 } else {
			 return false;
		 }
	 }
	 public boolean isLast(){
		 Node parentNode = this.getParent();
		 if ( parentNode == null ){
			 return true;
		 }
		 int index = parentNode.getIndex(this);
		 if ( index == (parentNode.getChildCount() - 1) ){
			 return true;
		 } else {
			 return false;
		 }
		 
	 }
	
}