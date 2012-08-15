/**   
  * @Title: SequenceFormater.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:55:38 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: SequenceFormater 
 * @Description: 序列格式化
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:55:38 
 *  
 */
public interface SequenceFormater {
	public String format(long pSequence) throws FormatSequenceExcepiton;
}