/**   
  * @Title: ITArticleService.java 
  * @Package com.tgyt.lucene.biz 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-8-7 上午9:50:07 
  * @version V1.0   
  */

package com.tgyt.lucene.biz;

import java.util.List;

import org.apache.lucene.document.Document;

import com.tgyt.framework.service.ServiceInterface;
import com.tgyt.lucene.model.TArticle;

/** 
 * @ClassName: ITArticleService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-8-7 上午9:50:07 
 *  
 */
public interface ITArticleService extends ServiceInterface<TArticle>{
	public void createIndex();
	public List<Document> searchIndex(String keyword,String indexDirPath);
	public List<Document> paginationQuery(String keyWord,int pageSize,int currentPage, String indexDirPath);
	public  int getCount(String keyWord, String indexDirPath);
}
