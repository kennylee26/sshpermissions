package com.tgyt.permissions.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.framework.dao.hspring.handler.IHandler;
import com.tgyt.permissions.model.Arguments;


/** 
  * @ClassName: ArgumentsDAO 
  * @Description: 全局参数DAO 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:44:00 
  *  
  */
@Repository(value="argumentsDAO")
public class ArgumentsDAO extends BaseDAO<Arguments> {

	/** 
	  * @Title: getMaxOrder 
	  * @Description: 获取全局参数表中的排序字段的最大值 
	  * @param @return
	  * @return int
	  * @throws 
	  */
	public int getMaxOrder(){
		IHandler handler = this.getHandler();
		Object result = handler.findObj("select max(orderid) from Arguments ");
		if(result!=null){
			return (Integer)result;
		}else{
			return 0;
		}
	}
	
	/** 
	  * @Title: getAllArguments 
	  * @Description: 获取全局参数
	  * @param @return
	  * @return List<Arguments>
	  * @throws 
	  */
	public List<Arguments> getAllArguments(){
		StringBuffer sb = new StringBuffer("from Arguments");
		return this.getHandler().findListOfObj(sb.toString());
	}
}
