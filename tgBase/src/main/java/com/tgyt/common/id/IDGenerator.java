/**   
 * @Title: IDGenerator.java 
 * @Package com.tgyt.common.id 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午01:53:41 
 * @version V1.0   
 */

package com.tgyt.common.id;

/**
 * @ClassName: IDGenerator
 * @Description: id自动生成
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:53:41
 * 
 */
public interface IDGenerator {
	public String create() throws CreateIDException;
}
