/**   
  * @Title: AbstractRollingSequenceGenerator.java 
  * @Package com.tgyt.common.id.sequence 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:11:05 
  * @version V1.0   
  */

package com.tgyt.common.id.sequence;

import com.tgyt.common.id.CreateSequnceException;

/** 
 * @ClassName: AbstractRollingSequenceGenerator 
 * @Description: 卷生成器
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:11:05 
 *  
 */
public abstract class AbstractRollingSequenceGenerator extends DefaultSequenceGenerator{
	
	public long next() throws CreateSequnceException {
		if ( isResetCount() ){
			this.currCount = this.minValue;
			maxCount = this.currCount;
			sequenceStorer.save(maxCount, this.getId());
		}
		return super.next();
	}
	
   abstract protected boolean isResetCount();

}