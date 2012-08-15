/**   
 * @Title: NodeVisitor.java 
 * @Package com.tgyt.tree 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午03:51:53 
 * @version V1.0   
 */

package com.tgyt.tree;

/**
 * @ClassName: NodeVisitor
 * @Description: 
 * 在使用TreeBuilder构造节点前进行访问.可以通过NodeVisitor
 * 设置Node的属性，过滤节点.当访问一个节点返回false时，
 * 该节点和他所有儿子节点不会传递个TreeBuilder
 * 节点访问.
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:51:53
 * 
 */
public interface NodeVisitor {
	public boolean visit(Node pNode);
}
