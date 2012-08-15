/**   
  * @Title: ExtLoadTreeBuilder.java 
  * @Package com.tgyt.tree.ext 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:05:06 
  * @version V1.0   
  */

package com.tgyt.tree.ext;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.WebTreeDynamicNode;
import com.tgyt.tree.support.WebTreeNode;


/** 
 * @ClassName: ExtLoadTreeBuilder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:05:06 
 *  
 */
public class ExtLoadTreeBuilder extends ExtTreeBuilder {
    public static final String DATA_URL = "_e3DataUrl";
	
	public void buildTreeStart() throws BuildTreeException {
		super.buildTreeStart();
		StringBuffer nodeTemplate = new StringBuffer();		
		nodeTemplate.append("       ${treeID}.loader = new Ext.tree.TreeLoader({").append(ENTER);
		nodeTemplate.append("          dataUrl: 'temp'").append(ENTER);
		nodeTemplate.append("       });").append(ENTER);
        //修改dataUrl
		nodeTemplate.append("${treeID}.on('beforeload', function(pNode){").append(ENTER);
		nodeTemplate.append("    if ( pNode.attributes.${dataUrl} ) ").append(ENTER);
		nodeTemplate.append("       ${treeID}.loader.dataUrl = pNode.attributes.${dataUrl};").append(ENTER);
		nodeTemplate.append(" });").append(ENTER);
		//修改图标
		nodeTemplate.append("${treeID}.on('collapsenode', function(pNode){").append(ENTER); 
		nodeTemplate.append("    if ( pNode.attributes.${icon} ) ").append(ENTER);
		nodeTemplate.append("       pNode.ui.getIconEl().src = pNode.attributes.${icon}").append(ENTER);
	    nodeTemplate.append(" });").append(ENTER);
	    //修改open icon
		nodeTemplate.append("${treeID}.on('expandnode', function(pNode){").append(ENTER);
		nodeTemplate.append("    if ( pNode.attributes.${openIcon} ) ").append(ENTER);		
		nodeTemplate.append("       pNode.ui.getIconEl().src = pNode.attributes.${openIcon}").append(ENTER);
	    nodeTemplate.append(" });").append(ENTER);
	    
		
		Context context = new DefaultContext();
		context.put("treeID", this.getTreeID());
		context.put("dataUrl", DATA_URL);		
		context.put("icon", ExtSubTreeBuilder.ICON_NAME);
		context.put("openIcon", ExtSubTreeBuilder.OPEN_ICON_NAME);
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
	}
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}
		if ( pRootNode instanceof WebTreeDynamicNode == false){
			super.buildRootNodeStart(pRootNode, pLevel, pRow);
			return;
		}
		
		WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)pRootNode;
		StringBuffer nodeTemplate = new StringBuffer();
		
		nodeTemplate.append("${nodeScriptName} = new Ext.tree.AsyncTreeNode({").append(ENTER);
		nodeTemplate.append("       id:'${nodeScriptName}', ").append(ENTER);
		nodeTemplate.append("       text:'${text}',").append(ENTER);
		nodeTemplate.append("       href:\"${action}\",").append(ENTER);
		nodeTemplate.append("       hrefTarget:'${target}',").append(ENTER);		
		nodeTemplate.append("       qtip :'${tip}',").append(ENTER);
		nodeTemplate.append("       allowDrag:${allowDrag},").append(ENTER);
		nodeTemplate.append("       allowDrop:${allowDrop},").append(ENTER);
		nodeTemplate.append("       ${dataUrlKey}:'${dataUrl}',").append(ENTER);		
		nodeTemplate.append("       ${userAttributes}");//节点扩展属性		
		nodeTemplate.append("       iconCls :'${iconCls}'").append(ENTER);		
		nodeTemplate.append("     });").append(ENTER);
		
		nodeTemplate.append("${rootName} = ${nodeScriptName} ; ").append(ENTER);
		nodeTemplate.append("${treeID}.setRootNode( ${rootName} );").append(ENTER);
		
		Context context = new DefaultContext(); 
		String nodeScriptName = getNodeScriptName(dynamicNode);
		context.put("nodeScriptName", nodeScriptName);
		context.put("dataUrlKey", DATA_URL);
		
		context.put("userAttributes", getUserAttributes(dynamicNode));
		vars.add(nodeScriptName);
		vars.add(this.getRootName());
		
		context.put("dataUrl", dynamicNode.getSubTreeURL());
		context.put("text", dynamicNode.getName());
		context.put("action", dynamicNode.getAction());	
		context.put("treeID", this.getTreeID());
		context.put("rootName", this.getRootName());
		
		context.put("iconCls", getStyle( new Icon(dynamicNode.getIcon(), dynamicNode.getOpenIcon())));
		
		context.put("target", dynamicNode.getTarget());
		context.put("allowDrag", dynamicNode.isDragable());
		context.put("allowDrop", dynamicNode.isDropable());
		context.put("tip", dynamicNode.getTip());
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));		
	}
	


	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		if ( pNode instanceof WebTreeDynamicNode == false){
			super.buildNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}
		
		WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)pNode;
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		StringBuffer nodeTemplate = new StringBuffer();
	    
		nodeTemplate.append("${nodeScriptName} = new Ext.tree.AsyncTreeNode({").append(ENTER);
		nodeTemplate.append("       id:'${nodeScriptName}', ").append(ENTER);
		nodeTemplate.append("       text:'${text}',").append(ENTER);	 
		nodeTemplate.append("       href:\"${action}\",").append(ENTER);
		nodeTemplate.append("       hrefTarget:'${target}',").append(ENTER);		
		nodeTemplate.append("       qtip :'${tip}',").append(ENTER);
		nodeTemplate.append("       disabled:${disabled},").append(ENTER);
		nodeTemplate.append("       allowDrag:${allowDrag},").append(ENTER);
		nodeTemplate.append("       allowDrop:${allowDrop},").append(ENTER);
		nodeTemplate.append("       ${dataUrlKey}:'${dataUrl}',").append(ENTER);		
		nodeTemplate.append("       ${userAttributes}");//节点扩展属性
		nodeTemplate.append("       icon:'${icon}'").append(ENTER);
		nodeTemplate.append("     });").append(ENTER);
		nodeTemplate.append("${parentNodeScriptName}.appendChild( ${nodeScriptName} );").append(ENTER);		
		 
		Context context = new DefaultContext(); 
		String nodeScriptName = getNodeScriptName(dynamicNode);
		context.put("nodeScriptName", nodeScriptName);
		context.put("dataUrlKey", DATA_URL);
		vars.add(nodeScriptName);
		context.put("parentNodeScriptName", parentNodeScriptName);
		context.put("text", dynamicNode.getName());
		context.put("userAttributes", getUserAttributes(dynamicNode));
		context.put("action", dynamicNode.getAction());
		context.put("icon", dynamicNode.getIcon());
		
		context.put("dataUrl", dynamicNode.getSubTreeURL());
		context.put("target", dynamicNode.getTarget());
		context.put("disabled", dynamicNode.isDisabled());
		context.put("allowDrag", dynamicNode.isDragable());
		context.put("allowDrop", dynamicNode.isDropable());
		context.put("tip", dynamicNode.getTip());
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}

}
