/**   
  * @Title: TgCoreBaseDao.java 
  * @Package com.tgyt.framework.dao.hibernate 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-5 上午11:00:14 
  * @version V1.0   
  */

package com.tgyt.framework.dao.hibernate;

import java.io.Serializable;

/** 
 * @ClassName: TgCoreBaseDao 
 * @Description: 核心basedao包，继承自basedao
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-5 上午11:00:14 
 *  
 */
public interface TgCoreBaseDao<T extends Serializable> extends BaseDao<T>{

}
