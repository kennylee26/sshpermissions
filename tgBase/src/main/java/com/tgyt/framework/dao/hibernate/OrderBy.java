/**   
 * @Title: OrderBy.java 
 * @Package com.tgyt.framework.dao.hibernate 
 * @Description: 北京太谷雨田信息科技有限责任公司 版权所有 
 * @author zhangfeng  13940488705@163.com  
 * @date 2011-8-1 下午8:48:55 
 * @version V1.0   
 */

package com.tgyt.framework.dao.hibernate;

import org.hibernate.criterion.Order;

/**
 * @ClassName: OrderBy
 * @Description: 用于排序
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-1 下午8:48:55
 * 
 */

@SuppressWarnings("serial")
public class OrderBy extends Condition {
	private OrderType orderType;

	protected OrderBy(String field, OrderType orderType) {
		this.field = field;
		this.orderType = orderType;
	}

	public static OrderBy asc(String field) {
		return new OrderBy(field, OrderType.ASC);
	}

	public static OrderBy desc(String field) {
		return new OrderBy(field, OrderType.DESC);
	}

	public Order getOrder() {
		Order order = null;
		if (OrderType.ASC == orderType) {
			order = Order.asc(getField());
		} else if (OrderType.DESC == orderType) {
			order = Order.desc(getField());
		}
		return order;
	}

	public static Order[] asOrders(OrderBy[] orderBys) {
		if (orderBys != null) {
			Order[] orders = new Order[orderBys.length];
			for (int i = 0; i < orderBys.length; i++) {
				orders[i] = orderBys[i].getOrder();
			}
			return orders;
		} else {
			return null;
		}

	}

	public static enum OrderType {
		ASC, DESC
	}

}
