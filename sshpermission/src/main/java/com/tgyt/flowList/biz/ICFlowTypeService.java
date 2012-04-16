package com.tgyt.flowList.biz;

import java.util.List;
import java.util.Map;

import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.framework.service.ServiceInterface;

public interface ICFlowTypeService extends ServiceInterface<CFlowtype> {

	public List<Map<String,Object>> getTree();
}
