/**   
 * @Title: TreeDirector.java 
 * @Package com.tgyt.tree 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午03:56:05 
 * @version V1.0   
 */

package com.tgyt.tree;

import java.util.Comparator;

/**
 * @ClassName: TreeDirector
 * @Description: tree指挥者
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:56:05
 * 
 */
public interface TreeDirector {

	/**
	 * 设置节点比较器
	 * 
	 * @param pComparator
	 *            节点比较器，用于进行兄弟节点比较
	 */
	public void setComparator(Comparator pComparator);

	/**
	 * 节点访问者
	 * 
	 * @param pVisitor
	 */
	public void setNodeVisitor(NodeVisitor pVisitor);

	/**
	 * build树
	 * 
	 * @param pTree
	 * @param pTreeBuilder
	 *            Tree构造器（非空)
	 * @throws BuildTreeException
	 */
	public void build(TreeModel pTree, TreeBuilder pTreeBuilder)
			throws BuildTreeException;
}
