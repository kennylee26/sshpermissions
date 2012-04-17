package com.tgyt.flowList.biz;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tgyt.flowList.dao.TypeFlowDao;
import com.tgyt.flowList.model.TypeFlow;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;

public class TypeFlowService extends BaseService<TypeFlow> implements ITypeFlowService {
     @Resource(name="typeFlowDao")
	private TypeFlowDao typeFlowDao;
     
	@Override
	protected DAOInterface<TypeFlow> getDAO() {
		// TODO Auto-generated method stub
		return this.typeFlowDao;
	}

}
