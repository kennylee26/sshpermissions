/**   
  * @Title: VelocityHelper.java 
  * @Package com.tgyt.templateEngine.velocity 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:22:18 
  * @version V1.0   
  */

package com.tgyt.templateEngine.velocity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.tgyt.templateEngine.Context;

/** 
 * @ClassName: VelocityHelper 
 * @Description: viocity模板帮助类
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:22:18 
 *  
 */
public class VelocityHelper {

	private static Log log = LogFactory.getLog(VelocityHelper.class);

	/**
	 * 为了避免VelocityEngine跟其他项目用到的VelocityEngine产生冲突，所以
	 * 这里使用多实例引擎，而不是singleton引擎.
	 * @return
	 */
	public static VelocityEngine getVeocityEngine(Properties pProperties) throws InitVelocityEngineException{
		VelocityEngine ve = new VelocityEngine();
		try {
				ve.init( pProperties );				
		} catch (Exception e) {
			e.printStackTrace();
			final String MSG =
				"初始化Velocity引擎失败!" + e.getMessage();
			if ( log.isErrorEnabled() ){
			  log.error(MSG);
			}
			throw new InitVelocityEngineException(MSG, e);
		}
		return ve;
	}

	public static Properties getDefaultProperties()throws InitVelocityEngineException {
   	   InputStream is = VelocityHelper.class
				.getResourceAsStream("Velocity.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			final String MSG =
				"导入属性文件出错!" + e.getMessage();
			if ( log.isErrorEnabled()){
			  log.error("导入属性文件出错!" + e.getMessage());
			}
			throw new InitVelocityEngineException(MSG, e);
		}
		return props;
	}
	
	public static VelocityContext context2VelocityContext(Context pContext){
		if ( pContext == null ){
			return null;
		}
		VelocityContext result = new VelocityContext();
		Map params = pContext.getParameters();
		for(Iterator i= params.keySet().iterator(); i.hasNext();){
			String key = (String)i.next();
			Object value = params.get(key);
			result.put(key, value);
		}
	  return result;	
	}
		
}
