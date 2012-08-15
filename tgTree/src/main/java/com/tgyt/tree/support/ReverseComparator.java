/**   
  * @Title: ReverseComparator.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:55:37 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import java.util.Comparator;

/** 
 * @ClassName: ReverseComparator 
 * @Description: 反转排序器
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:55:37 
 *  
 */
public class ReverseComparator implements Comparator{
	
	private Comparator comparator;
	public ReverseComparator(Comparator pComparator){
		if ( pComparator == null ){
			throw new RuntimeException("Comparator is null");
		}
		this.comparator = pComparator;
	}
	public int compare(Object o1, Object o2) {
		int result = comparator.compare(o1, o2);
		if ( result > 0 ){
			return -1;
		} else if ( result < 0 ){
			return 1;
		} else {
			return 0;
		}
	}

}
