/**   
  * @Title: DefaultSequenceFormater.java 
  * @Package com.tgyt.common.id.fomater 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:59:35 
  * @version V1.0   
  */

package com.tgyt.common.id.fomater;

import java.text.DecimalFormat;

import com.tgyt.common.id.FormatSequenceExcepiton;
import com.tgyt.common.id.SequenceFormater;

/** 
 * @ClassName: DefaultSequenceFormater 
 * @Description: 序列格式化
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:59:35 
 *  
 */
public class DefaultSequenceFormater implements SequenceFormater {

	private String pattern;
	
	public String format(long pSequence) throws FormatSequenceExcepiton {
        DecimalFormat  df = new DecimalFormat(pattern);
        return df.format(pSequence);
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
