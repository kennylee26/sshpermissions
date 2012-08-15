/**   
  * @Title: DefaultNodeComparator.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:49:46 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import com.tgyt.tree.Node;

/** 
 * @ClassName: DefaultNodeComparator 
 * @Description: 采用节点文本进行排序
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:49:46 
 *  
 */
public class DefaultNodeComparator extends AbstractNodeComparator {

	protected Comparable getComparableProperty(Node pNode) {
		if ( pNode instanceof DefaultNode == false){
			throw new IllegalArgumentException("node type is error, should be WebNode!");
		}
		DefaultNode defaultNode = (DefaultNode)pNode;
		return defaultNode.getName();
	}

}