/**   
 * @Title: PropertyNodeComparator.java 
 * @Package com.tgyt.tree.support 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午04:53:44 
 * @version V1.0   
 */

package com.tgyt.tree.support;

import org.apache.commons.beanutils.PropertyUtils;

import com.tgyt.tree.Node;

/**
 * @ClassName: PropertyNodeComparator
 * @Description: 按指定属性名进行排序.属性名必须在业务对象中存在
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:53:44
 * 
 */
public class PropertyNodeComparator extends AbstractNodeComparator {

	private final String sortProperty;

	public PropertyNodeComparator(String pSortProperty) {
		if (pSortProperty == null) {
			throw new java.lang.RuntimeException("排序属性名不能为空null");
		}
		this.sortProperty = pSortProperty;
	}

	protected Comparable getComparableProperty(Node pNode) {
		if (pNode instanceof DefaultNode == false) {
			throw new IllegalArgumentException(
					"node type is error, should be DefaultNode!");
		}
		DefaultNode defaultNode = (DefaultNode) pNode;
		Object userData = defaultNode.getUserData();
		if (userData == null) {
			return new Integer(0);
		}
		Object result = null;
		try {
			result = PropertyUtils.getProperty(userData, this.sortProperty);
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.lang.RuntimeException("获取 "
					+ userData.getClass().getName() + "的" + this.sortProperty
					+ "属性失败!", e);
		}
		if (result == null) {
			return null;
		}
		if (result instanceof Comparable == false) {
			throw new java.lang.RuntimeException(userData.getClass().getName()
					+ "的属性[" + this.sortProperty + "]没有实现接口:"
					+ Comparable.class.getName());
		}
		return (Comparable) result;
	}
}
