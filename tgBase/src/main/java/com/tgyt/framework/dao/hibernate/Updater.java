/**   
  * @Title: Updater.java 
  * @Package com.tgyt.framework.dao.hibernate 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-4 下午01:24:17 
  * @version V1.0   
  */

package com.tgyt.framework.dao.hibernate;

import java.util.HashSet;
import java.util.Set;

/** 
 * @ClassName: Updater 
 * @Description: 
 * 更新对象类
 * 
 * 提供三种更新模式：MAX, MIN, MIDDLE
 * <ul>
 * <li>MIDDLE：默认模式。除了null外，都更新。exclude和include例外。</li>
 * <li>MAX：最大化更新模式。所有字段都更新（包括null）。exclude例外。</li>
 * <li>MIN：最小化更新模式。所有字段都不更新。include例外。</li>
 * </ul>
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-4 下午01:24:17 
 *  
 */
public class Updater {
	
	private Object bean;

	private Set<String> includeProperties = new HashSet<String>();

	private Set<String> excludeProperties = new HashSet<String>();

	private UpdateMode mode = UpdateMode.MIDDLE;
	
	public static enum UpdateMode {
		MAX, MIN, MIDDLE
	}

	public Object getBean() {
		return bean;
	}
	
	public Set<String> getExcludeProperties() {
		return excludeProperties;
	}

	public Set<String> getIncludeProperties() {
		return includeProperties;
	}
	
	protected Updater(Object bean) {
		this.bean = bean;
	}
	
	/** 
	  * @Title: create 
	  * @Description: 创建更新对象
	  * @param bean 更新的对象
	  * @return Updater 要更新的对象
	  * @throws 
	  */
	public static Updater create(Object bean) {
		return new Updater(bean);
	}
	
	/** 
	  * @Title: create 
	  * @Description: 创建更新对象 
	  * @param @param bean 要更新的bean
	  * @param @param mode 更新模式
	  * @return Updater 更新的对象
	  * @throws 
	  */
	public static Updater create(Object bean, UpdateMode mode) {
		Updater updater = new Updater(bean);
		updater.setUpdateMode(mode);
		return updater;
	}
	
	/** 
	  * @Title: setUpdateMode 
	  * @Description: 设置更新模式
	  * @param mode 更新模式
	  * @return Updater 更新对象
	  * @throws 
	  */
	public Updater setUpdateMode(UpdateMode mode) {
		this.mode = mode;
		return this;
	}
	
	/** 
	  * @Title: include 
	  * @Description: 必须更新的字段
	  * @param property 更新的属性名称
	  * @return Updater 更新的对象
	  * @throws 
	  */
	public Updater include(String property) {
		includeProperties.add(property);
		return this;
	}
	
	/** 
	  * @Title: exclude 
	  * @Description: 不更新的字段
	  * @param property 不更新的属性
	  * @return Updater 更新的对象
	  * @throws 
	  */
	public Updater exclude(String property) {
		excludeProperties.add(property);
		return this;
	}
	
	/** 
	  * @Title: isUpdate 
	  * @Description: 某一字段是否更新
	  * @param name 字段名
	  * @param value 字段值
	  * @return boolean 是否更新
	  * @throws 
	  */
	public boolean isUpdate(String name, Object value) {
		if (this.mode == UpdateMode.MAX) {
			return !excludeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIN) {
			return includeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIDDLE) {
			if (value != null) {
				return !excludeProperties.contains(name);
			} else {
				return includeProperties.contains(name);
			}
		} else {
			// never reach
		}
		return true;
	}

}
