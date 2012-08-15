/**   
  * @Title: NumberMatcher.java 
  * @Package com.tgyt.common.tools.macher 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-7-27 下午02:00:11 
  * @version V1.0   
  */

package com.tgyt.common.tools.macher;

import java.util.regex.Pattern;

/** 
 * @ClassName: NumberMatcher 
 * @Description: 数值匹配器,实现<code>Matcher</code>接口.
 * <p>模板通配符规则：
 * <UL>
 * <LI>* 代表任意多个0-9的数字</LI>
 * <LI>？代表一个0-9的数字</LI>
 * <LI>- 代表从x至y之间的数值区间</LI></UL>
 * <P>例如：<FONT face=宋体>32-5.5* ,第一位是3,第二位是2至5当中的任意一个数字,第三位是小数点,第四位是5，及5以后任意多个数字</FONT></P>
 * <P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp; 2?6，第一位是2,第二位是任意数字，第三位是6</P>
 * <P>&nbsp;</P>&nbsp;
 * @author zhangfeng 13940488705@163.com
 * @date 2011-7-27 下午02:00:11 
 * @see com.tgyt.common.tools.macher.Matcher
 *  
 */
public class NumberMatcher implements Matcher {

	/* (non-Javadoc)
	 * <p>Title: isPattern</p> 
	 * <p>Description: </p> 
	 * @param pattern
	 * @return 
	 * @see com.tgyt.common.tools.macher.Matcher#isPattern(java.lang.String) 
	 */
	public boolean isPattern(String pattern) {
		return (pattern.indexOf('*') != -1 || pattern.indexOf('?') != -1 || pattern.indexOf('-') != -1);
	}

	/* 通过给定的模板判断对象值是否与该模板相匹配
	 * <p>Title: match</p> 
	 * <p>Description: </p> 
	 * @param pattern 模板
	 * @param value 待匹配的对象,其类型必须是<code>Number</code>的子类
	 * @return 如果对象与模板匹配返回true,否则返回false
	 * @see com.tgyt.common.tools.macher.Matcher#match(java.lang.String, java.lang.Object) 
	 */
	public boolean match(String pattern, Object value) {
		if(!(value instanceof Number))
			return false;
		
		String str = ((Number)value).toString();

		if(pattern == null || pattern.trim().equals("") || str == null || str.trim().equals(""))
			return false;
		
		if(!Pattern.matches("[\\d|\\.]*",str))
			return false;
		
		pattern = pattern.replaceAll("[.]", "\\\\.");
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < pattern.length(); i++) {
			char subChar = pattern.charAt(i);
			switch(subChar){
				case '*':
					sb.append("\\d*");
					break;
				case '?':
					sb.append("\\d{1}");
					break;
				default:
					sb.append(subChar);
			}
		}
		
		String[] patterns = sb.toString().split("-");
		if(patterns.length <= 1)
			pattern = patterns[0];
		else{
			sb = new StringBuffer("");
			for (int i = 0; i < patterns.length; i++) {
				String element = patterns[i];
				if(i != 0)
					sb.append(element.charAt(0) + "]{1}" + element.substring(1));
				if(i != patterns.length - 1){
					sb.append(element.substring(0, element.length() - 1) + "[" + element.charAt(element.length()-1) + "-");
				}
			}
		}
		pattern = sb.toString();
		return Pattern.matches(pattern,str);
	}
	
	public static void main(String[] args) {
		NumberMatcher matcher = new NumberMatcher();
		System.out.println(matcher.match("34-5.5*", new Double(34.5345)));
		
//		System.out.println(Pattern.matches("34\\d*5","34345"));
	}

}
