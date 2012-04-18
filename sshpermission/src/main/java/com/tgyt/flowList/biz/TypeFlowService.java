package com.tgyt.flowList.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.flowList.dao.CFlowTypeDao;
import com.tgyt.flowList.dao.TypeFlowDao;
import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.flowList.model.TypeFlow;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.model.Users;
@Service
@Transactional
public class TypeFlowService extends BaseService<TypeFlow> implements ITypeFlowService {
     @Resource(name="typeFlowDao")
	private TypeFlowDao typeFlowDao;
     @Resource(name="flowTypeDao")
 	private CFlowTypeDao flowTypeDao;
     
     private ArrayList<Integer> idList=new ArrayList<Integer>();
	@Override
	protected DAOInterface<TypeFlow> getDAO() {
		// TODO Auto-generated method stub
		return this.typeFlowDao;
	}

	@Override
	public Pagination getFlowList(TypeFlow typeFlow, Integer page,
			Integer rows, String sort, String order, Object object) {
		StringBuffer hql = new StringBuffer("from TypeFlow where 1=1");	
		if(typeFlow==null){
			return this.typeFlowDao.getHandler().getPageList(new Finder(hql.toString()), page, rows);
		}else{
			return this.typeFlowDao.getHandler().getPageList(new Finder(hql.toString()), page, rows);
		}
	}
	@Override
	public List getFlowList(TypeFlow typeFlow,int id){
		List<TypeFlow> list=new ArrayList<TypeFlow>();
		if(id==0||id==1){
			list=this.typeFlowDao.findList("from TypeFlow where 1=1");
		}else{
			idList.add(id);
			this.getNode(id);
			Iterator<Integer> item=idList.iterator();
			while(item.hasNext()){
				int tempId=(Integer) item.next();
				TypeFlow temp=this.typeFlowDao.find("from TypeFlow where typeid="+tempId);
				list.add(temp);
			}
			
		}
		idList.removeAll(idList);
		return list;
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
	private List doList(List<Users> list) {
		List lists = new ArrayList();
		if(list != null && list.size() > 0){
			for(int i =0;i<list.size();i++){
				Map<String,String> map = new HashMap<String, String>();
				Users user = list.get(i);
				map.put("address", user.getAddress());
				map.put("age", user.getAge());
				map.put("birthday", user.getBirthday());
				map.put("education", user.getEducation());
				map.put("employeddate", user.getEmployeddate());
				map.put("enname", user.getEnname());
				map.put("email", user.getEmail());
				map.put("lastlogoffdate", user.getLastlogoffdate());
				map.put("lastlogondate", user.getLastlogondate());
				map.put("lastlogonip", user.getLastlogonip());
				map.put("logonid", user.getLogonid());
				map.put("marriage", user.getMarriage());
				map.put("memo", user.getMemo());
				map.put("name", user.getName());
				
				map.put("nativeplace", user.getNativeplace());
				map.put("orderid", user.getOrderid());
				map.put("password", user.getPassword());
				
				map.put("phone", user.getPhone());
				map.put("position", user.getPosition());
				map.put("registerdate", user.getRegisterdate());
				map.put("sex", user.getSex());
				map.put("status", user.getStatus());
				map.put("usertype", user.getUsertype());
				
				map.put("id", user.getId() + "");
				
				
				lists.add(map);
			}
		}
		return lists;
	}
}
