/**   
  * @Title: EmptyNodeVisitor.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:51:27 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import com.tgyt.tree.Node;
import com.tgyt.tree.NodeVisitor;

/** 
 * @ClassName: EmptyNodeVisitor 
 * @Description: empty node visitor
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:51:27 
 *  
 */
public class EmptyNodeVisitor implements NodeVisitor{

	public boolean visit(Node pNode) {
		return true;
	}

}
