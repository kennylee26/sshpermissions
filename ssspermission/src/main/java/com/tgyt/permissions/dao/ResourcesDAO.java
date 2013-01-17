/**   
  * @Title: ResourcesDAO.java 
  * @Package com.tgyt.permissions.dao 
  * @Description: 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-20 下午1:25:44 
  * @version V1.0   
  */

package com.tgyt.permissions.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.framework.dao.hspring.handler.IHandler;
import com.tgyt.permissions.biz.IResourcesService;
import com.tgyt.permissions.biz.ISystemService;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Systems;
import com.tgyt.tree.Node;
import com.tgyt.tree.TreeDirector;
import com.tgyt.tree.TreeModel;
import com.tgyt.tree.UncodeException;
import com.tgyt.tree.UserDataUncoder;
import com.tgyt.tree.easyui.EasyuiTreeBuilder;
import com.tgyt.tree.support.AbstractWebTreeModelCreator;
import com.tgyt.tree.support.DefaultNodeComparator;
import com.tgyt.tree.support.DefaultTreeDirector;
import com.tgyt.tree.support.DefaultTreeModel;
import com.tgyt.tree.support.ReverseComparator;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: ResourcesDAO 
 * @Description: 处理资源信息模块的DAO 
 * @author sunct sunchaotong18@163.com 
 * @date 2011-9-20 下午1:25:44 
 *  
 */
@Repository(value="resourcesDAO")
public class ResourcesDAO extends BaseDAO<Resources> {

	/** 
	  * @Title: getMaxOrder 
	  * @Description:获取资源信息表中的排序最大值 
	  * @param @return
	  * @return int
	  * @throws 
	  */
	public int getMaxOrder(){
		IHandler handler = this.getHandler();
		Object result = handler.findObj("select max(orderid) from Resources ");
		if(result!=null){
			return (Integer)result;
		}else{
			return 0;
		}
		
	}
	
	
	
	/** 
	  * @Title: getTree 
	  * @Description: 获得单个系统的树HTML代码 
	  * @param @param request
	  * @param @param system
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String getTree(HttpServletRequest request,Systems system){
		// 业务数据
				List orgs = this.findList("from Resources r where r.system.id="+system.getId());
				// 业务数据解码器，从业务数据中分解出id和parentid
				UserDataUncoder orgUncoder = new UserDataUncoder() {
					public Object getID(Object pUserData) throws UncodeException {
						Resources org = (Resources) pUserData;
						return org.getId();
					}

					public Object getParentID(Object pUserData) throws UncodeException {
						Resources org = (Resources) pUserData;
						if(org.getParent()!=null){
							return org.getParent().getId();
						}else{
							return null;
						}
					}
				};

				// Tree模型构造器，用于生成树模型
				AbstractWebTreeModelCreator treeModelCreator = new AbstractWebTreeModelCreator() {
					// 该方法负责将业务数据映射到树型节点
					protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
						Resources org = (Resources) pUserData;
						WebTreeNode result = new WebTreeNode(org.getName(), IResourcesService.TREE_NAME+org.getId().toString());
						result.setAttribute("orderid", org.getOrderid().toString());
						return result;
					}
				};
				treeModelCreator.init(request);

				TreeModel tempModel = treeModelCreator.create(orgs, orgUncoder);
				WebTreeNode virtualRootNode = new WebTreeNode(system.getName(), ISystemService.TREE_NAME+system.getId());
				java.util.Iterator rootNodes = tempModel.getRootNodes();
				while (rootNodes.hasNext()) {
					Node rootNode = (Node) rootNodes.next();
					rootNode.setParent(virtualRootNode);
				}
				DefaultTreeModel treeModel = new DefaultTreeModel();
				treeModel.addRootNode(virtualRootNode);

				TreeDirector director = new DefaultTreeDirector();// 构造树导向器
				director.setComparator(new ReverseComparator(
						new DefaultNodeComparator()));
				EasyuiTreeBuilder treeBuilder = new EasyuiTreeBuilder();// 构造树Builder
				treeBuilder.setTreeID("tree"+system.getId());//设置当前树id
				treeBuilder.init(request);
//				treeBuilder.setImportCss(true);//设置生成树时导入依赖的CSS
//				treeBuilder.setImportJs(true);//设置生成树时导入依赖的JS
				director.build(treeModel, treeBuilder);// 执行构造
				String treeScript = treeBuilder.getTreeScript();// 获取构造树的脚本
				return treeScript;
	}
	
	/** 
	  * @Title: getAll 
	  * @Description: 获得所有资源记录集合
	  * @param @return
	  * @return List<Resources>
	  * @throws 
	  */
	public List<Resources> getAll(){
		return this.findList("from Resources");
	}
}
