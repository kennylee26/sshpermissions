/**   
  * @Title: TreeModelCreator.java 
  * @Package com.tgyt.tree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:57:53 
  * @version V1.0   
  */

package com.tgyt.tree;

import java.util.Collection;

/** 
 * @ClassName: TreeModelCreator 
 * @Description: 树模型构建
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:57:53 
 *  
 */
public interface TreeModelCreator {

	/**
	 * 创建树模型
	 * @param pUserDatas 业务数据,至少要存在一个跟节点（不存在父亲节点的节点)
	 *                   要求集合元素读必须实现Uncodable接口                     
	 * @return 返回根节点.
	 * @throws CreateTreeModelException 如果集合元素没有实现Uncodable接口，会抛出
	 *                                  ClassCastException异常
	 */
   public TreeModel create(Collection pUserDatas) throws CreateTreeModelException;
	
	/**
	 * 创建树模型 
	 * @param pUserDatas 业务数据,至少要存在一个跟节点（不存在父亲节点的节点)
	 * @param pUncoder   解码器，对每个业务数据进行解码，返回主键对象和父亲主键对象.
	 * @return 返回根节点.
	 * @throws CreateTreeModelException
	 */
  public TreeModel create(Collection pUserDatas, UserDataUncoder pUncoder) throws CreateTreeModelException;
}
