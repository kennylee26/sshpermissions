/**   
  * @Title: XLoadSubTreeBuilder.java 
  * @Package com.tgyt.tree.xtree 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:12:24 
  * @version V1.0   
  */

package com.tgyt.tree.xtree;

import javax.servlet.http.HttpServletRequest;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.Constants;
import com.tgyt.tree.support.WebContext;
import com.tgyt.tree.support.WebTreeDynamicNode;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: XLoadSubTreeBuilder 
 * @Description: 子树构建
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:12:24 
 *  
 */
public class XLoadSubTreeBuilder extends AbstractWebTreeBuilder{
	public static final String DEFAULT_VERSION = "1.0";
	public static final String DEFAULT_ENCODING = "utf-8";
	//XML文件版本
    private String version = DEFAULT_VERSION;
    //XML编码方式
    private String encoding;
    //默认不设置.
    private String treeType  = null;


	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public void init(WebContext webContext) {
		super.init(webContext);
    	if ( this.encoding == null ){
    		this.encoding = webContext.getCharacterEncoding();
    	}
    	if ( this.encoding == null ){
    		this.encoding = DEFAULT_ENCODING;
    	}
	}
	
	public void init(HttpServletRequest pRequest){
    	super.init(pRequest);
    	if ( this.encoding == null ){
    		this.encoding = pRequest.getCharacterEncoding();
    	}
    	if ( this.encoding == null ){
    		this.encoding = DEFAULT_ENCODING;
    	}
	}    
	/**
	 * 开始构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException{
		StringBuffer resouces = new StringBuffer();
		resouces.append("<?xml version=\"${version}\" encoding=\"${encoding}\" ?>").append(ENTER);		
		resouces.append("<tree>").append(ENTER);
		
		Context context = new DefaultContext();
		context.put("version", this.version);
		context.put("encoding", this.encoding);
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	/**
	 * 结束构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeEnd() throws BuildTreeException{
		treeScript.append("</tree>").append(ENTER);
	}
	
	/**
	 * 开始构造普通节点（除跟节点之外的节点)
	 * @param pNode 当前节点
	 * @param pParentNode 父亲节点
	 * @param pLevel 节点级别，根节点为0级，根节点直接儿子节点为1级，依次类推，2,3,....
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException 
	 */
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow)
			throws BuildTreeException{
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}		
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		StringBuffer nodeXml = new StringBuffer();
		nodeXml.append(" <tree text=\"${text}\" value=\"${value}\" checked=\"${checked}\"  " +
				       " disabled=\"${disabled}\" type=\"${type}\" action=\"${action}\" icon=\"${icon}\" " +
				       " openIcon=\"${openIcon}\"");
		Context context = new DefaultContext();
		context.put("text", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		
		if ( treeType != null ){
		    context.put("type", getNodeType(webTreeNode) );
		} else {
			context.put("type", "" );
		}
		
		if ( webTreeNode.getAction() != null ){
		    context.put("action", webTreeNode.getAction().replaceAll("&","&amp;"));
		}else{
			context.put("action", webTreeNode.getAction());
		}
		
		if ( webTreeNode instanceof WebTreeDynamicNode){
			WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)webTreeNode;
			if ( dynamicNode.getSubTreeURL() != null ){
				nodeXml.append(" src=\"${src}\"");
			} 
			if ( dynamicNode.getSubTreeURL() != null ){
			  context.put("src", dynamicNode.getSubTreeURL().replaceAll("&","&amp;"));
			}
		}
		nodeXml.append(">" + ENTER);
		treeScript.append(StrTemplateUtil.merge(nodeXml.toString(), context));		
	}
	
	private String getNodeType(WebTreeNode pRootNode){
		if ( Constants.DEFAULT_TREE_TYPE.equals(this.treeType )){
			return WebTreeNode.NONE;
		}else if ( Constants.RADIO_TREE_TYPE.equals(this.treeType) ){
			return WebTreeNode.RADIO;
		}else if ( Constants.CHECK_TREE_TYPE.equals(this.treeType) ){
			return WebTreeNode.CHECKBOX;
		} else if ( Constants.COMPOSITE_TREE_TYPE.equals(this.treeType)){
			return pRootNode.getNodeProperty();
		} else {
			throw new BuildTreeException("not supported treeType:" + this.treeType);
		}
	}

	/**
	 * 结束构造普通节点（除跟节点之外的节点)
	 * @param pNode 当前节点
	 * @param pParentNode 父亲节点
	 * @param pLevel 节点级别，根节点为0级，根节点直接儿子节点为1级，依次类推，2,3,....
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException 
	 */
	public void buildNodeEnd(Node pNode, Node pParentNode, int pLevel, int pRow)
	throws BuildTreeException{
		treeScript.append("</tree>").append(ENTER);
	}
	

	/**
	 * 开始构造跟节点
	 * @param pRootNode 跟节点，非空
	 * @param pLevel 根节点级别
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException
	 */
	public void buildRootNodeStart(Node pRootNode,int pLevel, int pRow) throws BuildTreeException{
		this.buildNodeStart(pRootNode, null, pLevel, pRow);
	}
	
	/**
	 * 结束构造跟节点
	 * @param pRootNode 跟节点，非空
	 * @param pLevel 根节点级别
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException
	 */
	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow) throws BuildTreeException{
		this.buildNodeEnd(pRootNode, null, pLevel, pRow);
	}
}
