package com.tgyt.flowList.biz;

import java.util.List;
import java.util.Map;

import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.framework.service.ServiceInterface;

/** 
  * @ClassName: ICFlowTypeService 
  * @Description: 工作流类型接口
  * @author haoly  454688562@qq.com
  * @date 2012-4-17 上午10:49:42 
  *  
  */
public interface ICFlowTypeService extends ServiceInterface<CFlowtype> {

	/** 
	  * @Title: getTree 
	  * @Description: 获得类型树
	  * @param @return    设定文件 
	  * @return List<Map<String,Object>>    返回类型 
	  * @throws 
	  */
	public List<Map<String,Object>> getTree();
	public int getMaxSort();
	public Boolean deleteType(int id);
}
