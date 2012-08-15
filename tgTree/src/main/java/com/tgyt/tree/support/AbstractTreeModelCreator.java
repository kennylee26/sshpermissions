/**   
  * @Title: AbstractTreeModelCreator.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:45:36 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.tree.CreateTreeModelException;
import com.tgyt.tree.Node;
import com.tgyt.tree.TreeModel;
import com.tgyt.tree.TreeModelCreator;
import com.tgyt.tree.Uncodable;
import com.tgyt.tree.UncodeException;
import com.tgyt.tree.UserDataUncoder;

/** 
 * @ClassName: AbstractTreeModelCreator 
 * @Description: 抽象树模型构造器
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:45:36 
 *  
 */
public abstract class AbstractTreeModelCreator implements TreeModelCreator {

	private boolean allowMutiRoot = true;
	private final Log log = LogFactory.getLog(getClass());

	public boolean isAllowMutiRoot() {
		return allowMutiRoot;
	} 

	public void setAllowMutiRoot(boolean allowMutiRoot) {
		this.allowMutiRoot = allowMutiRoot;
	}
	
	   public TreeModel create(Collection pUserDatas) throws CreateTreeModelException{
		   return create(pUserDatas, new UserDataUncoder(){
			public Object getID(Object pUserData) throws UncodeException {
				Uncodable uncode = (Uncodable)pUserData;
				return uncode.getID();
			}
			public Object getParentID(Object pUserData) throws UncodeException {
				Uncodable uncode = (Uncodable)pUserData;
				return uncode.getParentID();
			}
			   
		   });
	   }
	public TreeModel create(Collection pUserDatas, UserDataUncoder pUncoder)
			throws CreateTreeModelException {
		if (pUserDatas == null) {
			return new DefaultTreeModel();
		}
		if ( pUserDatas.isEmpty() ){
			return new DefaultTreeModel();
		}
		if (pUncoder == null) {
			throw new CreateTreeModelException("节点解码器不能为空null");
		}
		DefaultTreeModel result = new DefaultTreeModel();
		Map nodes = new LinkedHashMap();
		Iterator userDatasIterator = pUserDatas.iterator();
		while (userDatasIterator.hasNext()) {
			Object userData = userDatasIterator.next();
			Object id = null;
			try {
				id = pUncoder.getID(userData);
			} catch (UncodeException ex) {
				throw new CreateTreeModelException(ex.getMessage(), ex);
			}
			if ( id == null ){
				throw new CreateTreeModelException("获取用户ID失败，用户对象:" + userData);
			}
			Node node = null;
			try {
				node = createNode(userData, pUncoder);
			} catch (Exception ex) {
				throw new CreateTreeModelException(ex.getMessage(), ex);
			}
			if ( node == null ){
				log.warn("创建节点失败，用户对象:" + userData);
				continue;
			}
			node.setUserData(userData);
			nodes.put(id, node);//将节点cache起来
		}

		Iterator nodeIterator = nodes.values().iterator();
		while (nodeIterator.hasNext()) {
			Node node = (Node) nodeIterator.next();
			Object userData = node.getUserData();
			Object parentId = null;
			try {
				parentId = pUncoder.getParentID(userData);
			} catch (UncodeException ex) {
				throw new CreateTreeModelException(ex.getMessage(), ex);
			}
			Node parentNode = (Node) nodes.get(parentId);
			if (parentNode == null) {//跟节点
				result.addRootNode(node);
				continue;
			}
			node.setParent(parentNode);
		}
		
		if (result.getRootNodeCount() == 0) {
			throw new CreateTreeModelException("不存在跟节点");
		}
		
		if ( allowMutiRoot == false ){
			if ( result.getRootNodeCount() > 1 ){
				throw new MultiRootNodeException();
			}
		}
		 return result;
	}
 
	protected abstract Node createNode(Object pUserData, UserDataUncoder pUncoder);


}