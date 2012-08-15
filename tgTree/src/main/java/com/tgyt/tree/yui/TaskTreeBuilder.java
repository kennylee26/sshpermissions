/**   
  * @Title: TaskTreeBuilder.java 
  * @Package com.tgyt.tree.yui 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:15:07 
  * @version V1.0   
  */

package com.tgyt.tree.yui;

import java.io.StringWriter;

import com.tgyt.templateEngine.Template;
import com.tgyt.templateEngine.TemplateEngine;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.DefaultTemplate;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.templateEngine.support.TemplateEngineFactory;
import com.tgyt.templateEngine.support.TemplateType;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: TaskTreeBuilder 
 * @Description: 任务树构建
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:15:07 
 *  
 */
public class TaskTreeBuilder extends AbstractWebTreeBuilder{ 

	private String treeName = null;
	private String initMethod = "initMethodName";
	/**
	 * 开始构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException{

		String divId = "test";
		treeName = "tree";
		loadResource();		
		StringBuffer treeTemplate = new StringBuffer().append(ENTER);
		treeTemplate.append("<div id=\"${divId}\"></div>").append(ENTER);
		treeTemplate.append("<script>").append(ENTER);
		treeTemplate.append("function ${initMethodName}() {").append(ENTER); 
		treeTemplate.append("var ${treeName} = new YAHOO.widget.TreeView(\"${divId}\");").append(ENTER);

		DefaultContext context = new DefaultContext(); 
		context.put("divId", divId);
		context.put("initMethodName", initMethod);
		context.put("treeName", treeName );
		treeScript.append(StrTemplateUtil.merge(treeTemplate.toString(), context));		
	}
	
	protected void loadResource() throws BuildTreeException {
		Template template = new DefaultTemplate();
		template.setInputEncoding("utf-8");
		template.setResource("net/jcreate/e3/tree/yahoo/TaskTreeResource.vm");
		TemplateEngine engine = TemplateEngineFactory.getInstance(TemplateType.VELOCITY);
		StringWriter writer = new StringWriter();
		DefaultContext context = new DefaultContext();
		context.put("treeName", this.treeName);
		engine.mergeTemplate(template, context, writer);
		this.treeScript.append(writer.toString());
	}
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}
		if ( pParentNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("parentNode type is error, should be WebTreeNode type");
		}
		
		WebTreeNode node = (WebTreeNode)pNode;
		WebTreeNode parentNode = (WebTreeNode)pParentNode;

		StringBuffer rootNodeTemplate = new StringBuffer();
		rootNodeTemplate.append("var ${nodeScriptName} = new YAHOO.widget.TaskNode(\"${text}\", ${parentScriptName}, false); ").append(ENTER);
		DefaultContext context = new DefaultContext();
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("parentScriptName", getNodeScriptName(parentNode));
		context.put("text", node.getName());
		context.put("treeName", treeName);
		treeScript.append(StrTemplateUtil.merge(rootNodeTemplate.toString(), context));		
	}
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}
		WebTreeNode node = (WebTreeNode)pRootNode;

		StringBuffer rootNodeTemplate = new StringBuffer();
		rootNodeTemplate.append("var ${nodeScriptName} = new YAHOO.widget.TaskNode(\"${text}\", ${treeName}.getRoot(), false); ").append(ENTER);
		DefaultContext context = new DefaultContext();
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("text", node.getName());
		context.put("treeName", treeName);
		treeScript.append(StrTemplateUtil.merge(rootNodeTemplate.toString(), context));		
	}
	
	public void buildTreeEnd() throws BuildTreeException{
		StringBuffer treeTemplate = new StringBuffer();		
	    treeTemplate.append("${treeName}.draw();").append(ENTER);
	    treeTemplate.append("}").append(ENTER);//初始化结束
	    treeTemplate.append("if ( window.attachEvent ) ").append(ENTER);
	    treeTemplate.append("window.attachEvent('onload',${initMethodName});").append(ENTER);
	    treeTemplate.append("if ( window.addEventListener ) ").append(ENTER);
	    treeTemplate.append("window.addEventListener('load',${initMethodName}, false);").append(ENTER);
	    
	    treeTemplate.append("</script>").append(ENTER);	    
	    DefaultContext context = new DefaultContext(); 
		context.put("treeName", treeName );
		context.put("initMethodName", this.initMethod );
		treeScript.append(StrTemplateUtil.merge(treeTemplate.toString(), context));	    
	}

}
