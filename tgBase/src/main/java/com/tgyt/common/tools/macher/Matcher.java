/**   
  * @Title: Matcher.java 
  * @Package com.tgyt.common.tools.macher 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-7-27 下午01:57:02 
  * @version V1.0   
  */

package com.tgyt.common.tools.macher;

/** 
 * @ClassName: Matcher 
 * @Description: 对象匹配器的策略接口.<p>其核心思想是,通过给定的对象与模板相匹配.模板本身会提供不同的通配符
 * @author zhangfeng 13940488705@163.com
 * @date 2011-7-27 下午01:57:02 
 *  
 */
public interface Matcher {
	
	/** 
	  * @Title: isPattern 
	  * @Description: 给定的参数是否符合模板格式 
	  * @param @param 待确认的模板
	  * @return boolean 如果符合模板格式返回true,否则返回false
	  * @throws 
	  */
	boolean isPattern(String pattern);
	
	/** 
	  * @Title: match 
	  * @Description: 通过给定的模板判断对象值是否与该模板相匹配
	  * @param @param 规则模板
	  * @param @param 待匹配的对象
	  * @return boolean 如果对象与模板匹配返回true,否则返回false
	  * @throws 
	  */
	boolean match(String pattern, Object value);

}
