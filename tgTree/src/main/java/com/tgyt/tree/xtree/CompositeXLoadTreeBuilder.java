/**   
  * @Title: CompositeXLoadTreeBuilder.java 
  * @Package com.tgyt.tree.xtree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:08:53 
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
 * @ClassName: CompositeXLoadTreeBuilder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:08:53 
 *  
 */
public class CompositeXLoadTreeBuilder extends XLoadTreeBuilder{
	
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
			resouces.append("<script src='${resouceHome}/map.js'></script>").append(ENTER);
			resouces.append("<script src='${resouceHome}/checkboxTreeItem.js'></script>").append(ENTER);
			resouces.append("<script src='${resouceHome}/checkboxXLoadTree.js'></script>").append(ENTER);
			resouces.append("<script src='${resouceHome}/radioTreeItem.js'></script>").append(ENTER);
			resouces.append("<script src='${resouceHome}/radioXLoadTree.js'></script>").append(ENTER);
		}
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	private void buildNormalNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		String nodeProperty = webTreeNode.getNodeProperty();
		if ( WebTreeNode.NONE.equals(nodeProperty) ){
			super.buildNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}		
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer node = new StringBuffer();
		node.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${value}\",\"${action}\",${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled}); ");
		node.append(ENTER);
		
		
		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));		
		if ( WebTreeNode.CHECKBOX.equals(nodeProperty)){
			context.put("treeItem", "WebFXCheckBoxTreeItem");	
		}else if ( WebTreeNode.RADIO.equals(nodeProperty)){
			context.put("treeItem", "WebFXRadioTreeItem");
		}		
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("action", webTreeNode.getAction());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(node.toString(), context));
		
	}
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		if ( webTreeNode instanceof WebTreeDynamicNode == false ){
			buildNormalNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}
		//build动态节点
		WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)webTreeNode;
		String nodeProperty = dynamicNode.getNodeProperty();
		StringBuffer nodeTemplate = new StringBuffer();
		Context context = new DefaultContext();		
		if ( WebTreeNode.NONE.equals(nodeProperty) ){
			nodeTemplate.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${subTreeURL}\", \"${action}\",${parent},\"${icon}\",\"${openIcon}\"); ");	
  		    nodeTemplate.append(ENTER);
		} else {
			nodeTemplate.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${value}\",\"${action}\",\"${subTreeURL}\",${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled});"); 			
 		    nodeTemplate.append(ENTER);			
		}
		
				
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));		
		if ( WebTreeNode.CHECKBOX.equals(nodeProperty)){
			context.put("treeItem", "WebFXLoadCheckBoxTreeItem");	
		}else if ( WebTreeNode.RADIO.equals(nodeProperty)){
			context.put("treeItem", "WebFXLoadRadioTreeItem");
		} else {
			context.put("treeItem", "WebFXLoadTreeItem");			
			
		}
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("subTreeURL", dynamicNode.getSubTreeURL());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("action", webTreeNode.getAction());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}		
	
}