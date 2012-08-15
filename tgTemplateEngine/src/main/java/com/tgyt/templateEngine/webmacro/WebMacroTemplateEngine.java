/**   
  * @Title: WebMacroTemplateEngine.java 
  * @Package com.tgyt.templateEngine.webmacro 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:26:40 
  * @version V1.0   
  */

package com.tgyt.templateEngine.webmacro;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.webmacro.PropertyException;
import org.webmacro.ResourceException;
import org.webmacro.WM;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.MergeTemplateException;
import com.tgyt.templateEngine.Template;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.DefaultTemplate;
import com.tgyt.templateEngine.support.TemplateEngineSupport;

/** 
 * @ClassName: WebMacroTemplateEngine 
 * @Description: webmacro模板引擎
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:26:40 
 *  
 */
public class WebMacroTemplateEngine extends TemplateEngineSupport {
	private final Log log = LogFactory.getLog( this.getClass() );
	
	private Properties properties = null;

	public void init(){
		properties = WebMacroHelper.getDefaultProperties();
		
		
	}
	
	public void init(Properties pProperties){
        this.properties = pProperties; 		
	}
	
	/**
	 * 
	 */
	protected void mergeFileTemplate(Template pTemplate, Context pContext,
			Writer pWriter) throws MergeTemplateException {
		
		Properties props = WebMacroHelper.getDefaultProperties();
		props.putAll(this.properties);
		
	    props.setProperty("TemplateEncoding", pTemplate.getInputEncoding());
		String path = pTemplate.getResource();
		if ( log.isDebugEnabled() ){
	          log.debug("模板文件: \"" + path + "\" 采用WebMacro引擎进行合并.");
	          log.debug("模板文件: \"" + path + "\" 输入编码方式是：" + pTemplate.getInputEncoding());
			}

		/**
		 * @FIXME
		 * 由于WebMacro没有提供运行时，设置模板编码方式，所以要求每次实例化Engine,这样性能有些影响，希望以后版本
		 * WebMacro会提供该方法。不过好在，是单机版本的，性能问题不大.
		 */
		WM wm = WebMacroHelper.getWMEngine(props);
		
		org.webmacro.Context c = new org.webmacro.Context(wm.getBroker());
		Map params = pContext.getParameters();
		for (Iterator i = params.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			Object value = params.get(key);
			c.put(key, value);
		}

		org.webmacro.Template t = null;
		try {
			t = wm.getTemplate(pTemplate.getResource());
		} catch (ResourceException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}

		String result = null;
		try {
//			result = t.evaluateAsString(c);
//		} catch (PropertyException e) {
		}catch(Exception e){
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}

		try {
			pWriter.write(result);
		} catch (IOException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
	}

	public static void main(String[] args) {
		WebMacroTemplateEngine a = new WebMacroTemplateEngine();
		Template t = new DefaultTemplate();
		t.setResource("noservlet.wm");
		Context c = new DefaultContext();
		c.put("msg", "hello中文哦.");
		StringWriter writer = new StringWriter();
		a.mergeTemplate(t, c, writer);
		System.out.println(writer.toString());
	}
}
