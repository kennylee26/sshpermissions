/**   
  * @Title: ITgTaskUserService.java 
  * @Package com.tgyt.tgbpm.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-1-12 下午3:41:26 
  * @version V1.0   
  */

package com.tgyt.tgbpm.biz;

import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.tgbpm.model.TgBpmTaskUser;

/** 
 * @ClassName: ITgTaskUserService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-1-12 下午3:41:26 
 *  
 */
public interface ITgTaskUserService extends ServiceInterface<TgBpmTaskUser>{
	public TgBpmTaskUser findTask(String taskName);
	public TgBpmTaskUser findTask(String processId,String taskName);
	public void deleteByProcessKey(String processKey);
}
