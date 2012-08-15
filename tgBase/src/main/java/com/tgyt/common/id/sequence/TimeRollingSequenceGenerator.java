/**   
  * @Title: TimeRollingSequenceGenerator.java 
  * @Package com.tgyt.common.id.sequence 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:13:25 
  * @version V1.0   
  */

package com.tgyt.common.id.sequence;

/** 
 * @ClassName: TimeRollingSequenceGenerator 
 * @Description: 时间滚动序列生成
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:13:25 
 *  
 */
public class TimeRollingSequenceGenerator extends AbstractRollingSequenceGenerator{

	private String time = null;
	private final String pattern;
	
	private TimeRollingSequenceGenerator(String pPattern){
		this.pattern = pPattern;
		java.util.Date now = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		time = sdf.format(now);
	}
	
	/**
	 * 日循环
	 * @return
	 */
	public static TimeRollingSequenceGenerator getDayRollingSequenceGenerator(){
		TimeRollingSequenceGenerator result = new TimeRollingSequenceGenerator("dd");
		return result;
	}
	
	/**
	 * 月循环
	 * @return
	 */
	public static TimeRollingSequenceGenerator getMonthRollingSequenceGenerator(){
		TimeRollingSequenceGenerator result = new TimeRollingSequenceGenerator("MM");
		return result;
	}
	
	/**
	 * 年循环
	 * @return
	 */
	public static TimeRollingSequenceGenerator getYearRollingSequenceGenerator(){
		TimeRollingSequenceGenerator result = new TimeRollingSequenceGenerator("yyyy");
		return result;
	}
	
	/**
	 * 时循环
	 * @return
	 */
	public static TimeRollingSequenceGenerator getHourRollingSequenceGenerator(){
		TimeRollingSequenceGenerator result = new TimeRollingSequenceGenerator("HH");
		return result;
	}
	
	
	protected boolean isResetCount() {
		java.util.Date currDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		String nowTime = sdf.format(currDate);
		if ( time.equals(nowTime) == false ){
			time = nowTime;
			return true;
		} else {
			return false;
		}
	}

	public String getPattern() {
		return pattern;
	}

}
