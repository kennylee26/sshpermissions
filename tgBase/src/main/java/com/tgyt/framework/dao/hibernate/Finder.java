/**   
 * @Title: Finder.java 
 * @Package com.tgyt.framework.dao.hibernate 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-4 下午03:07:23 
 * @version V1.0   
 */

package com.tgyt.framework.dao.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

/**
 * @ClassName: Finder
 * @Description: HQL语句分页查询
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-4 下午03:07:23
 * 
 */
public class Finder {
	protected Finder() {
	}
	
	public Finder(String hql) {
		hqlBuilder = new StringBuilder(hql);
	}
	
	public static Finder create(String hql) {
		Finder finder = new Finder(hql);
		return finder;
	}

	public Finder append(String hql) {
		hqlBuilder.append(hql);
		return this;
	}
	
	/** 
	  * @Title: getOrigHql 
	  * @Description: 获得原始hql语句
	  * @return String 返回的hql语句
	  * @throws 
	  */
	public String getOrigHql() {
		return hqlBuilder.toString();
	}
	
	/** 
	  * @Title: getRowCountHql 
	  * @Description: 获得查询数据库记录数的hql语句 
	  * @return String 列数
	  * @throws 
	  */
	public String getRowCountHql() {
		String hql = hqlBuilder.toString();

		int fromIndex = hql.toLowerCase().indexOf(FROM);
		String projectionHql = hql.substring(0, fromIndex);

		hql = hql.substring(fromIndex);
		String rowCountHql = hql.replace(HQL_FETCH, "");

		int index = rowCountHql.indexOf(ORDER_BY+" ");
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		return wrapProjection(projectionHql) + rowCountHql;
	}
	
	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	
	/** 
	  * @Title: setParam 
	  * @Description: 设置参数。与hibernate的Query接口一致。 
	  * @param param 
	  * @param value
	  * @return Finder 返回的查询对象
	  * @throws 
	  */
	public Finder setParam(String param, Object value) {
		return setParam(param, value, null);
	}
	
	/** 
	  * @Title: setParam 
	  * @Description: 设置参数。与hibernate的Query接口一致。
	  * @param param 参数
	  * @param value 值
	  * @param type 
	  * @return Finder
	  * @throws 
	  */
	public Finder setParam(String param, Object value, Type type) {
		getParams().add(param);
		getValues().add(value);
		getTypes().add(type);
		return this;
	}
	
	/** 
	  * @Title: setParams 
	  * @Description: 设置参数。与hibernate的Query接口一致。 
	  * @param paramMap 参数列表
	  * @return Finder 查询条件对象
	  * @throws 
	  */
	public Finder setParams(Map<String, Object> paramMap) {
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			setParam(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
	/** 
	  * @Title: setParamList 
	  * @Description: 设置参数列表
	  * @param name 名称
	  * @param vals 值
	  * @param type 
	  * @return Finder 查询条件对象
	  * @throws 
	  */
	public Finder setParamList(String name, Collection<Object> vals, Type type) {
		getParamsList().add(name);
		getValuesList().add(vals);
		getTypesList().add(type);
		return this;
	}
	
	/** 
	  * @Title: setParamList 
	  * @Description: 组成查询条件 
	  * @param name 条件名称
	  * @param vals 条件值
	  * @return Finder 查询条件对象
	  * @throws 
	  */
	public Finder setParamList(String name, Collection<Object> vals) {
		return setParamList(name, vals, null);
	}
	
	/** 
	  * @Title: setParamList 
	  * @Description: 设置参数。与hibernate的Query接口一致。
	  * @param name 条件名称
	  * @param vals 条件值
	  * @param type 类型
	  * @return Finder 查询条件对象
	  * @throws 
	  */
	public Finder setParamList(String name, Object[] vals, Type type) {
		getParamsArray().add(name);
		getValuesArray().add(vals);
		getTypesArray().add(type);
		return this;
	}
	
	/** 
	  * @Title: setParamList 
	  * @Description: 设置参数。与hibernate的Query接口一致。
	  * @param name 条件名称
	  * @param vals 条件值
	  * @return Finder 查询条件对象
	  * @throws 
	  */
	public Finder setParamList(String name, Object[] vals) {
		return setParamList(name, vals, null);
	}
	
	
	/** 
	  * @Title: setParamsToQuery 
	  * @Description: 将finder中的参数设置到query中。
	  * @param query 查询的条件
	  * @return Query 
	  * @throws 
	  */
	public Query setParamsToQuery(Query query) {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (types.get(i) == null) {
					query.setParameter(params.get(i), values.get(i));
				} else {
					query.setParameter(params.get(i), values.get(i), types
							.get(i));
				}
			}
		}
		if (paramsList != null) {
			for (int i = 0; i < paramsList.size(); i++) {
				if (typesList.get(i) == null) {
					query
							.setParameterList(paramsList.get(i), valuesList
									.get(i));
				} else {
					query.setParameterList(paramsList.get(i),
							valuesList.get(i), typesList.get(i));
				}
			}
		}
		if (paramsArray != null) {
			for (int i = 0; i < paramsArray.size(); i++) {
				if (typesArray.get(i) == null) {
					query.setParameterList(paramsArray.get(i), valuesArray
							.get(i));
				} else {
					query.setParameterList(paramsArray.get(i), valuesArray
							.get(i), typesArray.get(i));
				}
			}
		}
		return query;
	}
	
	public Query createQuery(Session s) {
		return setParamsToQuery(s.createQuery(getOrigHql()));
	}
	
	private String wrapProjection(String projection) {
		if (projection.indexOf("select") == -1) {
			return ROW_COUNT;
		} else {
			return projection.replace("select", "select count(") + ") ";
		}
	}

	private List<String> getParams() {
		if (params == null) {
			params = new ArrayList<String>();
		}
		return params;
	}

	private List<Object> getValues() {
		if (values == null) {
			values = new ArrayList<Object>();
		}
		return values;
	}

	private List<Type> getTypes() {
		if (types == null) {
			types = new ArrayList<Type>();
		}
		return types;
	}

	private List<String> getParamsList() {
		if (paramsList == null) {
			paramsList = new ArrayList<String>();
		}
		return paramsList;
	}

	private List<Collection<Object>> getValuesList() {
		if (valuesList == null) {
			valuesList = new ArrayList<Collection<Object>>();
		}
		return valuesList;
	}

	private List<Type> getTypesList() {
		if (typesList == null) {
			typesList = new ArrayList<Type>();
		}
		return typesList;
	}

	private List<String> getParamsArray() {
		if (paramsArray == null) {
			paramsArray = new ArrayList<String>();
		}
		return paramsArray;
	}

	private List<Object[]> getValuesArray() {
		if (valuesArray == null) {
			valuesArray = new ArrayList<Object[]>();
		}
		return valuesArray;
	}

	private List<Type> getTypesArray() {
		if (typesArray == null) {
			typesArray = new ArrayList<Type>();
		}
		return typesArray;
	}

	private StringBuilder hqlBuilder;

	private List<String> params;
	private List<Object> values;
	private List<Type> types;

	private List<String> paramsList;
	private List<Collection<Object>> valuesList;
	private List<Type> typesList;

	private List<String> paramsArray;
	private List<Object[]> valuesArray;
	private List<Type> typesArray;

	private int firstResult = 0;

	private int maxResults = 0;

	public static final String ROW_COUNT = "select count(*) ";
	public static final String FROM = "from";
	public static final String DISTINCT = "distinct";
	public static final String HQL_FETCH = "fetch";
	public static final String ORDER_BY = "order";

	public static void main(String[] args) {
		Finder find = Finder
				.create("select distinct p FROM BookType join fetch p");
		System.out.println(find.getRowCountHql());
		System.out.println(find.getOrigHql());
	}
	
}
