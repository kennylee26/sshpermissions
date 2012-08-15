/**   
  * @Title: PathMatcher.java 
  * @Package com.tgyt.common.tools.macher 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-7-27 下午02:08:00 
  * @version V1.0   
  */

package com.tgyt.common.tools.macher;

import org.springframework.util.AntPathMatcher;

/** 
 * @ClassName: PathMatcher 
 * @Description: 文件路径匹配器,实现<code>Matcher</code>接口.该类仅是spring的
 * AntPathMatcher类做一层简单的封装,具体实现与特点请参见spring
 * @author zhangfeng 13940488705@163.com
 * @date 2011-7-27 下午02:08:00 
 * @see com.tgyt.common.tools.macher.Matcher
 * @see org.springframework.util.AntPathMatcher
 *  
 */
public class PathMatcher implements Matcher {
	
	AntPathMatcher pathMatcher;
	
	public PathMatcher(){
		pathMatcher = new AntPathMatcher();
	}
	
	/** 
	  * @Title: setPathSeparator 
	  * @Description: 设置文件路径分隔符,缺省为 /
	  * @param @param pathSeparator 文件路径分隔符
	  * @return void
	  * @throws 
	  */
	public void setPathSeparator(String pathSeparator) {
		pathMatcher.setPathSeparator(pathSeparator);
	}

	/* (non-Javadoc)
	 * <p>Title: isPattern</p> 
	 * <p>Description: </p> 
	 * @param pattern
	 * @return 
	 * @see com.tgyt.common.tools.macher.Matcher#isPattern(java.lang.String) 
	 */

	public boolean isPattern(String pattern) {
		return pathMatcher.isPattern(pattern);
	}

	/* (non-Javadoc)
	 * <p>Title: match</p> 
	 * <p>Description: </p> 
	 * @param pattern
	 * @param value
	 * @return 
	 * @see com.tgyt.common.tools.macher.Matcher#match(java.lang.String, java.lang.Object) 
	 */

	public boolean match(String pattern, Object value) {
		if(!(value instanceof String))
			return false;
		
		String str = (String)value;
		
		return pathMatcher.match(pattern, str);
	}

}
