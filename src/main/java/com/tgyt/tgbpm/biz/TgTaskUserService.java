/**   
  * @Title: TgTaskUserService.java 
  * @Package com.tgyt.tgbpm.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-1-12 下午3:42:35 
  * @version V1.0   
  */

package com.tgyt.tgbpm.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.tgbpm.dao.TgTaskUserDao;
import com.tgyt.tgbpm.model.TgBpmTaskUser;

/** 
 * @ClassName: TgTaskUserService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-1-12 下午3:42:35 
 *  
 */
@Service
public class TgTaskUserService extends BaseService<TgBpmTaskUser> implements ITgTaskUserService{
	@Resource(name="tgTaskUserDao")
	private TgTaskUserDao tgTaskUserDao;

	
	@Override
	protected DAOInterface<TgBpmTaskUser> getDAO() {
		return tgTaskUserDao;
	}

	
	



	/* (non-Javadoc)
	 * <p>Title: findTask</p> 
	 * <p>Description: 根据任务名称查询</p> 
	 * @param taskName
	 * @return 
	 * @see com.tgyt.tgbpm.biz.ITgTaskUserService#findTask(java.lang.String) 
	 */
	public TgBpmTaskUser findTask(String taskName) {
		TgBpmTaskUser tgBpmTaskUser = tgTaskUserDao.find("from TgBpmTaskUser where taskName = " + taskName);
		return tgBpmTaskUser;
	}


	
	/* (non-Javadoc)
	 * <p>Title: findTask</p> 
	 * <p>Description:根据流程id和任务名称查询 </p> 
	 * @param processId
	 * @param taskName
	 * @return 
	 * @see com.tgyt.tgbpm.biz.ITgTaskUserService#findTask(java.lang.String, java.lang.String) 
	 */
	public TgBpmTaskUser findTask(String processId, String taskName) {
		TgBpmTaskUser tgBpmTaskUser = tgTaskUserDao.find("from TgBpmTaskUser where processId = '" + processId+"' and taskName = '" + taskName+"'");
		return tgBpmTaskUser;
	}



	
	
	/* (non-Javadoc)
	 * <p>Title: deleteByProcessKey</p> 
	 * <p>Description: 根据流程键删除</p> 
	 * @param processKey 
	 * @see com.tgyt.tgbpm.biz.ITgTaskUserService#deleteByProcessKey(java.lang.String) 
	 */
	public void deleteByProcessKey(String processKey) {
		// TODO Auto-generated method stub
		this.tgTaskUserDao.getHandler().executeSql("delete from tg_bpm_task_user where processId like '%" + processKey + "%'");
	}

}
