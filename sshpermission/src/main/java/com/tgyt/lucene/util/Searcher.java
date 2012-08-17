/**
 * 
 */
package com.tgyt.lucene.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.NumericUtils;
import org.apache.lucene.util.Version;

/** 
  * @ClassName: Searcher 
  * @Description: 搜索 
  * @author sunqiang sunqiangbingxue@sina.com
  * @date 2012-8-10 下午1:44:31 
  *  
  */
public class Searcher {

	/** 
	  * @Title: search 
	  * @Description: 搜索 
	  * @param @param keyWord
	  * @param @param indexDirPath
	  * @param @return
	  * @param @throws ParseException
	  * @param @throws IOException
	  * @return List<Document>
	  * @throws 
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
		File file = new File(indexDirPath);
		String pathAll = file.getParentFile().getParentFile().toString()+"\\index";
		// 打开索引目录
		File indexDir = new File(pathAll);
		Directory directory = FSDirectory.open(indexDir);
		// 获取访问索引的接口,进行搜索
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// TopDocs 搜索返回的结果
		TopDocs topDocs = indexSearcher.search(query, 1000000);// 只返回前100条记录
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
	  * @Title: paginationQuery 
	  * @Description: 分页显示
	  * @param @param keyWord
	  * @param @param pageSize
	  * @param @param currentPage
	  * @param @param indexDirPath
	  * @param @return
	  * @param @throws ParseException
	  * @param @throws CorruptIndexException
	  * @param @throws IOException
	  * @return List<Document>
	  * @throws 
	  */
	public static List<Document> paginationQuery(String keyWord,int pageSize,int currentPage, String indexDirPath) throws ParseException, CorruptIndexException, IOException {

		String[] fields = { "title", "content","id" };
		// 创建一个分词器,和创建索引时用的分词器要一致
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		// 创建查询解析器
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
				fields, analyzer);
		// 将查询关键词解析成Lucene的Query对象
		Query query = queryParser.parse(keyWord);
		File file = new File(indexDirPath);
		String pathAll = file.getParentFile().getParentFile().toString()+"\\index";
		// 打开索引目录
		File indexDir = new File(pathAll);
		Directory directory = FSDirectory.open(indexDir);
		// 获取访问索引的接口,进行搜索
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// TopDocs 搜索返回的结果
		
		TopDocs topDocs = indexSearcher.search(query, 1000000);// 只返回前100条记录
		int totalCount = topDocs.totalHits; // 搜索结果总数量
		System.out.println("搜索到的结果总数量为：" + totalCount);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 搜索返回的结果集合

         

        //查询起始记录位置

        int begin = pageSize * (currentPage - 1) ;

        //查询终止记录位置

        int end = Math.min(begin + pageSize, scoreDocs.length);
        List<Document> docs = new ArrayList<Document>();

        //进行分页查询

        for(int i=begin;i<end;i++) {

            int docID = scoreDocs[i].doc;

            Document doc = indexSearcher.doc(docID);
         // 文档编号
         	docs.add(doc);

            /*int id = NumericUtils.prefixCodedToInt(doc.get("id"));

            String title = doc.get("title");

            System.out.println("id is : "+id);

            System.out.println("title is : "+title);*/

        }
        indexReader.close();
		indexSearcher.close();
		return docs;
         

    }
	/** 
	  * @Title: searchHigh 
	  * @Description: 搜索高量 
	  * @param @param queryKeyWord
	  * @param @param indexDirPath
	  * @param @return
	  * @param @throws ParseException
	  * @param @throws CorruptIndexException
	  * @param @throws IOException
	  * @param @throws InvalidTokenOffsetsException
	  * @return List
	  * @throws 
	  */
	public static List searchHigh(String queryKeyWord,int pageSize,int currentPage,String indexDirPath) throws ParseException, CorruptIndexException, IOException, InvalidTokenOffsetsException {
        //搜索的关键词
         List list = new ArrayList();
        //创建查询分析器,把查询关键词转化为查询对象Query(单个Field中搜索)
        //QueryParser queryParser = new QueryParser(Version.LUCENE_36,"author",analyzer);//在作者的索引中搜索
	 Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
         
	 	String[] fields = { "title", "content","id" };
        //(在多个Filed中搜索)
        QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,fields,analyzer);
        Query query = queryParser.parse(queryKeyWord);
        File file = new File(indexDirPath);
		String pathAll = file.getParentFile().getParentFile().toString()+"\\index";
        //获取访问索引的接口,进行搜索
        File indexDir = new File(pathAll);
		Directory directory = FSDirectory.open(indexDir);
		// 获取访问索引的接口,进行搜索
		IndexReader indexReader = IndexReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
         
        //TopDocs 搜索返回的结果
        TopDocs topDocs = indexSearcher.search(query, 1000000000);//只返回前100条记录
         
        int totalCount = topDocs.totalHits; // 搜索结果总数量
        System.out.println("搜索到的结果总数量为：" + totalCount);
         
        ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 搜索的结果列表
         
        //创建高亮器,使搜索的关键词突出显示
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
        Scorer fragmentScore = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter,fragmentScore);
        Fragmenter fragmenter = new SimpleFragmenter(100);
        highlighter.setTextFragmenter(fragmenter);
      //查询起始记录位置

        int begin = pageSize * (currentPage - 1) ;

        //查询终止记录位置

        int end = Math.min(begin + pageSize, scoreDocs.length);
        //List<Book> books = new ArrayList<Book>();
        Map<String, String> strMap = new HashMap<String, String>();
        //把搜索结果取出放入到集合中
        for(int i=begin;i<end;i++) {

            int docID = scoreDocs[i].doc;
            Document document = indexSearcher.doc(docID);
            strMap.put("id",document.get("id"));

            String titleToBeHightlight = document.get("title");
            if (titleToBeHightlight == null)
                titleToBeHightlight = "";
            TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(titleToBeHightlight));
            String title = highlighter.getBestFragment(tokenStream, titleToBeHightlight);
            strMap.put("title",title);
            String contentToBeHightlight = document.get("content");
            if (contentToBeHightlight == null)
            	contentToBeHightlight = "";
            TokenStream tokenStream1 = analyzer.tokenStream("content", new StringReader(contentToBeHightlight));
            String content = highlighter.getBestFragment(tokenStream1, contentToBeHightlight);
            strMap.put("content",content);
            list.add(strMap);
        }
        //关闭
        indexReader.close();
        indexSearcher.close();
        return list;
    }
	/** 
	  * @Title: getCount 
	  * @Description: 获得数据总数
	  * @param @param keyWord
	  * @param @param pageSize
	  * @param @param currentPage
	  * @param @param indexDirPath
	  * @param @return
	  * @param @throws ParseException
	  * @param @throws IOException
	  * @return int
	  * @throws 
	  */
	public static int getCount(String keyWord, String indexDirPath)throws ParseException, IOException{

			String[] fields = { "title", "content","id" };
			// 创建一个分词器,和创建索引时用的分词器要一致
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
			// 创建查询解析器
			QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
					fields, analyzer);
			// 将查询关键词解析成Lucene的Query对象
			Query query = queryParser.parse(keyWord);
			 File file = new File(indexDirPath);
			String pathAll = file.getParentFile().getParentFile().toString()+"\\index";
			// 打开索引目录
			File indexDir = new File(pathAll);
			Directory directory = FSDirectory.open(indexDir);
			// 获取访问索引的接口,进行搜索
			IndexReader indexReader = IndexReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			// TopDocs 搜索返回的结果
			TopDocs topDocs = indexSearcher.search(query, 100);// 只返回前100条记录
			int totalCount = topDocs.totalHits;
			return totalCount;
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
