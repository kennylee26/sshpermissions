/**   
  * @Title: TemplateEngine.java 
  * @Package com.tgyt.templateEngine 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:58:35 
  * @version V1.0   
  */

package com.tgyt.templateEngine;

import java.io.Writer;

/** 
 * @ClassName: TemplateEngine 
 * @Description: 
 * 模板引擎，负责生成文件，生成内容，一Writer的形式输出.
 * 注意： 所有的TemplateEngine都应该支持根据ClassPath获取资源文件.
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:58:35 
 *  
 */
public interface TemplateEngine {
	public void mergeTemplate(Template pTemplate,
			                  Context pContext,
			                  Writer pWriter) throws MergeTemplateException;
}
