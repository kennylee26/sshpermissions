/**   
 * @Title: MyBeanUtils.java 
 * @Package com.tgyt.common.tools.bean 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-4 下午03:42:33 
 * @version V1.0   
 */

package com.tgyt.common.tools.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @ClassName: MyBeanUtils
 * @Description: 我的bean对象，实现bean拷贝，基于apache
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-4 下午03:42:33
 * 
 */
public class MyBeanUtils {
	protected static Logger logger = LoggerFactory.getLogger(MyBeanUtils.class);

	/**
	 * @Title: describe
	 * @Description: 获得同时有get和set的field和value。
	 * @param bean
	 *            获得bean对象
	 * @return Map 返回属性列表
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Map describe(Object bean) {
		Map des = new HashMap();
		PropertyDescriptor desor[] = PropertyUtils.getPropertyDescriptors(bean);
		String name = null;
		for (int i = 0; i < desor.length; i++) {
			if (desor[i].getReadMethod() != null
					&& desor[i].getWriteMethod() != null) {
				name = desor[i].getName();
				try {
					des.put(name, PropertyUtils.getProperty(bean, name));
				} catch (Exception e) {
					throw new RuntimeException("属性不存在：" + name);
				}
			}
		}
		return des;
	}

	public static void setSimpleProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setSimpleProperty(bean, name, value);
		} catch (Exception e) {
			throw new RuntimeException("属性不存在：" + name);
		}
	}

	public static Object setSimpleProperty(Object bean, String name) {
		try {
			return PropertyUtils.getSimpleProperty(bean, name);
		} catch (Exception e) {
			throw new RuntimeException("属性不存在：" + name);
		}
	}

	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * @Title: setFieldValue
	 * @Description: 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 * @param object
	 *            对象名
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            值
	 * @param
	 * @throws NoSuchFieldException
	 * @return void
	 * @throws
	 */
	public static void setFieldValue(Object object, String fieldName,
			Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/** 
	  * @Title: getDeclaredField 
	  * @Description: 循环向上转型,获取对象的DeclaredField.
	  * @param @param object
	  * @param @param fieldName
	  * @param @return
	  * @param @throws NoSuchFieldException
	  * @return Field
	  * @throws 
	  */
	public static Field getDeclaredField(Object object, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}
	
	/** 
	  * @Title: getDeclaredField 
	  * @Description: 循环向上转型,获取类的DeclaredField.
	  * @param clazz 类名称
	  * @param fieldName 字段名称
	  * @param @throws NoSuchFieldException
	  * @return Field
	  * @throws 
	  */
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(Class clazz, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName()
				+ '.' + fieldName);
	}
}
