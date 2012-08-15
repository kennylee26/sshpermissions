/**   
  * @Title: MemorySequenceStorer.java 
  * @Package com.tgyt.common.id.storer 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:34:01 
  * @version V1.0   
  */

package com.tgyt.common.id.storer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.common.id.SequenceStorer;
import com.tgyt.common.id.StoreSequenceException;

/** 
 * @ClassName: MemorySequenceStorer 
 * @Description: 内存数据保存
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:34:01 
 *  
 */
public class MemorySequenceStorer implements SequenceStorer{

	private final Log logger = LogFactory.getLog( MemorySequenceStorer.class );
	
	private Map cache = new HashMap();
	
	public void init() {
	}

	public long load(String sequenceID) throws StoreSequenceException {
		if ( logger.isDebugEnabled() ){
		   logger.debug("获取序号值,序号ＩＤ:" + sequenceID);
		}
		if ( cache.containsKey(sequenceID) == false ){
			save(0, sequenceID);
		}
		Long result = (Long)cache.get(sequenceID);
		return result.longValue();
	}

	public void save(long sequence, String sequenceID)	  
			throws StoreSequenceException {
		if ( logger.isDebugEnabled() ){
			logger.debug("保存序号,序号ＩＤ:[" + sequenceID + "]序号值:" + sequence);
		}
		cache.put(sequenceID, new Long(sequence) );
	}

}
