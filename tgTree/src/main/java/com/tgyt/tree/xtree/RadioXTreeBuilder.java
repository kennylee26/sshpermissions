/**   
  * @Title: RadioXTreeBuilder.java 
  * @Package com.tgyt.tree.xtree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:11:46 
  * @version V1.0   
  */

package com.tgyt.tree.xtree;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: RadioXTreeBuilder 
 * @Description: 单选树构建
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:11:46 
 *  
 */
public class RadioXTreeBuilder  extends XTreeBuilder{
	
	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		if ( this.importJs ){
		resouces.append("<script src='${resouceHome}/xtree.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/radioTreeItem.js'></script>").append(ENTER);
		}
		if ( this.importCss ){
		   resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);
		}
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer node = new StringBuffer();
		node.append("   var ${nodeScriptName}=new WebFXRadioTreeItem(\"${name}\",").
		     append("\"${value}\",\"${action}\", ${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled}); ");
		node.append(ENTER);
		
		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("action", webTreeNode.getAction());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(node.toString(), context));
		
	}	
	
	
}
