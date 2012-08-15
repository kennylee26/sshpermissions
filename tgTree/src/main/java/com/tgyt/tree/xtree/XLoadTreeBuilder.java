/**   
  * @Title: XLoadTreeBuilder.java 
  * @Package com.tgyt.tree.xtree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:13:03 
  * @version V1.0   
  */

package com.tgyt.tree.xtree;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.WebTreeDynamicNode;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: XLoadTreeBuilder 
 * @Description: 加载树builder
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:13:03 
 *  
 */
public class XLoadTreeBuilder extends XTreeBuilder {
	
	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {	
		StringBuffer resouces = new StringBuffer();
		if ( this.importCss ){
		resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);
		}
		
		if ( this.importJs ){
		resouces.append("<script src='${resouceHome}/xtree.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/xloadtree.js'></script>").append(ENTER);		
		resouces.append("<script src='${resouceHome}/xmlextras.js'></script>").append(ENTER);
		}
		
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeDynamicNode == false){
			super.buildRootNodeStart(pRootNode, pLevel, pRow);
			return;
		}
		
		WebTreeDynamicNode node = (WebTreeDynamicNode)pRootNode;
		if ( node.getSubTreeURL() == null ){
			super.buildRootNodeStart(pRootNode, pLevel, pRow);
			return;
		}
		treeScript.append("<script language='javascript'>").append(ENTER);		
		StringBuffer nodeTemplate = new StringBuffer();
		nodeTemplate.append("webFXTreeConfig.usePersistence = ${usePersistence};").append(ENTER);		
		nodeTemplate.append("webFXTreeConfig.setImagePath(\"${imagePath}\");").append(ENTER);
		nodeTemplate.append("   var ${nodeScriptName}=new WebFXLoadTree(\"${text}\",").
		     append("\"${subTreeURL}\",\"${action}\",\"${behavior}\",\"${icon}\",\"${openIcon}\", ${open}); ");	
		nodeTemplate.append(ENTER);
		
		Context context = new DefaultContext(); 
		context.put("usePersistence", new Boolean( this.isUsePersistence()) );
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("text", node.getName());	
		context.put("subTreeURL", node.getSubTreeURL());
		context.put("behavior", this.getBehavior());
		context.put("imagePath", this.getResourceHome() + "/images/");
		context.put("action", node.getAction());		
        context.put("icon", node.getIcon() );
		context.put("openIcon", node.getOpenIcon() );
		context.put("open", this.isOpen());
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));		
	}	
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeDynamicNode == false ){
			super.buildNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}
		WebTreeDynamicNode node = (WebTreeDynamicNode)pNode;
		 
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer nodeTemplate = new StringBuffer();
		nodeTemplate.append("   var ${nodeScriptName}=new WebFXLoadTreeItem(\"${text}\",").
		     append("\"${subTreeURL}\", \"${action}\",${parent},\"${icon}\",\"${openIcon}\"); ");	
		nodeTemplate.append(ENTER);
		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("text", node.getName());
		context.put("subTreeURL", node.getSubTreeURL());
		context.put("action", node.getAction());
		context.put("icon", node.getIcon());
		context.put("openIcon", node.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}

	
	
}
