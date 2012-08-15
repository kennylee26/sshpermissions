/**   
  * @Title: WebMacroHelper.java 
  * @Package com.tgyt.templateEngine.webmacro 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:25:47 
  * @version V1.0   
  */

package com.tgyt.templateEngine.webmacro;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.webmacro.Broker;
import org.webmacro.InitException;
import org.webmacro.WM;

import com.tgyt.templateEngine.velocity.VelocityHelper;

/** 
 * @ClassName: WebMacroHelper 
 * @Description: macro模板帮助类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:25:47 
 *  
 */
public class WebMacroHelper {
	private static Log log = LogFactory.getLog(VelocityHelper.class);

	public static WM getWMEngine(Properties pProps) throws InitWebMacroEngineException {
		Broker broker = null;
		try {
			broker =   Broker.getBroker(pProps.toString());
		} catch (InitException e) {
			e.printStackTrace();
			final String MSG = 
				"初始化WebMacro引擎失败!" + e.getMessage();
			if ( log.isErrorEnabled() ){
				log.error(MSG, e);
			}
			throw new InitWebMacroEngineException(MSG, e);
		}
		WM wm = null;
		try {
//			wm = new WM( broker );			
//		} catch (InitException e) {
		}catch (Exception e){
			e.printStackTrace();
			final String MSG = 
				"初始化WebMacro引擎失败!" + e.getMessage();
			if ( log.isErrorEnabled() ){
				log.error(MSG, e);
			}
			throw new InitWebMacroEngineException(MSG, e);
		}   
		return wm;
	}  
	

	public static Properties getDefaultProperties() throws InitWebMacroEngineException {
		InputStream is = WebMacroHelper.class
				.getResourceAsStream("WebMacro.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			final String MSG = "导入属性文件出错!" + e.getMessage();
			if ( log.isErrorEnabled() ){
			  log.error(MSG, e);
			}
			throw new InitWebMacroEngineException(MSG, e);
		}
		return props;
	}
}
