/**
 * 
 */
package com.tgyt.lucene.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author zhangfeng
 * 
 */
public class Searcher {

	/**
	 * 搜索
	 * 
	 * @param keyWord
	 *            搜索的关键词
	 * @param indexDir
	 *            索引目录所在路径
	 * @throws ParseException
	 * @throws IOException
	 * @return List<Document>
	 */
	public static List<Document> search(String keyWord, String indexDirPath)
			throws ParseException, IOException {

		String[] fields = { "title", "content","id" };
		// 创建一个分词器,和创建索引时用的分词器要一致
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		// 创建查询解析器
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
				fields, analyzer);
		// 将查询关键词解析成Lucene的Query对象
		Query query = queryParser.parse(keyWord);
		// 打开索引目录
		File indexDir = new File(indexDirPath);
		Directory directory = FSDirectory.open(indexDir);
		// 获取访问索引的接口,进行搜索
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// TopDocs 搜索返回的结果
		TopDocs topDocs = indexSearcher.search(query, 100);// 只返回前100条记录
		int totalCount = topDocs.totalHits; // 搜索结果总数量
		System.out.println("搜索到的结果总数量为：" + totalCount);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 搜索的结果集合
		List<Document> docs = new ArrayList<Document>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			// 文档编号
			int docID = scoreDoc.doc;
			// 根据文档编号获取文档
			Document doc = indexSearcher.doc(docID);
			docs.add(doc);
		}
		indexReader.close();
		indexSearcher.close();
		return docs;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException, IOException {
		 //搜索关键词
        String keyWord = "lucene";
        //索引目录路径
        String indexDirPath = "c:\\index";
        Searcher searcher = new Searcher();
        //调用搜索器进行搜索
        List<Document> docs = searcher.search(keyWord, indexDirPath);
        for(Document doc : docs) {
            System.out.println("文件名 ： "+doc.get("title"));
          //  System.out.println("路径 ： "+doc.get("path"));
            System.out.println("内容 ： "+doc.get("content"));
        }

	}

}
