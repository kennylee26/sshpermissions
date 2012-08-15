/**   
  * @Title: TgytTreeBuilder.java 
  * @Package com.tgyt.tree.easyui 
  * @Description: 
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-27 下午2:55:16 
  * @version V1.0   
  */

package com.tgyt.tree.easyui;

import java.io.StringWriter;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.DefaultTemplate;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: TgytTreeBuilder 
 * @Description: 权限登录左侧树拼成
 * @author ligangying ligangying1987@163.com 
 * @date 2011-9-27 下午2:55:16 
 *  
 */
public class TgytTreeBuilder extends AbstractWebTreeBuilder {

	@Override
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel,
			int pRow) throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		if (pNode instanceof WebTreeNode == false) {
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode) pNode;

		String parentNodeScriptName = getNodeScriptName((WebTreeNode) pParentNode);

		// 看是否还有子结点
		boolean isleaf = webTreeNode.isLeaf();
		// 是否是当前第一个结点
		boolean isfirst = webTreeNode.isFirst();
		// 如果是子结点，那么写入<li></li>
		// <li>
		// <span>File 13</span>
		// </li>
		if (isleaf) {
			//因为我加了一个根节点
			if(null == webTreeNode.getParent().getParent()){
				resouces.append("<div style='width: 173px;' class='panel'><div style='height: 15px; width: 163px;' class='panel-header accordion-header'>" + 
						"<div class='panel-title panel-with-icon'>${text}</div><div class='panel-icon icon icon-sys'></div>" + 
						"<div class='panel-tool'><div class='accordion-collapse accordion-expand'></div></div></div>").append(ENTER);
				resouces.append("</div>").append(ENTER);
			}else if(isfirst){
				resouces.append("<li><div>").append(ENTER);
				resouces.append("<a ref='${id}' href='#' rel='${action}'> <span class='icon ${icon}'>&nbsp;</span><span class='nav'>${text}</span></a>").append(ENTER);
				resouces.append("</div></li>").append(ENTER);
			}else{
				resouces.append("<li><div>").append(ENTER);
				resouces.append("<a ref='${id}' href='#' rel='${action}'> <span class='icon ${icon}'>&nbsp;</span><span class='nav'>${text}</span></a>").append(ENTER);
				resouces.append("</div></li>").append(ENTER);
			}
			
		} else {
			
			if(isfirst){
				resouces.append("<div style='width: 173px;' class='panel'><div style='height: 15px; width: 163px;' class='panel-header accordion-header accordion-header-selected'>" + 
						"<div class='panel-title panel-with-icon'>${text}</div><div class='panel-icon icon icon-sys'></div>" + 
						"<div class='panel-tool'>").append(ENTER);
				resouces.append("<div class='accordion-collapse'></div></div></div>").append(ENTER);
				resouces.append("<div style='width: 173px; height: 578px;' class='panel-body accordion-body' title=''><ul>").append(ENTER);
			}else{
				resouces.append("<div style='width: 173px;' class='panel'><div style='height: 15px; width: 163px;' class='panel-header accordion-header'>" + 
						"<div class='panel-title panel-with-icon'>${text}</div><div class='panel-icon icon icon-sys'></div>" + 
						"<div class='panel-tool'>").append(ENTER);
				resouces.append("<div class='accordion-collapse accordion-expand'></div></div></div>").append(ENTER);
				resouces.append("<div style='width: 173px; height: 578px;display: none;' class='panel-body accordion-body' title=''><ul>").append(ENTER);
			}
			
		}

		Context context = new DefaultContext();
		String nodeScriptName = getNodeScriptName(webTreeNode);
		context.put("nodeScriptName", nodeScriptName);
		context.put("parentNodeScriptName", parentNodeScriptName);
		context.put("text", webTreeNode.getName());
		context.put("action", webTreeNode.getAction());
		//context.put("treeID", this.getTreeID());
		//context.put("rootName", this.getRootName());
		context.put("state", webTreeNode.getState());
		context.put("icon", webTreeNode.getIcon());
		
		context.put("id", webTreeNode.getId().substring(4));
		context.put("target", webTreeNode.getTarget());
		StringWriter writer = new StringWriter();
		DefaultTemplate rootNodeTemplate = new DefaultTemplate();
		rootNodeTemplate.setInputEncoding("utf-8");

		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
	}
	
	public void buildNodeEnd(Node pNode, Node pParentNode, int pLevel, int pRow)
			throws BuildTreeException {

		StringBuffer resouces = new StringBuffer();
		if (pNode instanceof WebTreeNode == false) {
			throw new IllegalArgumentException(
					"node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode) pNode;

		// 看是否还有子结点
		boolean isleaf = webTreeNode.isLeaf();
		// 是否是最后一个
		boolean islast = webTreeNode.isLast();

		if (isleaf) {
			if(islast){
			//	resouces.append("</div>").append(ENTER);
			}else{
				
			}
		} else {
			if (islast) {
				resouces.append("</ul></div>").append(ENTER);
				resouces.append("</div>").append(ENTER);
			} else {
				resouces.append("</ul></div></div>").append(ENTER);
			}
		}

		Context context = new DefaultContext();
		DefaultTemplate rootNodeTemplate = new DefaultTemplate();
		rootNodeTemplate.setInputEncoding("utf-8");

		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
	}
	
	
	@Override
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
	}
}
