/**   
  * @Title: AssignmentHandlerFactory.java 
  * @Package com.tgyt.tgbpm.tools.factory 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2012-1-13 上午10:36:53 
  * @version V1.0   
  */

package com.tgyt.tgbpm.tools.factory;

import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tgyt.tgbpm.tools.modules.IAssignmentHandler;

/** 
 * @ClassName: AssignmentHandlerFactory 
 * @Description:工作流Task人员分配handler工厂类
 * @author sunct sunchaotong18@163.com 
 * @date 2012-1-13 上午10:36:53 
 *  
 */
@Service
public class AssignmentHandlerFactory {

	/** 
	  * @Title: getAssignmentHandlerInstance 
	  * @Description:根据设置的工单处理人分配方案，返回相应的处理类实例
	  * @param @return
	  * @param @throws Exception
	  * @return IAssignmentHandler
	  * @throws 
	  */
	public  IAssignmentHandler getAssignmentHandlerInstance()throws Exception{
		//根据设置方案中相应处理类的包字符串，通过反射获得相应的Class
		Class type = Class.forName("com.tgyt.tgbpm.tools.modules.impl.CandidateAssignmentHandlerImpl");
		//调用jbpm4.4，获取spring管理的bean
		IAssignmentHandler handler = (IAssignmentHandler)EnvironmentImpl.getFromCurrent(type);
		return handler;
	}
}
