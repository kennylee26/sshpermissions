package com.tgyt.flowList.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.flowList.dao.CFlowTypeDao;
import com.tgyt.flowList.dao.TypeFlowDao;
import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.flowList.model.TypeFlow;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
@Service
@Transactional
public class CFlowTypeService extends BaseService<CFlowtype> implements
		ICFlowTypeService {
	
    @Resource(name="typeFlowDao")
	private TypeFlowDao typeFlowDao;
    @Resource(name="flowTypeDao")
	private CFlowTypeDao flowTypeDao;

	@Override
	protected DAOInterface<CFlowtype> getDAO() {
		// TODO Auto-generated method stub
		return this.flowTypeDao;
	}
    private ArrayList<Integer> idList=new ArrayList<Integer>();
	@Override
	
	public List<Map<String, Object>> getTree() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();

		// 获取一级节点
		List<CFlowtype> types = this.flowTypeDao
				.findList("from CFlowtype where parentid is null");
		for (CFlowtype type : types) {
			Map<String, Object> node = new HashMap<String, Object>();
			node.put("id", type.getId());
			node.put("text", type.getTypename());
			nodes.add(node);
		}

		// 循环获取全部子节点
		List<Map<String, Object>> doing = new ArrayList<Map<String, Object>>();
		doing.addAll(nodes);
		while (!doing.isEmpty()) {
			List<Map<String, Object>> todo = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> item : doing) {
				//String id = item.get("id").toString();
				types = this.flowTypeDao
						.findList("from CFlowtype where parentid='"
								+ item.get("id") + "'");
				if (types.isEmpty())
					continue;

				List<Object> children = new ArrayList<Object>();
				for (CFlowtype type : types) {
					Map<String, Object> node = new HashMap<String, Object>();
					node.put("id", type.getId());
					node.put("text", type.getTypename());
					children.add(node);
					todo.add(node);
				}
				item.put("children",
						children.toArray(new Object[children.size()]));
			}
			doing = todo;
		}
		return nodes;
	}
//	public void deleteSubType(int id){
//		
//	}
	public int getMaxSort() {
		return flowTypeDao.getMaxSort();
	}
	public Boolean deleteType(int id){
		Boolean flag=false;
			idList.add(id);
			this.getNode(id);
			Iterator<Integer> item=idList.iterator();
			while(item.hasNext()){
				int tempId=(Integer) item.next();
				TypeFlow temp=this.typeFlowDao.find("from TypeFlow where typeid="+tempId);
				this.typeFlowDao.delete(temp);
				flag=this.flowTypeDao.deleteById(tempId);
			}
		
			return flag;
	}
	public void getNode(int id){
		//最上级
		CFlowtype flowType=this.flowTypeDao.findById(id);
		//第二层
		List<CFlowtype> list=this.flowTypeDao.findList("from CFlowtype where parentid="+flowType.getId());
			Iterator<CFlowtype> item=list.iterator();
			while(item.hasNext()){
				CFlowtype temp=(CFlowtype) item.next();
				idList.add(temp.getId());
				this.getNode(temp.getId());
			}
			
	}
}
