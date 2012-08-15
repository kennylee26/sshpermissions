/**   
 * @Title: SequenceStorer.java 
 * @Package com.tgyt.common.id 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午01:57:10 
 * @version V1.0   
 */

package com.tgyt.common.id;

/**
 * @ClassName: SequenceStorer
 * @Description: 保存序列
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午01:57:10
 * 
 */
public interface SequenceStorer {

	/**
	 * 保存序号
	 * 
	 * @param pSequence
	 *            序号
	 * @param pSequenceID
	 *            序号ID
	 * @throws StoreSequenceException
	 */
	public void save(long pSequence, final String pSequenceID)
			throws StoreSequenceException;

	/**
	 * 
	 * @param pSequenceID
	 *            序号ID
	 * @return
	 * @throws StoreSequenceException
	 */
	public long load(final String pSequenceID) throws StoreSequenceException;
}
