package com.tgyt.flowList.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.flowList.dao.CFlowTypeDao;
import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
@Service
@Transactional
public class CFlowTypeService extends BaseService<CFlowtype> implements
		ICFlowTypeService {
	
	@Resource(name="flowTypeDao")
	private CFlowTypeDao flowTypeDao;

	@Override
	protected DAOInterface<CFlowtype> getDAO() {
		// TODO Auto-generated method stub
		return this.flowTypeDao;
	}

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

}
