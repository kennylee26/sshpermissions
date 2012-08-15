/**   
 * @Title: TreeBuilderSupport.java 
 * @Package com.tgyt.tree.support 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午04:09:31 
 * @version V1.0   
 */

package com.tgyt.tree.support;

import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.TreeBuilder;

/**
 * @ClassName: TreeBuilderSupport
 * @Description: treebuilder帮助类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:09:31
 * 
 */
public abstract class TreeBuilderSupport implements TreeBuilder {

	// 换行符
	protected static final String ENTER = "\r\n";
	
	//空格
	protected static final String BLANK = "&nbsp;";

	public void buildNodeEnd(Node pNode, Node pParentNode, int pLevel, int pRow)
			throws BuildTreeException {
	}

	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow)
			throws BuildTreeException {
	}

	public void buildTreeEnd() throws BuildTreeException {
	}

	public void buildTreeStart() throws BuildTreeException {

	}

}