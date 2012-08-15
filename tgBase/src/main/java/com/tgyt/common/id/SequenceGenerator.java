/**   
 * @Title: SequenceGenerator.java 
 * @Package com.tgyt.common.id 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午01:56:29 
 * @version V1.0   
 */

package com.tgyt.common.id;

/**
 * @ClassName: SequenceGenerator
 * @Description: 序列生成
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:56:29
 * 
 */
public interface SequenceGenerator {
	public long next() throws CreateSequnceException;
}
