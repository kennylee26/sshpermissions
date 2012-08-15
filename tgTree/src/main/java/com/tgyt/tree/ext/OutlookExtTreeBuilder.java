/**   
  * @Title: OutlookExtTreeBuilder.java 
  * @Package com.tgyt.tree.ext 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:35:11 
  * @version V1.0   
  */

package com.tgyt.tree.ext;

import java.util.HashMap;
import java.util.Map;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.WebTreeBuilder;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: OutlookExtTreeBuilder 
 * @Description: outlook 效果ext tree
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:35:11 
 *  
 */
public class OutlookExtTreeBuilder extends AbstractWebTreeBuilder {

	private String resourceHome;
	private String style;
	private boolean animate = true;
	/**
	 * 是否引入css,如果为false,则使用用户自己设置的css.
	 * 注意: css路径是带web context path的路径,如: /e3/e3/ext/ext-all.css,
	 * e3是 web context path
	 */
	private boolean importCss = true;
	/**
	 * 是否引入js,如果为false,则使用用户自己设置的js,注意:
	 * js路径,是带web context path的路径,如: /e3/e3/ext/ext.js
	 * e3是 web context path.
	 */
	private boolean importJs = true;
	
	private boolean createDiv = true; 	
	private boolean autoScroll = true;
	private String treeID = "tree";
	private String width =  null;
	private String height = null;
	private StringBuffer divBuffer = new StringBuffer();
	private StringBuffer subTreeScriptBuffer = new StringBuffer();
	private Map nodeBuilder = new HashMap();
	
	



	public void buildRootNodeEnd(Node rootNode, int level, int row)throws BuildTreeException {
 
		StringBuffer temp = new StringBuffer();
		temp.append("] };").append(ENTER);		
		temp.append("if ( typeof(${treeID}ConfigHandler) == 'function' )").append(ENTER);
		temp.append("  ${treeID}ConfigHandler(initConfig);").append(ENTER);
		temp.append("var ${treeID} = new Ext.Panel( initConfig );").append(ENTER);				
		//render 前
		temp.append("if ( typeof(${treeID}RenderBeforeHandler) == 'function' )").append(ENTER);
		temp.append("  ${treeID}RenderBeforeHandler(${treeID});").append(ENTER);		
		temp.append("${treeID}.render('${treeID}');").append(ENTER);		
		temp.append("if ( typeof(${treeID}RenderAfterHandler) == 'function' )").append(ENTER);
		temp.append("  ${treeID}RenderAfterHandler(${treeID});").append(ENTER);
		temp.append("});</script>").append(ENTER);
		
		Context context = new DefaultContext(); 
		context.put("treeID", this.getTreeID());
		treeScript.append(StrTemplateUtil.merge(temp.toString(), context));		   		

		treeScript.append(subTreeScriptBuffer.toString());

		
		java.util.Iterator builderIterator = nodeBuilder.values().iterator();
		while( builderIterator.hasNext() ){
			WebTreeBuilder builder = (WebTreeBuilder) builderIterator.next();
			String subtreeScript = builder.getTreeScript();
   		    treeScript.append(subtreeScript ); 
		}
		if ( createDiv ){
			//如果这个div样式不符合标准，可以设置createDiv为false,用户自己定义
			divBuffer.insert(0,"<div  style=\"overflow:auto;width:100%;height:100%\" id='" + treeID + "'  />");
		}
		   
		treeScript.insert(0,divBuffer.toString());
		
	
		
		
	}
	public String getStyle() {
		if ( style == null ){
			return getResourceHome() + "/resources/css/ext-all.css";
		}
		return style;
	}
	public String getResourceHome() {
		if ( resourceHome == null ){
			//contxtPath,当path为"/"时,有的服务器返回空,有的会返回"/",所以在这需要做
			//判断
			String contextPath = this.webContext.getContextPath();
			String basePath = "/tg/commons/ext"; 
			if ( "/".equals(contextPath) || "\\".equals(contextPath)){
				return basePath;
			}
			return  contextPath + basePath;
		} else {
			return resourceHome;
		}
	}
	

	
		/**
		 * 负责导入Tree所需要的js,css
		 */
	public void buildRootNodeStart(Node rootNode, int level, int row)
	throws BuildTreeException {
		if ( rootNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}

		WebTreeNode webTreeNode = (WebTreeNode)rootNode;
		
			StringBuffer resouces = new StringBuffer();
			if ( this.importCss ){
			   resouces.append("<link type='text/css' rel='stylesheet' href='${style}' />").append(ENTER);
			}
			if ( this.importJs ){
				resouces.append("<script><!--").append(ENTER);
			  resouces.append("if ( typeof(Ext) == \"undefined\" || typeof(Ext.DomHelper) == \"undefined\" ){").append(ENTER);			
			  resouces.append("document.write('<script src=\"${resouceHome}/adapter/ext/ext-base.js\"></script>');").append(ENTER);
			  resouces.append("document.write('<script src=\"${resouceHome}/ext-all.js\"></script>');").append(ENTER);		  
			  resouces.append("}").append(ENTER);
			  resouces.append("--></script>").append(ENTER);
			}
			
			resouces.append("<script language='javascript'>").append(ENTER);
			resouces.append("Ext.onReady(function(){").append(ENTER);
			resouces.append("Ext.SSL_SECURE_URL= '${resouceHome}/resources/images/default/s.gif';").append(ENTER); 
			resouces.append("Ext.BLANK_IMAGE_URL= '${resouceHome}/resources/images/default/s.gif';").append(ENTER);
			
			    resouces.append("   var initConfig = { ").append(ENTER);
				resouces.append("       title:'${text}',").append(ENTER);
				if ( width != null )    resouces.append("       width:${width},").append(ENTER);
				if ( height != null )   resouces.append("       height:${height},").append(ENTER);
				resouces.append("       margins:'0 0 0 0',").append(ENTER);	
				resouces.append("       expanded: true,").append(ENTER);	
				resouces.append("       layout:'accordion',").append(ENTER);
				resouces.append("       layoutConfig: { animate : ${animate} } ,").append(ENTER);
				resouces.append("       items: [ ").append(ENTER);
			    
			Context context = new DefaultContext();
			context.put("resouceHome", getResourceHome());
			context.put("style", getStyle());
			context.put("text", webTreeNode.getName());
			context.put("importCss", new Boolean(importCss) );
			context.put("importJs", new Boolean(importJs) );
			context.put("createDiv", new Boolean(createDiv) );
			context.put("autoScroll", new Boolean(autoScroll) );
			context.put("animate", new Boolean(animate) );			
			context.put("treeID", treeID );			
			context.put("width", width );
			context.put("height", height );
			
			treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
		}

	private static final String SUB_TREE_DIV_POSTFIX = "_E3_SUB_TREE_DIV";
	public void buildNodeStart(Node node, Node parentNode, int level, int row)throws BuildTreeException {
		if ( level == 1 ){	
			if ( node instanceof WebTreeNode == false){
				throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
			}
	
			WebTreeNode webTreeNode = (WebTreeNode)node;
			StringBuffer nodeTemplate = new StringBuffer();
			
			nodeTemplate.append("{").append(ENTER);
			nodeTemplate.append("       title:'${text}', ").append(ENTER);
			nodeTemplate.append("       border: false,").append(ENTER);
//			nodeTemplate.append("       contentEl: '${nodeID}'").append(ENTER);			
			nodeTemplate.append("       html: '<div id=\"${nodeID}\" style=\"overflow:auto;width:100%;height:100%\"></div>'").append(ENTER);
			if ( node.isLast() == false ){
				nodeTemplate.append("},").append(ENTER);	
			} else {
				nodeTemplate.append("}").append(ENTER);
			}
			Context context = new DefaultContext(); 
			context.put("text", webTreeNode.getName());
			context.put("nodeID", webTreeNode.getId() +SUB_TREE_DIV_POSTFIX);
			
			treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));		
		    //divBuffer.append("<div    id='").append(webTreeNode.getId()).append(SUB_TREE_DIV_POSTFIX).append("'/>");
		    
		    
			ExtTreeBuilder treeBuilder = new ExtLoadTreeBuilder();//构造树Builder
			treeBuilder.setRootVisible(false);
			treeBuilder.init(this.webContext);
			treeBuilder.setCreateDiv(false);
			treeBuilder.setTreeID(webTreeNode.getId() +SUB_TREE_DIV_POSTFIX);
			
			nodeBuilder.put(node, treeBuilder);
			
			treeBuilder.buildTreeStart();
			treeBuilder.buildRootNodeStart(node, level-1, row);
			
		} else {
			AbstractWebTreeBuilder builder = getTreeBuilder(node, level);
			builder.buildNodeStart(node, parentNode, level-1, row);
		}
	    
	}
	
	private AbstractWebTreeBuilder getTreeBuilder(Node pNode, int pLevel){
		int times = pLevel - 1;
		Node parentNode = pNode;
		for(int i=0; i<times; i++){
			parentNode = parentNode.getParent();
		}
		AbstractWebTreeBuilder result = (AbstractWebTreeBuilder)nodeBuilder.get(parentNode);
		return result;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public boolean isImportCss() {
		return importCss;
	}

	public void setImportCss(boolean importCss) {
		this.importCss = importCss;
	}

	public boolean isImportJs() {
		return importJs;
	}

	public void setImportJs(boolean importJs) {
		this.importJs = importJs;
	}

	public boolean isCreateDiv() {
		return createDiv;
	}

	public void setCreateDiv(boolean createDiv) {
		this.createDiv = createDiv;
	}

	public boolean isAutoScroll() {
		return autoScroll;
	}

	public void setAutoScroll(boolean autoScroll) {
		this.autoScroll = autoScroll;
	}

	public String getTreeID() {
		return treeID;
	}

	public void setTreeID(String treeID) {
		this.treeID = treeID;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public StringBuffer getDivBuffer() {
		return divBuffer;
	}

	public void setDivBuffer(StringBuffer divBuffer) {
		this.divBuffer = divBuffer;
	}

	public void setResourceHome(String resourceHome) {
		this.resourceHome = resourceHome;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void buildNodeEnd(Node node, Node parentNode, int level, int row)
			throws BuildTreeException {
		AbstractWebTreeBuilder builder = getTreeBuilder(node, level);		
		if ( level == 1 ){
			builder.buildTreeEnd();
			builder.buildRootNodeEnd(node, level-1, row);
		} else {
			builder.buildNodeEnd(node, parentNode, level-1, row);
		}
		
	}
	public boolean isAnimate() {
		return animate;
	}
	public void setAnimate(boolean animate) {
		this.animate = animate;
	}


}