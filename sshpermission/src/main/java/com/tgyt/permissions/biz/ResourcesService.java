/**   
  * @Title: ResourcesService.java 
  * @Package com.tgyt.permissions.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-20 下午1:28:49 
  * @version V1.0   
  */

package com.tgyt.permissions.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.ActionsDAO;
import com.tgyt.permissions.dao.ResourcesDAO;
import com.tgyt.permissions.dao.SystemDao;
import com.tgyt.permissions.model.Actions;
import com.tgyt.permissions.model.CloneResources;
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Systems;
import com.tgyt.tree.easyui.EasyuiTreeBuilder;

/** 
 * @ClassName: ResourcesService 
 * @Description: 资源信息服务类 
 * @author sunct sunchaotong18@163.com 
 * @date 2011-9-20 下午1:28:49 
 *  
 */
@Service
public class ResourcesService extends BaseService<Resources> implements
		IResourcesService {

	/** 
	  * @Fields resourcesDAO : 资源服务DAO 
	  */ 
	@Resource(name="resourcesDAO")
	private ResourcesDAO resourcesDAO;
	
	/** 
	  * @Fields systemDao : 系统操作DAO 
	  */ 
	@Autowired
	private SystemDao systemDao;
	
	/** 
	  * @Fields actionsDAO :操作信息DAO 
	  */ 
	@Resource(name="actionsDAO")
	private ActionsDAO actionsDAO;
	/** 
	  * @Title: getTreeString 
	  * @Description: 获得所有系统的所有资源树的HTML代码 
	  * @param @param request
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String getAllHTMLTrees(HttpServletRequest request){
		StringBuffer trees = new StringBuffer("<ul id=\""+EasyuiTreeBuilder.DEFAULT_TREE_ID+"\" class=\"easyui-tree\" animate=\"false\">");
		try {
			trees.append("<li state=\"opened\" id=\"TREES\">");
			trees.append("<span>资源系统</span>");
			List<Systems> systems = systemDao.findList("from Systems");
			for(Iterator<Systems> iter=systems.iterator();iter.hasNext();){
				String tree = this.resourcesDAO.getTree(request,iter.next());
				trees.append(tree);
			}
			trees.append("</li>");
			trees.append("</ul>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trees.toString();
	}
	
	public Pagination getResourcesList(CloneResources resources,String treeId, int pageNo, int pageSize,String sort,String order) {
		StringBuffer hql = new StringBuffer("from Resources where 1=1 ");
		if(resources!=null){
			String parentId = resources.getParentId();
			Integer systemId = resources.getSystemId();
			String name = resources.getName();
			String enname = resources.getEnname();
			String resourcetype = resources.getResourcetype();
			String link = resources.getLink();
			String icon = resources.getIcon();
			String iconopen = resources.getIconopen();
			String isopen = resources.getIsopen();
			String isleaf = resources.getIsleaf();
			String status = resources.getStatus();
			Integer orderid = resources.getOrderid();
			String memo = resources.getMemo();
			if(parentId!=null && !"".equals(parentId)){
				hql.append(" and parent.id="+parentId);
			}
			if(systemId!=null){
				hql.append(" and system.id="+systemId);
			}
			if(name!=null && !"".equals(name)){
				hql.append(" and name like '%"+name+"%'");
			}
			if(enname!=null && !"".equals(enname)){
				hql.append(" and enname like '%"+enname+"%'");
			}
			if(resourcetype!=null && !"".equals(resourcetype)){
				hql.append(" and resourcetype like '%"+resourcetype+"%'");
			}
			if(link!=null && !"".equals(link)){
				hql.append(" and link like '%"+link+"%'");
			}
			if(icon!=null && !"".equals(icon)){
				hql.append(" and icon like '%"+icon+"%'");
			}
			if(iconopen!=null && !"".equals(iconopen)){
				hql.append(" and iconopen='"+iconopen+"'");
			}
			if(isleaf!=null && !"".equals(isleaf)){
				hql.append(" and isleaf='"+isleaf+"'");
			}
			if(isopen!=null && !"".equals(isopen)){
				hql.append(" and isopen'"+isopen+"'");
			}
			if(status!=null && !"".equals(status)){
				hql.append(" and status='"+status+"'");
			}
			if(orderid!=null){
				hql.append(" and orderid="+orderid);
			}
			if(memo!=null && !"".equals(memo)){
				hql.append(" and memo like '%"+memo+"%'");
			}
			
		}
		if(treeId!=null){
			if(treeId.indexOf(IResourcesService.TREE_NAME)!=-1){
				hql.append(" and parent.id="+treeId.substring(IResourcesService.TREE_NAME.length()));
				hql.append(" or id="+treeId.substring(IResourcesService.TREE_NAME.length()));
			}
			if(treeId.indexOf(ISystemService.TREE_NAME)!=-1){
				hql.append(" and system.id="+treeId.substring(ISystemService.TREE_NAME.length()));
			}
		}
		if(sort!=null && !"".equals(sort)){
			hql.append(" order by "+sort);
			if(order!=null && !"".equals(order)){
				hql.append(" "+order);
			}else{
				hql.append(" desc");
			}
		}
		return this.resourcesDAO.getHandler().getPageList(new Finder(hql.toString()), pageNo, pageSize);
	}

	
	public List<CloneResources> convert(List<Resources> resources) throws Exception{
		List<CloneResources> clones = new ArrayList<CloneResources>();
		if(resources!=null && resources.size()!=0){
			for(Iterator<Resources> iter=resources.iterator();iter.hasNext();){
				Resources res = iter.next();
				CloneResources c = new CloneResources();
				BeanUtils.copyProperties(c, res);
				if(res.getParent()!=null){
					
					c.setParentId(res.getParent().getId()+"");
					c.setParentName(res.getParent().getName());
				}
				if(res.getSystem()!=null){
					
					c.setSystemId(res.getSystem().getId());
					c.setSystemName(res.getSystem().getName());
				}
				clones.add(c);
			}
		}
		return clones;
	}
	
	public int getMaxOrder() {
		return this.resourcesDAO.getMaxOrder();
	}

	/* (non-Javadoc)
	 * <p>Title: getDAO</p> 
	 * <p>Description: 获得资源DAO</p> 
	 * @return 
	 * @see com.tgyt.framework.service.BaseService#getDAO() 
	 */
	
	protected DAOInterface<Resources> getDAO() {
		return this.resourcesDAO;
	}

	
	public String getTree(HttpServletRequest request,Systems system) {
		return this.resourcesDAO.getTree(request,system);
	}

	public List<Map<String,Object>> getAllRAMappings(){
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", "TREES");
		root.put("text", "系统资源");
		addActions(null,root);
		nodes.add(root);
		
		List<Systems> systems = this.systemDao.findList("from Systems");
		List<Object> all = new ArrayList<Object>();
		for(Iterator<Systems> iter=systems.iterator();iter.hasNext();){
			all.add(this.getRAMappings(iter.next()).get(0));
		}
		
		root.put("children", all);
		return nodes;
	}
	/** 
	  * @Title: addActions 
	  * @Description: 为每个资源动态拼接操作 
	  * @param @param resource
	  * @return void
	  * @throws 
	  */
	private void addActions(Integer roleId,Map<String,Object> resource){
		List<Actions> actions = this.actionsDAO.getAll();
		Object action = null;
		boolean isNum = Pattern.matches("\\d+",resource.get("id").toString());
		if(isNum&&roleId!=null){
			action = this.resourcesDAO.getHandler().findObj("select actions from RoleAuth where roleId="+roleId+" and resourceId="+resource.get("id"));
		}
		if(action==null || "".equals(action.toString())){
			for(Iterator<Actions> ac=actions.iterator();ac.hasNext();){
				Actions next = ac.next();
				//每一个资源对应的操作的name属性格式为:资源ID_操作ENname_操作ID
				resource.put(next.getEnname(),resource.get("id")+":"+ next.getEnname());
			}
		}else{
			for(Iterator<Actions> ac=actions.iterator();ac.hasNext();){
				Actions next = ac.next();
				if(action.toString().indexOf(next.getEnname())!=-1){
					//每一个资源对应的操作的name属性格式为:资源ID_操作ENname_操作ID
					resource.put(next.getEnname(),"_"+resource.get("id")+":"+ next.getEnname());
				}else{
					//每一个资源对应的操作的name属性格式为:资源ID_操作ENname_操作ID
					resource.put(next.getEnname(),resource.get("id")+":"+ next.getEnname());
				}
			}
		}
			
	}
	
	public List<Map<String,Object>> getRAMappings(Systems system) {
		// 存放所有节点每一个节点信息都保存到一个Map<String,Object>中
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		if(system==null){
			return new ArrayList<Map<String,Object>>();
		}
		
		// 获取系统一级节点
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", ISystemService.TREE_NAME+system.getId());
		root.put("text", system.getName());
//		root.put("_parentId", "TREES");
		addActions(null,root);
		nodes.add(root);//将系统节点放入
		
		// 存放每层资源节点
		List<Map<String,Object>> doing = new ArrayList<Map<String,Object>>();
		doing.addAll(nodes);
		
		List<Resources> types ;
		//存放每层资源节点的临时变量
		List<Map<String,Object>> temp = new ArrayList<Map<String,Object>>();
		//一次循环系统节点，获取每个系统的第一层资源节点
		for(Map<String,Object> item: doing){
			//获取当前系统的第一级资源的集合
			types =  this.resourcesDAO.findList("from Resources where parent.id is null and system.id="+system.getId());
			//如果当前系统不存在资源节点，那么执行下一次循环
			if (types.isEmpty()) continue;
			//存放当前系统的第一级资源
			List<Object> children = new ArrayList<Object>();
			for(Resources type: types){
				Map<String,Object> node = new HashMap<String,Object>();
				node.put("id",  type.getId()+"");
				node.put("text", type.getName());
//				node.put("_parentId", root.get("id"));
				addActions(null,node);
				children.add(node);
				//在临时变量中存放当前节点
				temp.add(node);
			}
			//将获取的第一层资源作为系统根节点的孩子
			item.put("children", children.toArray(new Object[children.size()]));
		}
		//在doing中存放当前层的所有资源节点
		doing = temp;
		//循环获取每层资源节点
		while(!doing.isEmpty()){
			//每次循环将临时变量清空，防止doing=temp死循环
			temp = new ArrayList<Map<String,Object>>();
			//循环获取当前层的下一级子节点，同上面的系统节点获取子节点
			for(Map<String,Object> item: doing){
				String pid = (String)item.get("id");
				if(pid.indexOf(ISystemService.TREE_NAME)!=-1){
					pid = pid.substring(ISystemService.TREE_NAME.length());
				}
				types = this.resourcesDAO.findList("from Resources where parent.id="+pid+" and system.id="+system.getId());
				if (types.isEmpty()) continue;
				
				List<Object> children = new ArrayList<Object>();
				for(Resources type: types){
					Map<String,Object> node = new HashMap<String,Object>();
					node.put("id", type.getId()+"");
					node.put("text", type.getName());
//					node.put("_parentId", pid);
					addActions(null,node);
					children.add(node);
					
					temp.add(node);
				}
				item.put("children", children.toArray(new Object[children.size()]));
			}
			//在doing中存放当前层的所有资源节点
			doing = temp;
		}
		return nodes;
	}
	
	public List<Map<String,Object>> getAllListTrees() {
		
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", "TREES");
		root.put("text", "系统资源");
		nodes.add(root);
		
		List<Systems> systems = this.systemDao.findList("from Systems");
		List<Object> all = new ArrayList<Object>();
		for(Iterator<Systems> iter=systems.iterator();iter.hasNext();){
			all.add(this.getTree(iter.next(),null).get(0));
		}
		
		root.put("children", all);
		return nodes;
	}
	
	public List<Map<String,Object>> getTree(Systems system,Integer id) {
		// 存放所有节点每一个节点信息都保存到一个Map<String,Object>中
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		if(system==null){
			return new ArrayList<Map<String,Object>>();
		}
		/*
		 * [{"id":1,"text":"XX公司","children":
		 * [{"id":3,"text":"行政部","state":"closed","children":
		 * [{"id":6,"text":"bbb","state":"open"}]},{"id":2,"text":"财务部","state":"open"},{"id":5,"text":"市场部","state":"open"},{"id":4,"text":"技术部","state":"open"}]}]
		 */
		
		// 获取系统一级节点
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", ISystemService.TREE_NAME+system.getId());
		root.put("text", system.getName());
		nodes.add(root);//将系统节点放入
		
		// 存放每层资源节点
		List<Map<String,Object>> doing = new ArrayList<Map<String,Object>>();
		doing.addAll(nodes);
		
		List<Resources> types ;
		//存放每层资源节点的临时变量
		List<Map<String,Object>> temp = new ArrayList<Map<String,Object>>();
		//一次循环系统节点，获取每个系统的第一层资源节点
		for(Map<String,Object> item: doing){
			//获取当前系统的第一级资源的集合
			types =  this.resourcesDAO.findList("from Resources where parent.id is null and system.id="+system.getId());
			//如果当前系统不存在资源节点，那么执行下一次循环
			if (types.isEmpty()) continue;
			//存放当前系统的第一级资源
			List<Object> children = new ArrayList<Object>();
			for(Resources type: types){
				if(id!=null&&type.getId()==id){
					continue;
				}
				Map<String,Object> node = new HashMap<String,Object>();
				node.put("id",  type.getId()+"");
				node.put("text", type.getName());
				children.add(node);
				//在临时变量中存放当前节点
				temp.add(node);
			}
			//将获取的第一层资源作为系统根节点的孩子
			item.put("children", children.toArray(new Object[children.size()]));
		}
		//在doing中存放当前层的所有资源节点
		doing = temp;
		//循环获取每层资源节点
		while(!doing.isEmpty()){
			//每次循环将临时变量清空，防止doing=temp死循环
			temp = new ArrayList<Map<String,Object>>();
			//循环获取当前层的下一级子节点，同上面的系统节点获取子节点
			for(Map<String,Object> item: doing){
				String pid = (String)item.get("id");
				if(pid.indexOf(ISystemService.TREE_NAME)!=-1){
					pid = pid.substring(ISystemService.TREE_NAME.length());
				}
				types = this.resourcesDAO.findList("from Resources where parent.id="+pid+" and system.id="+system.getId());
				if (types.isEmpty()) continue;
				
				List<Object> children = new ArrayList<Object>();
				for(Resources type: types){
					if(id!=null&&type.getId()==id){
						continue;
					}
					Map<String,Object> node = new HashMap<String,Object>();
					node.put("id", type.getId()+"");
					node.put("text", type.getName());
					children.add(node);
					
					temp.add(node);
				}
				item.put("children", children.toArray(new Object[children.size()]));
			}
			//在doing中存放当前层的所有资源节点
			doing = temp;
		}
		return nodes;
	}

	public Resources convert(CloneResources resource) throws Exception {
		Resources res = new Resources();
		BeanUtils.copyProperties(res,resource);
		String parentId = resource.getParentId();
		
		if(parentId!=null && parentId.indexOf(ISystemService.TREE_NAME)==-1){
			res.setParent(this.resourcesDAO.findById(Integer.parseInt(parentId)));
		}
		res.setSystem(this.systemDao.findById(resource.getSystemId()));
		if(resource.getOrderid()==null){
			res.setOrderid(this.resourcesDAO.getMaxOrder()+1);
		}
		return res;
	}

	
	public void deleteSubResources(Integer id) {
		List<Resources> subs = this.findList(" from Resources where parent.id="+id);
		if(subs!=null && subs.size()>0){
			//this.executeSql(" delete from Resources where parent.id="+id);
			for(Iterator<Resources> iter=subs.iterator();iter.hasNext();){
				this.deleteSubResources(iter.next().getId());
			}
		}
		this.deleteById(id);
		deleteRoleResource(id);
	}
	
	public void deleteSubResources(String treeId) {
		String id;
		if(treeId!=null && treeId.indexOf(ISystemService.TREE_NAME)!=-1){
			id = treeId.substring(ISystemService.TREE_NAME.length());
			this.executeSql("delete from Resources where system.id="+id);
			List<Resources> resources = this.findList("from Resources where system.id"+id);
			for(Iterator<Resources> iter=resources.iterator();iter.hasNext();){
				deleteRoleResource(iter.next().getId());
			}
		}else if(treeId!=null && treeId.indexOf(IResourcesService.TREE_NAME)!=-1){
			id = treeId.substring(IResourcesService.TREE_NAME.length());
			this.deleteSubResources(Integer.parseInt(id));
		}
	}

	/** 
	  * @Title: deleteRoleResource 
	  * @Description: 删除角色权限表中指定资源ID的记录
	  * @param @param resourceId
	  * @return void
	  * @throws 
	  */
	private void deleteRoleResource(Integer resourceId){
		this.executeSql("delete from RoleAuth where resourceId="+resourceId);
	}
	public List<Resources> getAll() {
		return this.resourcesDAO.getAll();
	}

	public List<Map<String, Object>> getRoleRAMappings(Integer roleId,
			Set<Resources> resources, Systems system) {
				// 存放所有节点每一个节点信息都保存到一个Map<String,Object>中
				List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
				if(resources==null || system==null){
					return new ArrayList<Map<String,Object>>();
				}
				
				// 获取系统一级节点
				Map<String,Object> root = new HashMap<String,Object>();
				root.put("id", ISystemService.TREE_NAME+system.getId());
				root.put("text", system.getName());
//				root.put("_parentId", "TREES");
				addActions(roleId,root);
				nodes.add(root);//将系统节点放入
				
				// 存放每层资源节点
				List<Map<String,Object>> doing = new ArrayList<Map<String,Object>>();
				//将系统节点放入，作为第一层节点
				doing.addAll(nodes);
				
				List<Resources> types ;
				//用来存放每层资源节点的临时变量
				List<Map<String,Object>> temp = new ArrayList<Map<String,Object>>();
				//一次循环系统节点，获取每个系统的第一层资源节点
				for(Map<String,Object> item: doing){
					//获取当前系统的第一级资源的集合
					types =  this.resourcesDAO.findList("from Resources where parent.id is null and system.id="+system.getId());
					//循环遍历第一级资源，判断哪些拥有子资源
					for(Iterator<Resources> iter=types.iterator();iter.hasNext();){
						Resources type = iter.next();
						int count = 0;//用来计数，判断当前节点是否有子节点
						for(Iterator<Resources> sub=resources.iterator();sub.hasNext();){
							Resources child = sub.next();
							if(child.getParent()!=null && child.getParent().getId()==type.getId()){
								count++;
							}
						}
						//如果当前节点有子节点，保存到临时变量中
						if(count>0){
							Map<String,Object> oNode = new HashMap<String,Object>();
							oNode.put("id",  type.getId()+"");
							oNode.put("text", type.getName());
							//						node.put("_parentId", root.get("id"));
							addActions(roleId,oNode);
							//在临时变量中存放当前节点
							temp.add(oNode);
						}
					}
					//将获取的第一层资源作为系统根节点的孩子
					item.put("children", temp.toArray(new Object[temp.size()]));
				}
				//在doing中存放当前层的有子节点的所有资源节点
				doing = temp;
				//循环获取每层资源节点
				while(!doing.isEmpty()){
					//每次循环将临时变量清空，防止doing=temp死循环
					temp = new ArrayList<Map<String,Object>>();
					//循环获取当前层的下一级子节点，同上面的系统节点获取子节点
					for(Map<String,Object> item: doing){
						String pid = (String)item.get("id");
						//用来存放当前节点的子节点
						List<Resources> children = new ArrayList<Resources>();
						//依次判断当前节点的子节点
						for(Iterator<Resources> sub=resources.iterator();sub.hasNext();){
							Resources child = sub.next();
							if(child.getParent()!=null && child.getParent().getId()==Integer.parseInt(pid)){
								children.add(child);
								//sub.remove();//每查出一个子资源，就把该资源从resources中删除，减少下一次循环数
							}
						}
						if(children.size()>0){
							for(Resources type: children){
								Map<String,Object> node = new HashMap<String,Object>();
								node.put("id", type.getId()+"");
								node.put("text", type.getName());
//								node.put("_parentId", pid);
								addActions(roleId,node);
								
								temp.add(node);
							}
							//存放当前节点的子资源节点
							item.put("children", temp.toArray(new Object[temp.size()]));
						}
					}
					//在doing中存放当前层的所有资源节点
					doing = temp;
				}
				return nodes;
	}

	public List<Map<String, Object>> getRoleRAMappings(Integer roleId,Set<Resources> resources) {
		//存放资源节点
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		//定义资源根节点
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", "TREES");
		root.put("text", "系统资源");
		addActions(null,root);
		nodes.add(root);
		//查出系统总数
		List<Systems> systems = this.systemDao.findList("from Systems");
		List<Object> all = new ArrayList<Object>();
		//循环遍历每个系统的资源
		for(Iterator<Systems> iter=systems.iterator();iter.hasNext();){
			all.add(this.getRoleRAMappings(roleId,resources,iter.next()).get(0));
		}
		
		root.put("children", all);
		return nodes;
	}

	
}
