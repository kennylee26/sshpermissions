/**   
  * @Title: TreeModel.java 
  * @Package com.tgyt.tree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:57:16 
  * @version V1.0   
  */

package com.tgyt.tree;

import java.util.Iterator;

/** 
 * @ClassName: TreeModel 
 * @Description: 树，森林
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:57:16 
 *  
 */
public interface TreeModel {
	/**
	 * 获取跟节点,可以是多个跟节点.
	 * @return
	 */
	public Iterator getRootNodes();
}
