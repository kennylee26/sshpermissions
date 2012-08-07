/**   
  * @Title: ArticleDao.java 
  * @Package com.tgyt.lucene.dao 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-8-6 下午2:05:59 
  * @version V1.0   
  */

package com.tgyt.lucene.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.lucene.model.TArticle;
import com.tgyt.lucene.util.Indexer;

/** 
 * @ClassName: ArticleDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2012-8-6 下午2:05:59 
 *  
 */
@Repository(value="tarticleDao")
public class TArticleDao extends BaseDAO<TArticle>{
	
		
	/** 
	  * @Title: getDocument 
	  * @Description: 为Document添加内容
	  * @param @return
	  * @return List<Document>
	  * @throws 
	  */
	public List<Document> getDocument() {
		// TODO Auto-generated method stub
		List<Document> documentList = new ArrayList<Document>();
		List<TArticle> tArticlesList =  this.findList("from TArticle");
		for(TArticle tArticle : tArticlesList){
			Document document = new Document();
			if(tArticle.getArticleTitle() != null){
				// 文件名,可查询,分词,存储到索引库记录中
				document.add(new Field("title",tArticle.getArticleTitle(), Store.YES,
						Index.ANALYZED));
			}else if(tArticle.getId().toString() != null){
				
				// 文件路径,可查询,不分词,存储到索引库记录中
				document.add(new Field("id", tArticle.getId().toString(), Store.YES,
						Index.NOT_ANALYZED));
			}else if(tArticle.getArticleContent() != null){
				// 大文本内容,可查询,不存储,实际上可根据文件路径去找到真正的文本内容
				// document.add(new Field("content",new FileReader(file)));
				
				// 小文本内容，可以存储到索引记录库
				document.add(new Field("content", tArticle.getArticleContent(), Store.YES,
						Index.ANALYZED));
			documentList.add(document);
			}else{
				continue;
			}
		}
		return documentList;
	}
}