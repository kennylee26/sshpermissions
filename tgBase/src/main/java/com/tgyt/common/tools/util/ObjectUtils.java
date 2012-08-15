/**   
  * @Title: ObjectUtils.java 
  * @Package com.tgyt.common.tools.util 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:22:12 
  * @version V1.0   
  */

package com.tgyt.common.tools.util;

/** 
 * @ClassName: ObjectUtils 
 * @Description: 对象处理
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:22:12 
 *  
 */
public abstract class ObjectUtils {
	/**
	 * Return whether the given array is empty: that is, <code>null</code>
	 * or of zero length.
	 * @param array the array to check
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}
}

