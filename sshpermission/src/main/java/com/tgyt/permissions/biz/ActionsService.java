/**   
  * @Title: ActionsService.java 
  * @Package com.tgyt.permissions.biz 
  * @Description: 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:54:00 
  * @version V1.0   
  */

package com.tgyt.permissions.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

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
import com.tgyt.permissions.model.Resources;
import com.tgyt.permissions.model.Role;
import com.tgyt.permissions.model.Systems;

/** 
  * @ClassName: ActionsService 
  * @Description: 操作管理服务类 
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-5-31 上午9:06:41 
  *  
  */
@Service
public class ActionsService extends BaseService<Actions> implements
		IActionsService {

	@Resource(name="actionsDAO")
	private ActionsDAO actionsDAO;
	@Autowired
	private SystemDao systemDao;
	@Resource(name="resourcesDAO")
	private ResourcesDAO resourcesDAO;
	
	public Pagination getActionsList(Actions actions, int pageNo, int pageSize,String sort,String order) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Actions where 1=1");
		if(actions!=null){
			String name = actions.getName();
			String enname = actions.getEnname();
			String methodName = actions.getMethodName();
			String icon = actions.getIcon();
			String memo = actions.getMemo();
			String status = actions.getStatus();
			Integer orderid = actions.getOrderid();
			if(name!=null && !"".equals(name)){
				hql.append(" and name like '%"+name+"%'");
			}
			if(enname!=null && !"".equals(enname)){
				hql.append(" and enname like '%"+enname+"%'");
			}
			if(methodName!=null && !"".equals(methodName)){
				hql.append(" and handler like '%"+methodName+"%'");
			}
			if(icon!=null && !"".equals(icon)){
				hql.append(" and icon like '%"+icon+"%'");
			}
			if(status!=null && !"".equals(status)){
				hql.append(" and status = '"+status+"'");
			}
			if(orderid!=null){
				hql.append(" and orderid = "+orderid);
			}
			if(memo!=null &&!"".equals(memo)){
				hql.append(" and memo like '"+memo+"'");
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
		return this.actionsDAO.getHandler().getPageList(new Finder(hql.toString()), pageNo, pageSize);
	}

	protected DAOInterface<Actions> getDAO() {
		// TODO Auto-generated method stub
		return this.actionsDAO;
	}

	public int getMaxOrder(){
		return this.actionsDAO.getMaxOrder();
	}
	
	public List<Actions> getAll(){
		return this.actionsDAO.getAll();
	}
	/** 
	  * @Title: getAllListTrees 
	  * @Description: 获得所有的树节点
	  * @param @param roleId
	  * @param @return
	  * @return List<Map<String,Object>>
	  * @throws 
	  */
	public List<Map<String,Object>> getAllListTrees(Integer actionId) {
		Actions actions = this.actionsDAO.findById(actionId);
		Map<Integer,Object> maps = new HashMap<Integer,Object>();
		if(actions!=null){
			for(Iterator<Resources> iter=actions.getResource().iterator();iter.hasNext();){
				Resources rs = iter.next();
				maps.put(rs.getId(), rs.getName());
			}
		}
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("id", "TREES");
		root.put("text", "系统资源");
		nodes.add(root);
		
		List<Systems> systems = this.systemDao.findList("from Systems");
		List<Object> all = new ArrayList<Object>();
		for(Iterator<Systems> iter=systems.iterator();iter.hasNext();){
			all.add(this.getTree(maps,iter.next()).get(0));
		}
		
		root.put("children", all);
		return nodes;
	}
	
	/** 
	  * @Title: saveAuthorizate 
	  * @Description:保存树节点
	  * @param @param actionId
	  * @param @param resourcesIds
	  * @return void
	  * @throws 
	  */
	public void saveAuthorizate(Integer actionId,String resourcesIds){
		Actions action = this.findById(actionId);
		action.getResource().clear();
		Set<Resources> actionRes = new HashSet<Resources>(0);
		if(resourcesIds!=null &&!"".equals(resourcesIds)){
			String[] rids = resourcesIds.split(",");
			for(int i=0;i<rids.length;i++){
				if(rids[i].indexOf(ISystemService.TREE_NAME)!=-1){
					continue;
				}
				Resources rs = this.resourcesDAO.findById(Integer.parseInt(rids[i]));
				if(rs.getId() != null){
					//action.getResource().add(rs);
					actionRes.add(rs);
				}
			}
			action.setResource(actionRes);
		}
		this.alter(action);
	}
	
	public List<Map<String,Object>> getTree(Map<Integer,Object> maps,Systems system) {
		// 存放所有节点每一个节点信息都保存到一个Map<String,Object>中
		List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
		if(system==null){
			return new ArrayList<Map<String,Object>>();
		}
		
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
				Map<String,Object> node = new HashMap<String,Object>();
				node.put("id",  type.getId()+"");
				node.put("text", type.getName());
				if(maps.containsKey(type.getId())){
					node.put("checked", true);
					
				}
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
					if(maps.containsKey(type.getId())){
						node.put("checked", true);
						
					}
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
}
