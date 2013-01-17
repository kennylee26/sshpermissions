package com.tgyt.flowList.biz;

import java.util.List;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.flowList.model.TypeFlow;
import com.tgyt.framework.service.ServiceInterface;

/** 
  * @ClassName: ITypeFlowService 
  * @Description: 工作流类型中间表接口
  * @author haoly  454688562@qq.com
  * @date 2012-4-17 上午10:50:02 
  *  
  */
public interface ITypeFlowService extends ServiceInterface<TypeFlow> {
	
	static final String OPEN_STATE="启用";
	static final String CLOSE_STATE="禁用";

	public Pagination getFlowList(TypeFlow typeFlow, Integer page, Integer rows,
			String sort, String order, Object object);
	
	public void getNode(int id);

	public List getFlowList(TypeFlow typeFlow, int id);
}
