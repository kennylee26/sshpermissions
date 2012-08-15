/**   
 * @Title: TreeBuilder.java 
 * @Package com.tgyt.tree 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午03:55:06 
 * @version V1.0   
 */

package com.tgyt.tree;

/**
 * @ClassName: TreeBuilder
 * @Description: builder构造树
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:55:06
 * 
 */
public interface TreeBuilder {

	/**
	 * 开始构造树
	 * 
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException;

	/**
	 * 结束构造树
	 * 
	 * @throws BuildTreeException
	 */
	public void buildTreeEnd() throws BuildTreeException;

	/**
	 * 开始构造普通节点（除跟节点之外的节点)
	 * 
	 * @param pNode
	 *            当前节点
	 * @param pParentNode
	 *            父亲节点
	 * @param pLevel
	 *            节点级别，根节点为0级，根节点直接儿子节点为1级，依次类推，2,3,....
	 * @param pRow
	 *            在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4....
	 * @throws BuildTreeException
	 */
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel,
			int pRow) throws BuildTreeException;

	/**
	 * 结束构造普通节点（除跟节点之外的节点)
	 * 
	 * @param pNode
	 *            当前节点
	 * @param pParentNode
	 *            父亲节点
	 * @param pLevel
	 *            节点级别，根节点为0级，根节点直接儿子节点为1级，依次类推，2,3,....
	 * @param pRow
	 *            在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4....
	 * @throws BuildTreeException
	 */
	public void buildNodeEnd(Node pNode, Node pParentNode, int pLevel, int pRow)
			throws BuildTreeException;

	/**
	 * 开始构造跟节点
	 * 
	 * @param pRootNode
	 *            跟节点，非空
	 * @param pLevel
	 *            根节点级别
	 * @param pRow
	 *            在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4....
	 * @throws BuildTreeException
	 */
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow)
			throws BuildTreeException;

	/**
	 * 结束构造跟节点
	 * 
	 * @param pRootNode
	 *            跟节点，非空
	 * @param pLevel
	 *            根节点级别
	 * @param pRow
	 *            在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4....
	 * @throws BuildTreeException
	 */
	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow)
			throws BuildTreeException;

}
