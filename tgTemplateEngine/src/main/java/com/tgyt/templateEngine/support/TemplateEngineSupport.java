/**   
  * @Title: TemplateEngineSupport.java 
  * @Package com.tgyt.templateEngine.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:18:02 
  * @version V1.0   
  */

package com.tgyt.templateEngine.support;

import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.MergeTemplateException;
import com.tgyt.templateEngine.Template;
import com.tgyt.templateEngine.TemplateEngine;

/** 
 * @ClassName: TemplateEngineSupport 
 * @Description: 模板帮助类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:18:02 
 *  
 */
public abstract class TemplateEngineSupport implements TemplateEngine{
	private final Log log = LogFactory.getLog( TemplateEngineSupport.class );
	
	
	protected final String getStrTemplatePrefix(){
		return DefaultTemplate.STR_PREFIX;
	}
	
	protected final String getStrTemplate(Template pTemplate){
		final String resource = pTemplate.getResource();
		if ( resource == null ){
			return null;
		}
		if ( resource.startsWith( DefaultTemplate.STR_PREFIX ) == false){
			return null;
		}
	    int index = resource.indexOf(":");
	    final String noPrefixResource = resource.substring(index+1);
		return noPrefixResource;
	}
	public void mergeTemplate(Template pTemplate,
            Context pContext,
            Writer pWriter) throws MergeTemplateException{
		final String resource = pTemplate.getResource();
		String property = pTemplate.getResource();
		if ( property == null ){
			throw new MergeTemplateException("resource must be not null");
		}
		if (resource.startsWith(DefaultTemplate.STR_PREFIX) == false){
            final String FILE_PATH = resource;
			if ( FILE_PATH== null ){
			  final String MSG =
				  "模板文件不能为空.";
			  if ( log.isErrorEnabled() ){
                log.error(MSG);				
			  }
			  throw new MergeTemplateException(MSG);
	       }
	 		mergeFileTemplate(pTemplate, pContext, pWriter);
			if ( log.isInfoEnabled() ){
				log.info("合并模板文件成功：\"" + FILE_PATH + "\"");
			}
		}else{
//			final String TEMPLATE_STR = getStrTemplate(pTemplate);
//			//合并字符串日志级别设置为debug,因为主要是fcg内部使用的，没必要呈现给终端用户.
//			if ( log.isDebugEnabled() ){
//				log.debug("开始合并字符串 \""+ TEMPLATE_STR + "\"");
//			}
			mergeStringTemplate(pTemplate, pContext, pWriter);
//			if ( log.isDebugEnabled() ){
//				log.debug("合并字符串 \"" + TEMPLATE_STR + "\" 成功!");
//			}
			
		}
		
	}
	
	protected abstract void mergeFileTemplate(Template pTemplate,
            Context pContext,
            Writer pWriter) throws MergeTemplateException;
	
	protected void mergeStringTemplate(Template pTemplate,
			Context pContext,
            Writer pWriter) throws MergeTemplateException{
		throw new UnsupportedOperationException(getClass() + ":mergeStringTemplate方法" );
		
	}

}