/**   
  * @Title: DefaultIDGenerator.java 
  * @Package com.tgyt.common.id.generator 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:01:31 
  * @version V1.0   
  */

package com.tgyt.common.id.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.common.id.CreateIDException;
import com.tgyt.common.id.IDGenerator;
import com.tgyt.common.id.PrefixGenerator;
import com.tgyt.common.id.SequenceFormater;
import com.tgyt.common.id.SequenceGenerator;
import com.tgyt.common.id.sequence.DefaultSequenceGenerator;

/** 
 * @ClassName: DefaultIDGenerator 
 * @Description: 默认id生成器
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:01:31 
 *  
 */
public class DefaultIDGenerator implements IDGenerator {

	private PrefixGenerator prefixGenerator ; 
	private SequenceGenerator sequenceGenerator = new DefaultSequenceGenerator();
	private SequenceFormater sequenceFormater   ;
	
	private final Log logger = LogFactory.getLog( DefaultIDGenerator.class ) ;
	
	public synchronized String create() throws CreateIDException {
		final String prefix = prefixGenerator == null ?  "" : prefixGenerator.create();
		logger.debug("ID前缀是:[" + prefix + "]");
		long sequence = sequenceGenerator.next();
		final String strSequence = sequenceFormater == null ? new Long(sequence).toString() :
			sequenceFormater.format(sequence);
		return prefix + strSequence;
	}
	
	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	public void setSequenceFormater(SequenceFormater sequenceFormater) {
		this.sequenceFormater = sequenceFormater;
	}


}