/**   
  * @Title: DateUtil.java 
  * @Package com.tgyt.common.tools.date 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-10 上午09:37:57 
  * @version V1.0   
  */

package com.tgyt.common.tools.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
/** 
 * @ClassName: DateUtil 
 * @Description: 时间处理类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-10 上午09:37:57 
 *  
 */
public class DateUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat();;

	/**
	 * 将时间转换成数据
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param format
	 *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
	 * @return String
	 */
	public static String dateToString(Timestamp date, String format) {
		// 附加时间格式
		dateFormat.applyPattern(format);
		// 将时间转换成字符串
		return dateFormat.format(date);
	}
	/**
	 * 将时间转换成数据
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param format
	 *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
	 * @return String
	 */
	public static String dateToString(java.util.Date date, String format) {
		// 附加时间格式
		dateFormat.applyPattern(format);
		// 将时间转换成字符串
		return dateFormat.format(date);
	}

	/**
	 * 将时间转换成时间
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param format
	 *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
	 * @return Date
	 */
	public static java.util.Date dateToDate(java.util.Date date, String format) {
		// 附加时间格式
		dateFormat.applyPattern(format);
		// 将时间转换成字符串
		try {
			return dateFormat.parse(dateToString(date, format));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字符串转换成时间
	 * 
	 * @param dateString
	 *            需要转换的时间字符串
	 * @param format
	 *            转换得格式 例如"yyyy-MM-dd hh:mm:ss"
	 * @return Date
	 */
	public static java.util.Date stringToDate(String dateString, String format) {
		// 附加时间格式
		dateFormat.applyPattern(format);
		// 将时间转换成字符串
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 比较两个时间的差值(以秒为单位)
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return long
	 */
	public static long dateDiff(java.util.Date date1, java.util.Date date2) {
		// return date1.getTime() / (24*60*60*1000) - date2.getTime() /
		// (24*60*60*1000);
		return date2.getTime() / 1000 - date1.getTime() / 1000; // 用立即数，减少乘法计算的开销
	}

	/**
	 * 作用：获取当前日期 格式 2007－3－4 返回类型：Date 参数：null
	 */
	public static Date date() {
		// 获取当前日期
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar
				.getInstance().getTime());
		// 将字符串日期转换成 java.sql.Date 日期类型
		return Date.valueOf(strDate);
	}
	/**
	 * 获取当前日期的前几天或者后几天日期
	 * @param day 天数 负数代表前几天，正数代表后几天
	 * @return
	 */
	public static Date getDateByDay(int day){
		// 获取当前日期
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE)+day);   		
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
		// 将字符串日期转换成 java.sql.Date 日期类型
		return Date.valueOf(strDate);
	}

	/**
	 * 作用：获取当前日期 格式 2007－3－4 12：10：20 返回类型：Date 参数：null
	 */
	public static Timestamp datetime() {
		// 获取当前日期
		String strTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		// 将字符串日期转换成 java.sql.Date 日期类型
		return Timestamp.valueOf(strTimestamp);
	}
	/**
	 * 作用：获取当前日期 格式 2007－3－4 12：10：20 返回类型：Date 参数：null
	 */
	public static String datetimeByString() {
		// 获取当前日期
		String strTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		// 将字符串日期转换成 java.sql.Date 日期类型
		return strTimestamp;
	}

	/**
	 * 作用：获取当前时间 格式 12：10：20 返回类型：Date 参数：null
	 */
	public static Time time() {
		// 获取当前日期
		String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar
				.getInstance().getTime());
		// 将字符串日期转换成 java.sql.Date 日期类型
		return Time.valueOf(strTime);
	}
	
	public static void main(String []args){
		String strTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String str=DateUtil.dateToString(DateUtil.datetime(), "HH:mm:ss");
	
		System.out.println(strTimestamp);
		Date.valueOf(strTimestamp);
		System.out.println(DateUtil.stringToDate(strTimestamp, "yyyy-MM-dd HH:mm:ss"));
	}
}
