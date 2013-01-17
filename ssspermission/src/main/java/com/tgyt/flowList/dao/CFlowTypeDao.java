package com.tgyt.flowList.dao;

import org.springframework.stereotype.Repository;

import com.tgyt.flowList.model.CFlowtype;
import com.tgyt.framework.dao.hspring.BaseDAO;

@Repository(value="flowTypeDao")
public class CFlowTypeDao extends BaseDAO<CFlowtype> {

	
	public Integer getMaxSort() {
		// TODO Auto-generated method stub
		return (Integer) (this.getHandler().findObj("select max(list) from CFlowtype "));
	}
}
