package com.tgyt.permissions.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.permissions.model.Dictionarys;

/** 
  * @ClassName: DictionaryDao 
  * @Description: 系统字典Dao层
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-26 上午9:52:29 
  *  
  */
@Repository(value="dictionaryDao")
public class DictionaryDao extends BaseDAO<Dictionarys> {
	
	/** 
	  * @Title: findMax 
	  * @Description: 获取昵称后的值
	  * @param @param nickName 昵称  生成的代码
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String findMax(String nickName){
		StringBuffer sb = new StringBuffer("from Dictionarys where nickName='" + nickName + "'");
		List<Dictionarys> list = this.getHandler().findListOfObj(sb.toString());
		if(null != list && list.size() > 0){
			int[] arr = new int[list.size()];
			for(int i=0;i<list.size();i++){
				Dictionarys tem = list.get(i);
				String temp = tem.getValue().substring(nickName.length());
				arr[i] = Integer.valueOf(temp);
			}
			Arrays.sort(arr);
			return nickName + String.valueOf(arr[arr.length-1]+1);
		}else{
			return nickName + "100";
		}
	}
	/** 
	  * @Title: getPageList 
	  * @Description: 获取某页的具体记录
	  * @param @param dic 一条Dictionarys对象记录
	  * @param @param page 当前页
	  * @param @param rows 一页显示的记录
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	public Pagination getPageList(Dictionarys dic, int page, int rows,String sort,String order) {
		StringBuffer sb = new StringBuffer("from Dictionarys where 1=1 ");
		if(null != dic){
			if(null != dic.getName() && !"".equals(dic.getName())){
				sb.append("and name like '%" + dic.getName() + "%' ");
			}
			if(null != dic.getCreateTime() && !"".equals(dic.getCreateTime())){
				sb.append("and createtime like '%" + dic.getCreateTime() + "%' ");
			}
			if(null != dic.getCreator() && !"".equals(dic.getCreator())){
				sb.append("and creator like '%" + dic.getCreator() + "%'");
			}
		}
		if(sort!=null && !"".equals(sort)){
			sb.append(" order by "+sort);
			if(order!=null && !"".equals(order)){
				sb.append(" "+order);
			}else{
				sb.append(" desc");
			}
		}
		return this.getHandler().getPageList(Finder.create(sb.toString()), page, rows);
	}
	
	/** 
	  * @Title: getAllDictionary 
	  * @Description: 获取所有的系统字典的记录
	  * @param @return
	  * @return List<Dictionarys>
	  * @throws 
	  */
	public List<Dictionarys> getAllDictionary(){
		StringBuffer sb = new StringBuffer("from Dictionarys");
		return this.getHandler().findListOfObj(sb.toString());
	}
}
