/**   
  * @Title: NodeUtils.java 
  * @Package com.tgyt.tree.taglib 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:24:38 
  * @version V1.0   
  */

package com.tgyt.tree.taglib;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tgyt.tree.Node;
import com.tgyt.tree.TreeModel;
import com.tgyt.tree.support.DefaultTreeModel;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: NodeUtils 
 * @Description: 结点处理
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:24:38 
 *  
 */
public abstract class NodeUtils {
	  private NodeUtils(){
		  
	  }
	  
	  /**
	   * 将pNodes列表转换成TreeModel对象,注意pNodes里的元素是
	   * WebTreeNode或者子元素.这些节点
	   * @param pNodes WebTreeNode 列表
	   * @param pNodeMap key是id, value是parentId;
	   * @return
	   */
	  public static TreeModel convert(final List pNodes,final Map pNodeMap){
			DefaultTreeModel result = new DefaultTreeModel();
			if ( pNodes == null ){
				return result;
			}
			if ( pNodes.isEmpty() ){
				return result;
			}
			Map nodes = new LinkedHashMap();
			for(int i=0; i<pNodes.size(); i++){
				WebTreeNode node = (WebTreeNode)pNodes.get(i);
				nodes.put(node.getId(), node);
			}
			for(int i=0; i<pNodes.size(); i++){
				WebTreeNode node = (WebTreeNode)pNodes.get(i);
				final String id = node.getId();
				final String parentId = (String)pNodeMap.get(id);
				Node parentNode = (Node)nodes.get(parentId);
				if (parentNode == null) {//跟节点
					result.addRootNode(node);
					continue;
				}
				node.setParent(parentNode);
			}
		    return result;
	  }
	}
