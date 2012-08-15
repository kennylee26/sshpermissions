/**   
  * @Title: DefaultTreeModel.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:47:13 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tgyt.tree.Node;
import com.tgyt.tree.TreeModel;

/** 
 * @ClassName: DefaultTreeModel 
 * @Description: 默认树模型
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:47:13 
 *  
 */
public class DefaultTreeModel implements TreeModel{
    
    /**
     * 用户存储树的跟节点
     */
    private List rootNodes = new ArrayList();
    
    public DefaultTreeModel(){
    	
    }
    
    public DefaultTreeModel(Node pRootNode){
    	addRootNode(pRootNode);
    }
    
	public Iterator getRootNodes() {
		return rootNodes.iterator();
	}
	
	public void addRootNode(Node pRootNode){
		rootNodes.add(pRootNode);
	}

	public int getRootNodeCount(){
		return this.rootNodes.size();
	}
	public Node getRootNodeAt(int pRootIndex){
	  return (Node)rootNodes.get(pRootIndex);	
	}
	 public int getRootNodeIndex(Node node){
		return this.rootNodes.indexOf(node); 
	 }
	public boolean removeRoot(Node pRootNode){
		return rootNodes.remove(pRootNode);
	}
	
}
