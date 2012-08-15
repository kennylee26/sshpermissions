/**   
  * @Title: BaseDaoImpl.java 
  * @Package com.tgyt.framework.dao.hibernate 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-4 下午01:41:58 
  * @version V1.0   
  */

package com.tgyt.framework.dao.hibernate;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.tgyt.common.tools.bean.MyBeanUtils;
import com.tgyt.common.tools.page.Pagination;

/** 
 * @ClassName: BaseDaoImpl 
 * @Description: DAO基类。
 * 提供hql分页查询，example分页查询，拷贝更新等功能。
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-4 下午01:41:58 
 *  
 */
@Repository
public class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	public static final NotBlankPropertySelector NOT_BLANK = new NotBlankPropertySelector();
	
	protected SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/* (non-Javadoc)
	 * <p>Title: countByProperty</p> 
	 * <p>Description: </p> 
	 * @param property
	 * @param value
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#countByProperty(java.lang.String, java.lang.Object) 
	 */
	public int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	/* (non-Javadoc)
	 * <p>Title: createNewEntiey</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#createNewEntiey() 
	 */
	public T createNewEntiey() {
		try {
			return getPersistentClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("不能创建实体对象："
					+ getPersistentClass().getName());
		}
	}

	/* (non-Javadoc)
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#delete(java.lang.Object) 
	 */
	public void delete(Object entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
	}

	/* (non-Javadoc)
	 * <p>Title: deleteById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#deleteById(java.io.Serializable) 
	 */
	public T deleteById(Serializable id) {
		Assert.notNull(id);
		T entity = load(id);
		getSession().delete(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findAll() 
	 */

	public List<T> findAll() {
		return findByCriteria();
	}

	/* (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description: </p> 
	 * @param orders
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findAll(com.tgyt.framework.dao.hibernate.OrderBy[]) 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(OrderBy... orders) {
		Criteria crit = createCriteria();
		if (orders != null) {
			for (OrderBy order : orders) {
				crit.addOrder(order.getOrder());
			}
		}
		return crit.list();
	}

	/* (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description: </p> 
	 * @param pageNo
	 * @param pageSize
	 * @param orders
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findAll(int, int, com.tgyt.framework.dao.hibernate.OrderBy[]) 
	 */
	public Pagination findAll(int pageNo, int pageSize, OrderBy... orders) {
		Criteria crit = createCriteria();
		return findByCriteria(crit, pageNo, pageSize, null, OrderBy
				.asOrders(orders));
	}
	
	/** 
	  * @Title: find 
	  * @Description: 查询方法
	  * @param hql 使用的hql语句
	  * @param values 要查找的值
	  * @return List 返回的数据列表
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	protected List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}
	
	/** 
	  * @Title: find 
	  * @Description: 查找对象根据组成的条件
	  * @param @param finder
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @return
	  * @return Pagination
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	protected Pagination find(Finder finder, int pageNo, int pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		List list = query.list();
		p.setList(list);
		return p;
	}
	
	/** 
	  * @Title: find 
	  * @Description: 根据hql语句查询
	  * @param finder 查询对象
	  * @return List 结果列表
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	protected List find(Finder finder) {
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(finder.getFirstResult());
		if (finder.getMaxResults() > 0) {
			query.setMaxResults(finder.getMaxResults());
		}
		List list = query.list();
		return list;
	}
	
	/** 
	  * @Title: findUnique 
	  * @Description: 查找唯一对象
	  * @param hql
	  * @param values
	  * @return Object
	  * @throws 
	  */
	protected Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}
	
	/** 
	  * @Title: createCriteria 
	  * @Description: 创建查询对象
	  * @param criterions 查询对象列表
	  * @return Criteria 
	  * @throws 
	  */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/* (non-Javadoc)
	 * <p>Title: findByEg</p> 
	 * <p>Description: </p> 
	 * @param exampleInstance
	 * @param anyWhere
	 * @param conds
	 * @param pageNo
	 * @param pageSize
	 * @param exclude
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findByEg(java.io.Serializable, boolean, com.tgyt.framework.dao.hibernate.Condition[], int, int, java.lang.String[]) 
	 */
	public Pagination findByEg(T eg, boolean anyWhere, Condition[] conds,
			int page, int pageSize, String... exclude) {
		Order[] orderArr = null;
		Condition[] condArr = null;
		if (conds != null && conds.length > 0) {
			List<Order> orderList = new ArrayList<Order>();
			List<Condition> condList = new ArrayList<Condition>();
			for (Condition c : conds) {
				if (c instanceof OrderBy) {
					orderList.add(((OrderBy) c).getOrder());
				} else {
					condList.add(c);
				}
			}
			orderArr = new Order[orderList.size()];
			condArr = new Condition[condList.size()];
			orderArr = orderList.toArray(orderArr);
			condArr = condList.toArray(condArr);
		}
		Criteria crit = getCritByEg(eg, anyWhere, condArr, exclude);
		return findByCriteria(crit, page, pageSize, null, orderArr);
	}
	
	/** 
	  * @Title: createQuery 
	  * @Description: 根据条件查询 
	  * @param queryString 查询条件
	  * @param values 查询的值
	  * @return Query 查询对象
	  * @throws 
	  */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
	
	/** 
	  * @Title: findByCriteria 
	  * @Description: 根据对象查询
	  * @param criterion 条件
	  * @return List<T> 查询的列表的值
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}
	
	/** 
	  * @Title: findByCriteria 
	  * @Description: 根据对象查询
	  * @param crit 对象条件
	  * @param pageNo 超始页
	  * @param pageSize 显示页数
	  * @param projection
	  * @param orders 排序条件
	  * @return Pagination 分页组件对象
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	protected Pagination findByCriteria(Criteria crit, int pageNo,
			int pageSize, Projection projection, Order... orders) {
		int totalCount = ((Number) crit.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		crit.setProjection(projection);
		if (projection == null) {
			crit.setResultTransformer(Criteria.ROOT_ENTITY);
		}
		if (orders != null) {
			for (Order order : orders) {
				crit.addOrder(order);
			}
		}
		crit.setFirstResult(p.getFirstResult());
		crit.setMaxResults(p.getPageSize());
		p.setList(crit.list());
		return p;
	}
	
	/** 
	  * @Title: countQueryResult 
	  * @Description: 统计查询结果
	  * @param finder 查询条件
	  * @return int 指定条件查询的数量
	  * @throws 
	  */
	protected int countQueryResult(Finder finder) {
		System.out.println(finder.getRowCountHql());
		System.out.println(getSession());
		Query query = getSession().createQuery(finder.getRowCountHql());
		finder.setParamsToQuery(query);
		System.out.println(((Number) query.iterate().next()).intValue());
		return ((Number) query.iterate().next()).intValue();
	}
	
	/** 
	  * @Title: countQueryResult 
	  * @Description: 查询指定条件列表
	  * @param c 组合查询条件
	  * @return int 返回数量
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	protected int countQueryResult(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;
		System.out.println("..................");
		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) MyBeanUtils.getFieldValue(impl,
					"orderEntries");
			MyBeanUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("不可能抛出的异常:{}", e.getMessage());
		}
		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount())
				.uniqueResult();
		if (totalCount < 1) {
			return 0;
		}
		// 将之前的Projection和OrderBy条件重新设回去
		c.setProjection(projection);
		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			MyBeanUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("不可能抛出的异常:{}", e.getMessage());
		}
		return totalCount;
	}
	
	protected Criteria getCritByEg(T bean, boolean anyWhere, Condition[] conds,
			String... exclude) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(bean);
		example.setPropertySelector(NOT_BLANK);
		if (anyWhere) {
			example.enableLike(MatchMode.ANYWHERE);
			example.ignoreCase();
		}
		for (String p : exclude) {
			example.excludeProperty(p);
		}
		crit.add(example);
		// 处理排序和is null字段
		if (conds != null) {
			for (Condition o : conds) {
				if (o instanceof OrderBy) {
					OrderBy order = (OrderBy) o;
					crit.addOrder(order.getOrder());
				} else if (o instanceof Nullable) {
					Nullable isNull = (Nullable) o;
					if (isNull.isNull()) {
						crit.add(Restrictions.isNull(isNull.getField()));
					} else {
						crit.add(Restrictions.isNotNull(isNull.getField()));
					}
				} else {
					// never
				}
			}
		}
		// 处理many to one查询
		ClassMetadata cm = getCmd(bean.getClass());
		String[] fieldNames = cm.getPropertyNames();
		for (String field : fieldNames) {
			Object o = cm.getPropertyValue(bean, field, POJO);
			if (o == null) {
				continue;
			}
			ClassMetadata subCm = getCmd(o.getClass());
			if (subCm == null) {
				continue;
			}
			Serializable id = subCm.getIdentifier(o, POJO);
			if (id != null) {
				Serializable idName = subCm.getIdentifierPropertyName();
				crit.add(Restrictions.eq(field + "." + idName, id));
			} else {
				crit.createCriteria(field).add(Example.create(o));
			}
		}
		return crit;
	}
	
	@SuppressWarnings("unchecked")
	private ClassMetadata getCmd(Class clazz) {
		return (ClassMetadata) sessionFactory.getClassMetadata(clazz);
	}

	/* (non-Javadoc)
	 * <p>Title: findByEgList</p> 
	 * <p>Description: </p> 
	 * @param eg
	 * @param anyWhere
	 * @param conds
	 * @param exclude
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findByEgList(java.io.Serializable, boolean, com.tgyt.framework.dao.hibernate.Condition[], java.lang.String[]) 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByEgList(T eg, boolean anyWhere, Condition[] conds,
			String... exclude) {
		Criteria crit = getCritByEg(eg, anyWhere, conds, exclude);
		return crit.list();
	}

	/* (non-Javadoc)
	 * <p>Title: findByEgList</p> 
	 * <p>Description: </p> 
	 * @param eg
	 * @param anyWhere
	 * @param conds
	 * @param firstResult
	 * @param maxResult
	 * @param exclude
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findByEgList(java.io.Serializable, boolean, com.tgyt.framework.dao.hibernate.Condition[], int, int, java.lang.String[]) 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByEgList(T eg, boolean anyWhere, Condition[] conds,
			int firstResult, int maxResult, String... exclude) {
		Criteria crit = getCritByEg(eg, anyWhere, conds, exclude);
		crit.setFirstResult(firstResult);
		crit.setMaxResults(maxResult);
		return crit.list();
	}

	/* (non-Javadoc)
	 * <p>Title: findByProperty</p> 
	 * <p>Description: </p> 
	 * @param property
	 * @param value
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findByProperty(java.lang.String, java.lang.Object) 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/* (non-Javadoc)
	 * <p>Title: findUniqueByProperty</p> 
	 * <p>Description: </p> 
	 * @param property
	 * @param value
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#findUniqueByProperty(java.lang.String, java.lang.Object) 
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	/* (non-Javadoc)
	 * <p>Title: get</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#get(java.io.Serializable) 
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		Assert.notNull(id);
		return (T) getSession().get(getPersistentClass(), id);
	}

	static final class NotBlankPropertySelector implements PropertySelector {
		private static final long serialVersionUID = 1L;

		public boolean include(Object object, String property, Type type) {
			return object != null
					&& !(object instanceof String && StringUtils
							.isBlank((String) object));
		}
	}
	
	/* (non-Javadoc)
	 * <p>Title: getPersistentClass</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#getPersistentClass() 
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/* (non-Javadoc)
	 * <p>Title: load</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @param lock
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#load(java.io.Serializable, boolean) 
	 */
	@SuppressWarnings("unchecked")
	public T load(Serializable id, boolean lock) {
		Assert.notNull(id);
		T entity = null;
		if (lock) {
			entity = (T) getSession().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getSession().load(getPersistentClass(), id);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * <p>Title: load</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#load(java.io.Serializable) 
	 */
	public T load(Serializable id) {
		Assert.notNull(id);
		return load(id, false);
	}

	/* (non-Javadoc)
	 * <p>Title: merge</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#merge(java.lang.Object) 
	 */
	public Object merge(Object entity) {
		Assert.notNull(entity);
		return getSession().merge(entity);
	}

	/* (non-Javadoc)
	 * <p>Title: refresh</p> 
	 * <p>Description: </p> 
	 * @param entity 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#refresh(java.lang.Object) 
	 */
	public void refresh(Object entity) {
		getSession().refresh(entity);
	}

	/* (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#save(java.io.Serializable) 
	 */
	public T save(T entity) {
		Assert.notNull(entity);
		getSession().save(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * <p>Title: saveOrUpdate</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#saveOrUpdate(java.lang.Object) 
	 */
	public Object saveOrUpdate(Object entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * <p>Title: update</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#update(java.lang.Object) 
	 */
	public Object update(Object entity) {
		Assert.notNull(entity);
		getSession().update(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * <p>Title: updateByUpdater</p> 
	 * <p>Description: </p> 
	 * @param updater
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#updateByUpdater(com.tgyt.framework.dao.hibernate.Updater) 
	 */
	public Object updateByUpdater(Updater updater) {
		ClassMetadata cm = getCmd(updater.getBean().getClass());
		if (cm == null) {
			throw new RuntimeException("所更新的对象没有映射或不是实体对象");
		}
		Object bean = updater.getBean();
		Object po = getSession().load(bean.getClass(),
				cm.getIdentifier(bean, POJO));
		updaterCopyToPersistentObject(updater, po);
		return po;
	}
	
	/** 
	  * @Title: updaterCopyToPersistentObject 
	  * @Description: 将更新对象拷贝至实体对象，并处理many-to-one的更新。
	  * @param updater 更新对象
	  * @param po 
	  * @return void
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	private void updaterCopyToPersistentObject(Updater updater, Object po) {
		Map map = MyBeanUtils.describe(updater.getBean());
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (!updater.isUpdate(name, value)) {
				continue;
			}
			if (value != null) {
				Class valueClass = value.getClass();
				ClassMetadata cm = getCmd(valueClass);
				if (cm != null) {
					Serializable vid = cm.getIdentifier(value, POJO);
					// 如果更新的many to one的对象的id为空，则将many to one设置为null。
					if (vid != null) {
						value = getSession().load(valueClass, vid);
					} else {
						value = null;
					}
				}
			}
			try {
				PropertyUtils.setProperty(po, name, value);
			} catch (Exception e) {
				// never
				log.warn("更新对象时，拷贝属性异常", e);
			}
		}
	}
	
	/* (non-Javadoc)
	 * <p>Title: updateDefault</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return 
	 * @see com.tgyt.framework.dao.hibernate.BaseDao#updateDefault(java.lang.Object) 
	 */
	public Object updateDefault(Object entity) {
		return updateByUpdater(Updater.create(entity));
	}

}
