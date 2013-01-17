package com.tgyt.permissions.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgyt.common.tools.page.Pagination;
import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.permissions.dao.DictionaryDao;
import com.tgyt.permissions.model.Dictionarys;

@Service
@Transactional
public class DictionaryService extends BaseService<Dictionarys> implements IDictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;
	public Pagination getPageList(Dictionarys dic, int page, int rows,String sort,String order) {
		return dictionaryDao.getPageList(dic, page, rows,sort,order);
	}

	@Override
	protected DAOInterface<Dictionarys> getDAO() {
		return dictionaryDao;
	}

	public DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}
	
	public List<Dictionarys> getAllDictionary() {
		return dictionaryDao.getAllDictionary();
	}
	
	public String findMax(String nickName) {
		return dictionaryDao.findMax(nickName);
	}
 
}
