/**   
  * @Title: DBSequenceStorer.java 
  * @Package com.tgyt.common.id.storer 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:29:45 
  * @version V1.0   
  */

package com.tgyt.common.id.storer;

import com.tgyt.common.id.SequenceStorer;
import com.tgyt.common.id.StoreSequenceException;

/** 
 * @ClassName: DBSequenceStorer 
 * @Description: 数据序列保存
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:29:45 
 *  
 */
public class DBSequenceStorer implements SequenceStorer{

	private javax.sql.DataSource dataSource;
	private String tableName;
	private String idColumnName;
	private String valueColumnName;
	public long load(String sequenceID) throws StoreSequenceException {
		return 0;
	}

	public void save(long sequence, String sequenceID)
			throws StoreSequenceException {
		
	}

	public javax.sql.DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdColumnName() {
		return idColumnName;
	}

	public void setIdColumnName(String idColumnName) {
		this.idColumnName = idColumnName;
	}

	public String getValueColumnName() {
		return valueColumnName;
	}

	public void setValueColumnName(String valueColumnName) {
		this.valueColumnName = valueColumnName;
	}

}
