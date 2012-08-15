/**   
  * @Title: Nullable.java 
  * @Package com.tgyt.framework.dao.hibernate 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-4 下午03:57:25 
  * @version V1.0   
  */

package com.tgyt.framework.dao.hibernate;

/** 
 * @ClassName: Nullable 
 * @Description: 设置空值
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-4 下午03:57:25 
 *  
 */
@SuppressWarnings("serial")
public class Nullable extends Condition {
	private boolean isNull;

	public Nullable(String field, boolean isNull) {
		this.field = field;
		this.isNull = isNull;
	}

	public static Nullable isNull(String field) {
		return new Nullable(field, true);
	}

	public static Nullable isNotNull(String field) {
		return new Nullable(field, false);
	}

	public boolean isNull() {
		return isNull;
	}
}
