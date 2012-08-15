/**   
  * @Title: PrefixGenerator.java 
  * @Package com.tgyt.common.id 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午01:54:52 
  * @version V1.0   
  */

package com.tgyt.common.id;

/** 
 * @ClassName: PrefixGenerator 
 * @Description: 
 * 前缀生成器
 * 因为创建的值用于作为ID的前缀，所以取名为PrefixGenerator.
 * 对于在集群环境部署的系统，通常需要给ID设置前缀，这样就不会
 * 出现主键冲突的情况.
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:54:52 
 *  
 */
public interface PrefixGenerator {
	public String create() throws CreatePrefixException;
}
