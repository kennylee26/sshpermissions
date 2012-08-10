/**   
  * @Title: TArticleService.java 
  * @Package com.tgyt.lucene.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-8-7 上午9:40:40 
  * @version V1.0   
  */

package com.tgyt.lucene.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.stereotype.Service;

import com.tgyt.framework.dao.hspring.DAOInterface;
import com.tgyt.framework.service.BaseService;
import com.tgyt.lucene.dao.TArticleDao;
import com.tgyt.lucene.model.TArticle;
import com.tgyt.lucene.util.Indexer;
import com.tgyt.lucene.util.Searcher;

/** 
 * @ClassName: TArticleService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-8-7 上午9:40:40 
 *  
 */
@Service
public class TArticleService extends BaseService<TArticle> implements ITArticleService{
	@Resource(name="tarticleDao")
	private TArticleDao tarticleDao;
	/* (non-Javadoc)
	 * <p>Title: getDAO</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.tgyt.framework.service.BaseService#getDAO() 
	 */
	protected DAOInterface<TArticle> getDAO() {
		return this.tarticleDao;
	}
	
	/* (non-Javadoc)
	 * <p>Title: createIndex</p> 
	 * <p>Description: </p>  
	 * @see com.tgyt.lucene.biz.ITArticleService#createIndex() 
	 */
	
	@Override
	public void createIndex() {
		// TODO Auto-generated method stub
		List<Document> list = tarticleDao.getDocument();
		try {
			Indexer.createIndex(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Document> searchIndex(String keyword,String indexDirPath) {
		// TODO Auto-generated method stub
		try {
			 List<Document> docs = Searcher.search(keyword, "c:\\index");
			 return docs;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: paginationQuery</p> 
	 * <p>Description: </p> 
	 * @param keyWord
	 * @param pageSize
	 * @param currentPage
	 * @param indexDirPath
	 * @return 
	 * @see com.tgyt.lucene.biz.ITArticleService#paginationQuery(java.lang.String, int, int, java.lang.String) 
	 */
	
	@Override
	public List<Document> paginationQuery(String keyWord, int pageSize,
			int currentPage, String indexDirPath) {
		// TODO Auto-generated method stub
		try {
			 List<Document> docs = Searcher.paginationQuery(keyWord, pageSize, currentPage, "c:\\index");
			 return docs;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: getCount</p> 
	 * <p>Description: </p> 
	 * @param keyWord
	 * @param indexDirPath
	 * @return 
	 * @see com.tgyt.lucene.biz.ITArticleService#getCount(java.lang.String, java.lang.String) 
	 */
	
	@Override
	public int getCount(String keyWord, String indexDirPath) {
		// TODO Auto-generated method stub
		try {
		 int totalcount = Searcher.getCount(keyWord, "c:\\index");
			return totalcount;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}
