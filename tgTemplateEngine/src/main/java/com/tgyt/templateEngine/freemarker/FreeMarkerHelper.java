/**   
  * @Title: FreeMarkerHelper.java 
  * @Package com.tgyt.templateEngine.freemarker 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:03:06 
  * @version V1.0   
  */

package com.tgyt.templateEngine.freemarker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/** 
 * @ClassName: FreeMarkerHelper 
 * @Description: freemarker帮助类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:03:06 
 *  
 */
public class FreeMarkerHelper {
private static Log log = LogFactory.getLog(FreeMarkerHelper.class);
	
    private static Configuration cfg = null;
    static{
		cfg = new Configuration();
		try {
			TemplateLoader ct = new ClasspathTemplateLoader();
			TemplateLoader[] loaders = new TemplateLoader[] { ct };
			MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
			cfg.setTemplateLoader(mtl);
		} catch (Exception e) {
            final String MSG = 
            	"初始化FreeMarker引擎失败." + e.getMessage();
            if ( log.isErrorEnabled() ){
            	log.error(MSG);
            }   
            throw new InitFreeMarkerEngineException(MSG, e);
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());
    }
	/**
	 * 为了避免Configuration跟其他项目用到的Configuration产生冲突，所以
	 * 这里使用多实例引擎，而不是singleton引擎.
	 * @return
	 */
	public static Configuration  getConfiguration() {
		return cfg;
	}
}
