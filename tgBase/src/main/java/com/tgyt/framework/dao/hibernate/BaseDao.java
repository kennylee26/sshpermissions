/**   
  * @Title: BaseDao.java 
  * @Package com.tgyt.framework.dao.hibernate 
  * @Description: 北京太谷雨田信息科技有限责任公司 版权所有  
  * @author zhangfeng  13940488705@163.com  
  * @date 2011-8-1 下午8:36:54 
  * @version V1.0   
  */

package com.tgyt.framework.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import com.tgyt.common.tools.page.Pagination;

/** 
 * @ClassName: BaseDao 
 * @Description: 基本dao操作类
 * @author zhangfeng  13940488705@163.com
 * @date 2011-8-1 下午8:36:54 
 *  
 */
public interface BaseDao<T extends Serializable> {

	/** 
	  * @Title: load 
	  * @Description: 通过ID查找对象
	  * @param id 记录的ID
	  * @param lock 是否锁定对象
	  * @return T    实体对象 
	  * @throws 
	  */
	public T load(Serializable id, boolean lock);
	
	/** 
	  * @Title: get 
	  * @Description: 通过ID查找对象 
	  * @param id 记录的ID
	  * @return T    实体对象
	  * @throws 
	  */
	public T get(Serializable id);
	
	/** 
	  * @Title: load 
	  * @Description: 通过ID查找对象
	  * @param id
	  * @return T    实体对象 
	  * @throws 
	  */
	public T load(Serializable id);
	
	/** 
	  * @Title: findAll 
	  * @Description: 查找所有对象
	  * @return List<T>    对象列表 
	  * @throws 
	  */
	public List<T> findAll();
	
	
	/** 
	  * @Title: findAll 
	  * @Description: 根据多个排序进行列表显示
	  * @param orders 排序的字段
	  * @return List<T> 对象列表 
	  * @throws 
	  */
	public List<T> findAll(OrderBy... orders);
	
	/** 
	  * @Title: findAll 
	  * @Description: 查找指定段数的列表信息并且根据条件排序
	  * @param pageNo 页号
	  * @param pageSize 显示页数
	  * @param orders 排序信息
	  * @return Pagination 分页对象
	  * @throws 
	  */
	public Pagination findAll(int pageNo, int pageSize, OrderBy... orders);
	
	/** 
	  * @Title: findByEgList 
	  * @Description: 通过示例对象查找对象列表
	  * @param eg 示例对象
	  * @param anyWhere 是否模糊查询，默认false。
	  * @param conds 排序和is null的字段。分别为OrderBy和String。
	  * @param exclude 需要排除的属性
	  * @return List<T> 对象列表
	  * @throws 
	  */
	public List<T> findByEgList(T eg, boolean anyWhere, Condition[] conds,
			String... exclude);
	
	/** 
	  * @Title: findByEgList 
	  * @Description: 通过示例对象查找对象列表 
	  * @param eg 示例对象
	  * @param anyWhere 是否模糊查询，默认false。
	  * @param conds 排序和is null的字段。分别为OrderBy和String。
	  * @param firstResult 第一个结果
	  * @param maxResult 最大结果
	  * @param exclude 不包含
	  * @return List<T> 对象列表
	  * @throws 
	  */
	public List<T> findByEgList(T eg, boolean anyWhere, Condition[] conds,
			int firstResult, int maxResult, String... exclude);
	
	/** 
	  * @Title: findByEg 
	  * @Description: 通过示例对象查找对象列表 
	  * @param exampleInstance 示例对象
	  * @param anyWhere 是否模糊查询，默认false。
	  * @param conds 排序和is null的字段。分别为OrderBy和String。
	  * @param pageNo 起始页号
	  * @param pageSize 每页列数
	  * @param exclude 不包含
	  * @return Pagination 分页对象
	  * @throws 
	  */
	public Pagination findByEg(T exampleInstance, boolean anyWhere,
			Condition[] conds, int pageNo, int pageSize, String... exclude);
	
	/** 
	  * @Title: findByProperty 
	  * @Description: 按属性查找对象列表.
	  * @param property 属性
	  * @param value 显示的值
	  * @return List<T> 对象列表
	  * @throws 
	  */
	public List<T> findByProperty(String property, Object value);
	
	
	/** 
	  * @Title: findUniqueByProperty 
	  * @Description: 查找唯一的属性
	  * @param @param property 属性名
	  * @param @param value 值
	  * @return T 对象
	  * @throws 
	  */
	public T findUniqueByProperty(String property, Object value);
	
	/** 
	  * @Title: countByProperty 
	  * @Description: 按属性查找对象的数量 
	  * @param @param property 属性名
	  * @param @param value 属性值
	  * @return int 属性数量
	  * @throws 
	  */
	public int countByProperty(String property, Object value);
	
	/** 
	  * @Title: updateByUpdater 
	  * @Description: 按照更新的内容更新字段 
	  * @param updater 更新对象
	  * @return Object 更新结果
	  * @throws 
	  */
	public Object updateByUpdater(Updater updater);
	
	/** 
	  * @Title: updateDefault 
	  * @Description: 更新指定的对象
	  * @param entity 更新指定的对象
	  * @return Object 更新结果
	  * @throws 
	  */
	public Object updateDefault(Object entity);
	
	/** 
	  * @Title: save 
	  * @Description: 保存对象
	  * @param entity 要保存的实体
	  * @return T 返回结果
	  * @throws 
	  */
	public T save(T entity);
	
	/** 
	  * @Title: update 
	  * @Description: 更新对象
	  * @param entity 实体对象
	  * @return Object 实体对象
	  * @throws 
	  */
	public Object update(Object entity);
	
	/** 
	  * @Title: saveOrUpdate 
	  * @Description: 保存或更新对象
	  * @param entity 实体对象
	  * @return Object 实体对象
	  * @throws 
	  */
	public Object saveOrUpdate(Object entity);
	
	/** 
	  * @Title: merge 
	  * @Description: 保存或更新对象拷贝
	  * @param entity 实体对象
	  * @return Object 实体对象
	  * @throws 
	  */
	public Object merge(Object entity);
	
	/** 
	  * @Title: delete 
	  * @Description: 删除对象
	  * @param entity 实体对象
	  * @return void
	  * @throws 
	  */
	public void delete(Object entity);
	
	/** 
	  * @Title: deleteById 
	  * @Description: 根据ID删除记录
	  * @param id 记录ID
	  * @return T 实体对象
	  * @throws 
	  */
	public T deleteById(Serializable id);
	
	/** 
	  * @Title: refresh 
	  * @Description: 刷新对象 
	  * @param entity 实体对象
	  * @return void
	  * @throws 
	  */
	public void refresh(Object entity);
	
	/** 
	  * @Title: getPersistentClass 
	  * @Description: 获得实体Class
	  * @return Class<T>
	  * @throws 
	  */
	public Class<T> getPersistentClass();
	
	/** 
	  * @Title: createNewEntiey 
	  * @Description: 创建实体类的对象
	  * @return T 实体对象
	  * @throws 
	  */
	public T createNewEntiey();
	
}
