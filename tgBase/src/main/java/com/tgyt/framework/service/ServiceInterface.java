/**   
 * @Title: ServiceInterface.java 
 * @Package com.tgyt.framework.service 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 上午10:04:36 
 * @version V1.0   
 */

package com.tgyt.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ServiceInterface
 * @Description: 通用Service接口
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:04:36
 * 
 */
public interface ServiceInterface<T> {
	/**
	 * 保存对象
	 * 
	 * @param obj
	 *            要保存的对象
	 * @return 布尔值
	 */
	public boolean save(T obj);

	/**
	 * 删除对象
	 * 
	 * @param obj
	 *            要删除的对象
	 * @return 布尔值
	 */
	public boolean delete(T obj);

	/**
	 * 删除对象
	 * 
	 * @param obj
	 *            要删除的对象
	 * @return 布尔值
	 */
	public boolean deleteById(Serializable id);

	/**
	 * 修改对象
	 * 
	 * @param obj
	 *            要修改的对象
	 * @return 布尔值
	 */
	public boolean alter(T obj);

	/**
	 * 查询对象
	 * 
	 * @param hql
	 *            查找语句
	 * @return 对象
	 */
	public T find(final String hql);

	/**
	 * 查询对象(防止依赖注入)
	 * 
	 * @param hql
	 *            查找语句
	 * @param map
	 *            参数
	 * @return 对象
	 */
	public T findOfMap(final String hql, Map<Serializable, Serializable> map);

	/**
	 * 主要用于防止sql依赖注入
	 * 
	 * @param hql
	 *            hql语句
	 * @param map
	 * @return List集合
	 */
	public List<T> findListOfMap(final String hql,
			final Map<Serializable, Serializable> map);

	/**
	 * 根据id查询对象
	 * 
	 * @param className
	 *            类名
	 * @param id
	 *            类id
	 * @return 对象
	 */
	public T findById(Serializable id);

	/**
	 * 根据id查询缓存对象
	 * 
	 * @param className
	 *            类名
	 * @param id
	 *            类id
	 * @return 对象
	 */
	public T findByIdLoad(Serializable id);

	/**
	 * 查询集合
	 * 
	 * @param sql
	 *            要保存的对象
	 * @return 集合
	 */
	public List<T> findList(String hql);

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
	public List<T> findList(String hql, int page, int count);

	/**
	 * 根据hql查找集合的条数
	 * 
	 * @param hql
	 *            hql语句
	 * @return 返回查询总数
	 */
	public int findCountBySql(String hql);

	// -------------------sql语句-----------------------//

	/**
	 * sql語句操作數據庫
	 * 
	 * @param sql
	 * @return boolean
	 */
	public boolean executeSql(String sql);

	/**
	 * sql語句查询集合
	 * 
	 * @param sql
	 * @return boolean
	 */
	public List findListBySql(final String sql, Class className);
}
