/**   
  * @Title: IResourcesService.java 
  * @Package com.tgyt.permissions.biz 
  * @Description: 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-20 下午1:27:47 
  * @version V1.0   
  */

package com.tgyt.permissions.biz;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.permissions.model.CloneResources;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.Systems;

/** 
 * @ClassName: IResourcesService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunct sunchaotong18@163.com 
 * @date 2011-9-20 下午1:27:47 
 *  
 */
public interface IResourcesService extends ServiceInterface<Resources> {

	/** 
	  * @Fields TREE_NAME :在构造树的时候用于标识当前节点的类型 
	  */ 
	String TREE_NAME = "RESOURCE";
	/** 
	  * @Title: getResourcesList 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param @param resources
	  * @param @param treeId
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @param sort 排序字段
	  * @param @param order 排序顺序
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getResourcesList(CloneResources resources,String treeId, int pageNo, int pageSize,String sort,String order);
	
	/** 
	  * @Title: getMaxOrder 
	  * @Description: 获得最大排序数 
	  * @param @return
	  * @return int
	  * @throws 
	  */
	public int getMaxOrder();
	
	/** 
	  * @Title: getTreeString 
	  * @Description: 获得所有系统的所有资源树的HTML代码 
	  * @param @param request
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String getAllHTMLTrees(HttpServletRequest request);
	
	/** 
	  * @Title: getAllTrees 
	  * @Description: 返回所有资源树的集合
	  * @param @return
	  * @return List<Resources>
	  * @throws 
	  */
	public List<Map<String,Object>> getAllListTrees();
	/** 
	  * @Title: getTree 
	  * @Description: 获得指定系统的资源树HTML代码 
	  * @param @param request
	  * @param @param system
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String getTree(HttpServletRequest request,Systems system);
	
	/** 
	  * @Title: getTree 
	  * @Description: 获得指定系统的资源集合,不包含指定ID资源以及其子资源 
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getTree(Systems system,Integer id);
	
	/** 
	  * @Title: convert 
	  * @Description:将Resources类型转换成CloneResources 
	  * @param @param resources
	  * @param @return
	  * @param @throws Exception
	  * @return List<CloneResources>
	  * @throws 
	  */
	public List<CloneResources> convert(List<Resources> resources) throws Exception;
	
	/** 
	  * @Title: convert 
	  * @Description: 将CLoneResources类型转换成Resources
	  * @param @param resource
	  * @param @return
	  * @param @throws Exception
	  * @return Resources
	  * @throws 
	  */
	public Resources convert(CloneResources resource) throws Exception;
	
	/** 
	  * @Title: deleteSubResources 
	  * @Description: 根据传入的treeId，删除该节点以及所有子节点 
	  * @param @param treeId
	  * @return void
	  * @throws 
	  */
	public void deleteSubResources(String treeId);
	
	
	/** 
	  * @Title: deleteSubResources 
	  * @Description: 根据资源的id,删除该节点以及所有子节点 ,还有角色资源表中相应资源的记录
	  * @param @param id
	  * @return void
	  * @throws 
	  */
	public void deleteSubResources(Integer id);
	
	/** 
	  * @Title: getRAMappings 
	  * @Description: 获得单个系统的资源操作对应集合 ，用于给指定系统分配操作时使用 
	  * @param @param system
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getRAMappings(Systems system);
	
	/** 
	  * @Title: getAllRAMappings 
	  * @Description: 获得所有系统的资源操作对应集合，用于给所有资源分配操作时使用 
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getAllRAMappings();
	/** 
	  * @Title: getRoleRAMappings 
	  * @Description: 获得指定角色的指定系统的资源操作集合，用于给指定角色的资源分配操作时使用 
	  * @param @param resources
	  * @param @param system
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getRoleRAMappings(Integer roleId,Set<Resources> resources,Systems system);
	/** 
	  * @Title: getRoleRAMappings 
	  * @Description: 获得指定角色的所有资源操作对应的集合，用于给指定角色的资源分配操作时使用 
	  * @param @param resources 角色所拥有的资源集合
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getRoleRAMappings(Integer roleId,Set<Resources> resources);
	
	/**
	 * 
	  * @Title: getRoleResActMappings 
	  * @Description: 获得指定角色的资源操作集合
	  * @param @param role
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws
	 */
	public List<Map<String, Object>> getRoleResActMappings(Role role);
	/** 
	  * @Title: getAll 
	  * @Description: 获得所有资源信息记录集合
	  * @param @return
	  * @return List<Resources>
	  * @throws 
	  */
	public List<Resources> getAll();
}
