///**   
//  * @Title: JxpTemplateEngine.java 
//  * @Package com.tgyt.templateEngine.jxp 
//  * @Description: 
//  * @author zhangfeng 13940488705@163.com 
//  * @date 2011-8-9 下午03:10:24 
//  * @version V1.0   
//  */
//
//package com.tgyt.templateEngine.jxp;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.io.UnsupportedEncodingException;
//import java.io.Writer;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.onemind.jxp.ByteArrayPageSource;
//import org.onemind.jxp.JxpContext;
//import org.onemind.jxp.JxpProcessor;
//import org.onemind.jxp.MultiSourcePageSource;
//import org.onemind.jxp.ResourceStreamPageSource;
//
//import com.tgyt.templateEngine.Context;
//import com.tgyt.templateEngine.MergeTemplateException;
//import com.tgyt.templateEngine.Template;
//import com.tgyt.templateEngine.support.DefaultContext;
//import com.tgyt.templateEngine.support.DefaultTemplate;
//import com.tgyt.templateEngine.support.TemplateEngineSupport;
//
///** 
// * @ClassName: JxpTemplateEngine 
// * @Description: jxp模板引擎
// * @author zhangfeng 13940488705@163.com
// * @date 2011-8-9 下午03:10:24 
// *  
// */
//public class JxpTemplateEngine  extends TemplateEngineSupport {
//	private final Log log = LogFactory.getLog( this.getClass());
//	
//	
//	public JxpTemplateEngine(){
//	}
//	public void mergeFileTemplate(Template pTemplate, Context pContext,
//			Writer pWriter) throws MergeTemplateException {
//		String encoding = pTemplate.getInputEncoding();		
//		 MultiSourcePageSource multiSource = new MultiSourcePageSource();
//		 ResourceStreamPageSource classPathSource = new ResourceStreamPageSource("");
//		 
////		 FilePageSource pageSource = new FilePageSource("./templates", encoding);
////		multiSource.addPageSource(pageSource);
//		multiSource.addPageSource(classPathSource);
//		 JxpContext context = new JxpContext(multiSource);
//		 JxpProcessor processor = new JxpProcessor(context);
//
//		String path = pTemplate.getResource();
//		if ( log.isDebugEnabled() ){
//	          log.debug("模板文件: \"" + path + "\" 采用 Jxp 引擎进行合并.");
//	          log.debug("模板文件: \"" + path + "\" 输入编码方式是：" + pTemplate.getInputEncoding());
//		}
//		if ( pContext == null ){
//			pContext = new DefaultContext();
//		}
//		
//		Map params = pContext.getParameters();
//		try {
//			processor.process(path, pWriter, params);
//		} catch (Exception e) {
// 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
// 			if ( log.isErrorEnabled()){
//			  log.error(MSG, e);
// 			}
//			throw new MergeTemplateException(MSG, e);
//    	}
//		
//		try {
//			pWriter.flush();
//		} catch (IOException e) {
// 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
// 			if ( log.isErrorEnabled()){
//			  log.error(MSG, e);
// 			}
//			throw new MergeTemplateException(MSG, e);
//		}
//		
//		
//	}
//	private final String STR_PATH_ID = "JxpTemplateEngine_fcg_path_id_huangyh_not_should_exist_same";
//	protected void mergeStringTemplate(Template pTemplate,
//			Context pContext,
//            Writer pWriter) throws MergeTemplateException{
//		String encoding = pTemplate.getInputEncoding();	
//		 ByteArrayPageSource byteArrayPageSource = new ByteArrayPageSource();
//		 JxpContext context = new JxpContext(byteArrayPageSource);
//		 JxpProcessor processor = new JxpProcessor(context);
//		
//		String templateStr = this.getStrTemplate(pTemplate);
//		try {
//			byteArrayPageSource.putPageBuffer(STR_PATH_ID, templateStr.getBytes(encoding));
//		} catch (UnsupportedEncodingException e) {
// 			final String MSG = "合并模板文件 \"" + templateStr + "\"  失败!" + e.getMessage();
// 			if ( log.isErrorEnabled()){
//			  log.error(MSG, e);
// 			}
//			throw new MergeTemplateException(MSG, e);
//		}
//		if ( pContext == null ){
//			pContext = new DefaultContext();
//		}
//		Map params = pContext.getParameters();
//		try {
//			processor.process(STR_PATH_ID, pWriter, params);
//		} catch (Exception e) {
// 			final String MSG = "合并模板文件 \"" + templateStr + "\"  失败!" + e.getMessage();
// 			if ( log.isErrorEnabled()){
//			  log.error(MSG, e);
// 			}
//			throw new MergeTemplateException(MSG, e);
//    	}
//		
//		try {
//			pWriter.flush();
//		} catch (IOException e) {
// 			final String MSG = "合并模板文件 \"" + templateStr + "\"  失败!" + e.getMessage();
// 			if ( log.isErrorEnabled()){
//			  log.error(MSG, e);
// 			}
//			throw new MergeTemplateException(MSG, e);
//		}
//		
//	}
//	
//	
//
//	public static void main(String[] args) {
//		JxpTemplateEngine a = new JxpTemplateEngine();
//		Template t = new DefaultTemplate();
//		t.setResource("test.jxp");
//		Context c = new DefaultContext();
//		c.put("msg", "hello");
//		c.put("msg2", "中文.");
//		StringWriter writer = new StringWriter();
//		a.mergeFileTemplate(t, c, writer);
//		System.out.println(writer.toString());
//	}
//}
