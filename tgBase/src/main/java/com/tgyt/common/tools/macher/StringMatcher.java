/**   
  * @Title: StringMatcher.java 
  * @Package com.tgyt.common.tools.macher 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-7-27 下午02:21:36 
  * @version V1.0   
  */

package com.tgyt.common.tools.macher;

import java.util.regex.Pattern;

/** 
 * @ClassName: StringMatcher 
 * @Description:
 * 字符匹配器,实现<code>Matcher</code>接口.
 * <p>模板通配符规则：
 * <UL>
 * <LI>* 代表任意多个字符</LI>
 * <LI>？代表一个字符</LI>
 * <P>例如：<FONT face=宋体>org.hi.* ,前七位是org.hi.以后任意多个字符</FONT></P>
 * <P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp; 2?6，第一位是2,第二位是任意字符，第三位是6</P>
 * <P>&nbsp;</P>&nbsp;
 * @author zhangfeng 13940488705@163.com
 * @date 2011-7-27 下午02:21:36 
 *  
 */
public class StringMatcher implements Matcher {

	/* (non-Javadoc)
	 * <p>Title: isPattern</p> 
	 * <p>Description: </p> 
	 * @param pattern
	 * @return 
	 * @see com.tgyt.common.tools.macher.Matcher#isPattern(java.lang.String) 
	 */

	public boolean isPattern(String pattern) {
		return (pattern.indexOf('*') != -1 || pattern.indexOf('?') != -1);
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
		
		if(pattern == null || pattern.trim().equals("") || str == null || str.trim().equals(""))
			return false;
		
		pattern = pattern.replaceAll("[.]", "\\\\.");
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < pattern.length(); i++) {
			char subChar = pattern.charAt(i);
			switch(subChar){
				case '*':
					sb.append(".*");
					break;
				case '?':
					sb.append(".{1}");
					break;
				default:
					sb.append(subChar);
			}
		}
		
		pattern = sb.toString();
		return Pattern.matches(pattern,str);
	}

}
