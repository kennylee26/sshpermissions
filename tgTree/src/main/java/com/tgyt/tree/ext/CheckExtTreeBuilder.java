/**   
  * @Title: CheckExtTreeBuilder.java 
  * @Package com.tgyt.tree.ext 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:01:43 
  * @version V1.0   
  */

package com.tgyt.tree.ext;

import java.io.StringWriter;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.TemplateEngine;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.DefaultTemplate;
import com.tgyt.templateEngine.support.TemplateEngineFactory;
import com.tgyt.templateEngine.support.TemplateType;
import com.tgyt.tree.support.Constants;

/** 
 * @ClassName: CheckExtTreeBuilder 
 * @Description: check ext tree builder
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:01:43 
 *  
 */
public class CheckExtTreeBuilder extends ExtTreeBuilder{
		
 	
	{
		this.setTreeType( Constants.CHECK_TREE_TYPE );
	}


	
	public static void main(String[] args){
		DefaultTemplate template = new DefaultTemplate();
		template.setInputEncoding("utf-8");
		template.setResource("com/tgyt/tree/ext/RootNode.vm");		
		Context context = new DefaultContext();
		TemplateEngine engine = TemplateEngineFactory.getInstance(TemplateType.VELOCITY);
		StringWriter writer = new StringWriter();
		engine.mergeTemplate(template, context, writer);
		System.out.println( writer.toString());
		

	}

	


}
