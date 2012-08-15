/**   
  * @Title: DefaultPrefixGenerator.java 
  * @Package com.tgyt.common.id.prefix 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:20:41 
  * @version V1.0   
  */

package com.tgyt.common.id.prefix;

import com.tgyt.common.id.CreatePrefixException;
import com.tgyt.common.id.PrefixGenerator;

/** 
 * @ClassName: DefaultPrefixGenerator 
 * @Description: 默认前缀生成器
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:20:41 
 *  
 */
public class DefaultPrefixGenerator implements PrefixGenerator{

	/**
	 * 前缀值
	 */
	private String prefix = "";
	
	/**
	 * 是否附带日期
	 */
	private boolean withDate = false;
	/**
	 * 日期格式
	 */
	private String pattern = "yyyyMMdd";
	
	public String create() throws CreatePrefixException {
		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		if ( this.withDate ){
			sb.append(getFormatedDate());
		}
		return sb.toString();
	}

	private String getFormatedDate(){
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(this.pattern);
		java.util.Date now = new java.util.Date();
		return  sdf.format(now);		
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isWithDate() {
		return withDate;
	}

	public void setWithDate(boolean withDate) {
		this.withDate = withDate;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}