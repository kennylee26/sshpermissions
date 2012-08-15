/**   
  * @Title: ExtSubTreeBuilder.java 
  * @Package com.tgyt.tree.ext 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:33:34 
  * @version V1.0   
  */

package com.tgyt.tree.ext;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.Constants;
import com.tgyt.tree.support.WebTreeDynamicNode;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: ExtSubTreeBuilder 
 * @Description: ext 子树构造
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:33:34 
 *  
 */
public class ExtSubTreeBuilder  extends AbstractWebTreeBuilder{
	
	private final Log log = LogFactory.getLog(getClass());
	private String treeType  = Constants.DEFAULT_TREE_TYPE;
	
    public String getTreeType() {
		return treeType;
	}
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}
	public void init(HttpServletRequest pRequest){
    	super.init(pRequest);
	}    
	/**
	 * 开始构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException{
		treeScript.append("[").append(ENTER);		
	}
	/**
	 * 结束构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeEnd() throws BuildTreeException{
		int index = treeScript.lastIndexOf(",");
		if ( index != -1 ){
			treeScript.deleteCharAt(index);
		}		
		treeScript.append("]").append(ENTER);
	}
	
	//获取用户 
	final protected String getUserAttributes(WebTreeNode pNode){
		Map userAttributes = pNode.getUserAttributes();
		Iterator keyIterator = userAttributes.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while( keyIterator.hasNext() ){
			String key = (String)keyIterator.next();
			String value = (String)userAttributes.get(key);
			/**
			 * TODO: value进行escape处理，消除',"
			 */
			sb.append("'").append(key).append("'").append(" : ").append("'").append(value).append("'").append(",").append(ENTER);
		}
		return sb.toString();
	}
	
	public static final String  ICON_NAME = "_E3_TREE_NODE_001";
	public static final String  OPEN_ICON_NAME = "_E3_TREE_OPEN_NODE_001";
	
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
		nodeXml.append(" { 'id': '${nodeScriptName}', 'href' : '${action}', 'text' : '${text}', " +
				          " '${dataUrlKey}': '${dataUrl}'" +
				          ", 'hrefTarget' :'${target}',    " +
				          "  '${icon_name}' : '${icon}', '${open_icon_name}' : '${openIcon}', " +				          
				          " 'qtip' :'${tip}', 'allowDrag' : ${allowDrag}, 'disabled' : ${disabled}," +				          
				          "'allowDrop' : ${allowDrop}, " +
				          " ${userAttributes} 'icon' : '${icon}', 'leaf': ${leaf} ");
		if ( this.isGenCheckbox(webTreeNode)){
			nodeXml.append( " ,'checked': ${selected} " );
		}
		nodeXml.append("},").append(ENTER);
        
		Context context = new DefaultContext();
		String nodeScriptName = getNodeScriptName(webTreeNode);
		context.put("nodeScriptName", nodeScriptName);
		context.put("text", webTreeNode.getName());
		
		context.put("dataUrlKey", ExtLoadTreeBuilder.DATA_URL);
		context.put("leaf", "true" );
		context.put("dataUrl", "");
		if (webTreeNode instanceof WebTreeDynamicNode){//是动态节点
		  WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)webTreeNode;
		  if ( this.isEmpty(dynamicNode.getSubTreeURL()) == false ){//如果url为空，则为叶子节点
		  	  context.put("leaf", "false" );
			  context.put("dataUrl", dynamicNode.getSubTreeURL());
		  }
		}
		
		context.put("userAttributes", getUserAttributes(webTreeNode));
		
		context.put("text", webTreeNode.getName());
		context.put("action", webTreeNode.getAction());
		context.put("icon_name", ICON_NAME);
		context.put("open_icon_name", OPEN_ICON_NAME);
		
		context.put("icon", webTreeNode.getIcon());
		context.put("openIcon", webTreeNode.getOpenIcon());
		context.put("target", webTreeNode.getTarget());
		context.put("selected", webTreeNode.isSelected());
		context.put("disabled", webTreeNode.isDisabled());
		context.put("allowDrag", webTreeNode.isDragable());
		context.put("allowDrop", webTreeNode.isDropable());
		context.put("tip", webTreeNode.getTip());
		treeScript.append(StrTemplateUtil.merge(nodeXml.toString(), context));		
	}
	
	private boolean isGenCheckbox(WebTreeNode pRootNode){
		if ( Constants.DEFAULT_TREE_TYPE.equals(this.treeType )){
			return false;
		}else if ( Constants.CHECK_TREE_TYPE.equals(this.treeType) ){
			return true;
		} else if ( Constants.COMPOSITE_TREE_TYPE.equals(this.treeType)){
			if ( WebTreeNode.CHECKBOX.equals(pRootNode.getNodeProperty())){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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
	
}

