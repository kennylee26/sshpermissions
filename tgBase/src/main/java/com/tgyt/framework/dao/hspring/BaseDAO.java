/**   
  * @Title: BaseDAO.java 
  * @Package com.tgyt.framework.dao.hspring 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午09:39:39 
  * @version V1.0   
  */

package com.tgyt.framework.dao.hspring;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tgyt.framework.dao.hibernate.Finder;
import com.tgyt.framework.dao.hspring.handler.IHandler;

/** 
 * @ClassName: BaseDAO 
 * @Description: 公共DAO，实现对数据库的增删改查
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午09:39:39 
 *  
 */
public abstract class BaseDAO<T> implements DAOInterface<T> {
	@Resource(name="springHandler")
	private IHandler handler;
	
	private Class<T> clazz;
	 public BaseDAO() {
		 clazz =(Class<T>) ((ParameterizedType) getClass()
	                                .getGenericSuperclass()).getActualTypeArguments()[0];
	    }
	/**
	 * 保存对象
	 * 
	 * @param obj
	 *            要保存的对象
	 * @return 布尔值
	 */
	public boolean save(T obj) {
		
		return handler.saveObj(obj);

	}

	/**
	 * 删除对象
	 * 
	 * @param obj
	 *            要删除的对象
	 * @return 布尔值
	 */
	public boolean delete(T obj) {

		return handler.deleteObj(obj);

	}
	/**
	 * 根据Id删除对象
	 * 
	 * @param obj
	 *            要删除的对象
	 * @return 布尔值
	 */
	public boolean deleteById(Serializable id) {

		return handler.deleteObj(handler.findObjById(clazz, id));

	}
	/**
	 * 修改对象
	 * 
	 * @param obj
	 *            要修改的对象
	 * @return 布尔值
	 */
	public boolean alter(T obj) {

		return handler.alterObj(obj);

	}

	/**
	 * 查询对象
	 * 
	 * @param hql
	 *            查找语句
	 * @return 对象
	 */
	public T find(final String hql) {
		return (T) handler.findObj(hql);		
	}
	/**
	 * 查询对象(防止依赖注入)
	 * 
	 * @param hql
	 *            查找语句
	 * @param map 参数           
	 * @return 对象
	 */
	public T findOfMap(final String hql,Map<Serializable,Serializable> map) {
		return (T) handler.findObj(hql,map);		
	}
	/**
	 * 主要用于防止sql依赖注入
	 * 
	 * @param hql
	 *            hql语句
	 * @param map
	 * @return List集合
	 */
	public List<T> findListOfMap(final String hql,
			final Map<Serializable, Serializable> map) {
		return handler.findListOfObj(hql, map);
	}

	/**
	 * 根据id查询对象
	 * 
	 * @param className
	 *            类名
	 * @param id
	 *            类id
	 * @return 对象
	 */
	public T findById(Serializable id) {

		return (T) handler.findObjById(clazz, id);

	}
	/**
	 * 根据id查询缓存对象
	 * 
	 * @param className
	 *            类名
	 * @param id
	 *            类id
	 * @return 对象
	 */
	public T findByIdLoad(Serializable id) {

		return (T) handler.findObjByIdLoad(clazz, id);

	}

	/**
	 * 查询集合
	 * 
	 * @param sql
	 *            要保存的对象
	 * @return 集合
	 */
	public List<T> findList(String hql) {

		return handler.findListOfObj(hql);

	}

	/**
	 * 根据页数和条数查询集合
	 * 
	 * @param sql
	 *            要保存的对象
	 * @param page
	 *            页数
	 * @param count
	 *            显示条数
	 * @return 集合
	 */
	public List<T> findList(String hql, int page, int count) {

		return handler.findListOfObj(hql, page, count);

	}

	/**
	 * 根据hql查找集合的条数
	 * 
	 * @param hql
	 *            hql语句
	 * @return 返回查询总数
	 */
	public int findCountBySql(String hql) {

		return handler.findCountBySql(hql);

	}

	//-------------------sql语句-----------------------//


	/**
	 * sql語句操作數據庫
	 * 
	 * @param sql
	 * @return boolean
	 */
	public boolean executeSql(String sql) {
		return handler.executeSql(sql);
	}
	/**
	 * sql語句查询集合
	 * 
	 * @param sql
	 * @return boolean
	 */
	public List findListBySql(final String sql,Class className){
		return handler.findListBySql(sql, className);
	}
	
	public IHandler getHandler() {
		return handler;
	}

	public void setHandler(IHandler handler) {
		this.handler = handler;
	}
	
	
}
