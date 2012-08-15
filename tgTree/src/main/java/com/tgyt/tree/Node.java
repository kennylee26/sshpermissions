/**   
 * @Title: Node.java 
 * @Package com.tgyt.tree 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午03:50:51 
 * @version V1.0   
 */

package com.tgyt.tree;

import java.util.Iterator;

/**
 * @ClassName: Node
 * @Description: 树结点
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:50:51
 * 
 */
public interface Node {
	
	public Node getParent();

	public void setParent(Node pParent);

	public void setUserData(Object pUserData);

	public Object getUserData();

	public Iterator getChildren();

	public void detachNode(Node pNode);

	public void addNode(Node pNode);

	public boolean isLeaf();

	public boolean isRoot();

	public boolean isFirst();

	public boolean isLast();

	public int getChildCount();

	public Node getChildAt(int pChildIndex);

	public int getIndex(Node node);
	
}
