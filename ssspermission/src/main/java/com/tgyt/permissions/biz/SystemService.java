package com.tgyt.permissions.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.SystemDao;
import com.tgyt.permissions.model.Systems;

@Service
@Transactional
public class SystemService extends BaseService<Systems> implements ISystemService {

	@Autowired
	private SystemDao systemDao;
	
	public Pagination getPageList(Systems sys,int page, int rows,String sort,String order) {
		return systemDao.getPageList(sys,page, rows,sort,order);
	}
	public List<Systems> getAll(){
		return systemDao.getAll();
	}

	@Override
	protected DAOInterface<Systems> getDAO() {
		return systemDao;
	}
}
