/**   
  * @Title: WebTreeDynamicNode.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:37:26 
  * @version V1.0   
  */

package com.tgyt.tree.support;

/** 
 * @ClassName: WebTreeDynamicNode 
 * @Description: 动态树结点
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:37:26 
 *  
 */
public class WebTreeDynamicNode extends WebTreeNode{

	private static final long serialVersionUID = 1L;
	
	//负责导入子树的URL
	private String subTreeURL;

	public WebTreeDynamicNode() {
		super();
	}

	public WebTreeDynamicNode(String pName, String pId){
		super(pName, pId);
	}
	
	public WebTreeDynamicNode(String pName, Object pUserData) {
		super(pName, pUserData);
	}

	public WebTreeDynamicNode(String pName, String pId, Object pUserData) {
		super(pName, pId, pUserData);
	}

	public WebTreeDynamicNode(String pName) {
		super(pName);
	}

	public String getSubTreeURL() {
		return subTreeURL;
	}

	public void setSubTreeURL(String subTreeURL) {
		this.subTreeURL = subTreeURL;
	}
}