/**   
  * @Title: YUIMenuBuilder.java 
  * @Package com.tgyt.tree.yui 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:19:42 
  * @version V1.0   
  */

package com.tgyt.tree.yui;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: YUIMenuBuilder 
 * @Description: YUI树菜单构建
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:19:42 
 *  
 */
public class YUIMenuBuilder extends AbstractWebTreeBuilder{

	private String resourceHome;
	private String yuiMenuStyle;
	public static final String DEFAULT_NAME_PREFIX = "E3_YUI_MENU";
	public static final int DEFAULT_HIDE_DELAY = 750;
	public static final boolean DEFAULT_LAZYLOAD = true;
	public static final boolean DEFAULT_AUTO_SUB_MENU_DISPLAY = true;
	private String namePrefix = DEFAULT_NAME_PREFIX;
	
	private boolean autoSubMenuDisplay = true;
	private boolean lazyload = true;
	private int hideDelay = DEFAULT_HIDE_DELAY;
	
	

	/**
	 * 开始构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException{
		StringBuffer resouces = new StringBuffer();
		resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);
		resouces.append("<link type='text/css' rel='stylesheet' href='${resouceHome}/build/reset-fonts-grids/reset-fonts-grids.css' />").append(ENTER);
		resouces.append("<script src='${resouceHome}/build/yahoo-dom-event/yahoo-dom-event.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/build/container/container_core.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/build/menu/menu-min.js'></script>").append(ENTER);
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getYuiMenuStyle());
		
	   resouces.append("<script type='text/javascript'>").append(ENTER);
	   resouces.append("YAHOO.util.Event.onContentReady(\"${namePrefix}\", function () {").append(ENTER);
       resouces.append("var ${namePrefix}MenuBar = new YAHOO.widget.MenuBar(\"${namePrefix}\", { autosubmenudisplay: ${autoSubMenuDisplay}, hidedelay: ${hideDelay}, lazyload: ${lazyload} });");
       resouces.append("${namePrefix}MenuBar.render();").append(ENTER);
       resouces.append("});").append(ENTER);
       resouces.append("</script>").append(ENTER);
       
       context.put("namePrefix", this.namePrefix);
       context.put("autoSubMenuDisplay", this.autoSubMenuDisplay);
       context.put("hideDelay", this.hideDelay );
       context.put("lazyload",  this.lazyload  );
     		
       resouces.append("<div id=\"${namePrefix}\" class=\"yuimenubar \">").append(ENTER);
       resouces.append("  <div class=\"bd\">").append(ENTER);
       resouces.append("   <ul class=\"first-of-type\">").append(ENTER);
       treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
           
		

				
	}
	
	public String getResourceHome() {
		if ( resourceHome == null ){
			return this.webContext.getContextPath() + "/e3/commons/yui";
		} else {
			return resourceHome;
		}
	}
	
	/**
	 * 结束构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeEnd() throws BuildTreeException{
		StringBuffer resouces = new StringBuffer();
		resouces.append("</ul>").append(ENTER);
		resouces.append("</div>").append(ENTER);
		resouces.append("</div>").append(ENTER);
		Context context = new DefaultContext();
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
		
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
		if ( pNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}
		WebTreeNode node = (WebTreeNode)pNode;
		
		boolean isHaveChild = node.getChildCount() > 0 ;
		StringBuffer resouces = new StringBuffer();
		resouces.append("<li class=\"yuimenuitem ${first-of-type}\"><a class=\"yuimenuitemlabel\" href=\"${action}\">${text}</a>").append(ENTER);
		if (  isHaveChild ){			
			resouces.append("<div  class=\"yuimenu\">").append(ENTER);
	        resouces.append("  <div class=\"bd\">").append(ENTER);
		    resouces.append("   <ul>").append(ENTER);		
		}		
		Context context = new DefaultContext();
		if ( pRow == 0 ){//第一个节点
			context.put("first-of-type", "first-of-type");
		} else {
			context.put("first-of-type", "");
		}
		context.put("text", node.getName());
		context.put("action", node.getAction());		
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
		
		
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
		StringBuffer resouces = new StringBuffer();
		boolean isHaveChild = pNode.getChildCount() > 0 ;
		if (  isHaveChild ){
			resouces.append("</ul>").append(ENTER);
			resouces.append("</div>").append(ENTER);
			resouces.append("</div>").append(ENTER);
		}
		resouces.append("</li>").append(ENTER);		
		Context context = new DefaultContext();
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
	}
	

	/**
	 * 开始构造跟节点
	 * @param pRootNode 跟节点，非空
	 * @param pLevel 根节点级别
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException
	 */
	public void buildRootNodeStart(Node pRootNode,int pLevel, int pRow) throws BuildTreeException{
		if ( pRootNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}
		WebTreeNode node = (WebTreeNode)pRootNode;
		
		boolean isHaveChild = pRootNode.getChildCount() > 0 ;
		StringBuffer resouces = new StringBuffer();
		resouces.append("<li class=\"yuimenubaritem ${first-of-type}\"><a class=\"yuimenubaritemlabel\" href=\"${action}\">${text}</a>").append(ENTER);
		if (  isHaveChild ){			
			resouces.append("<div  class=\"yuimenu\">").append(ENTER);
	        resouces.append("  <div class=\"bd\">").append(ENTER);
		    resouces.append("   <ul>").append(ENTER);		
		}		
		Context context = new DefaultContext();
		if ( pRow == 0 ){//第一个节点
			context.put("first-of-type", "first-of-type");
		} else {
			context.put("first-of-type", "");
		}
		context.put("text", node.getName());
		context.put("action", node.getAction());
		
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
		
	}
	
	/**
	 * 结束构造跟节点
	 * @param pRootNode 跟节点，非空
	 * @param pLevel 根节点级别
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException
	 */
	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow) throws BuildTreeException{
		StringBuffer resouces = new StringBuffer();
		boolean isHaveChild = pRootNode.getChildCount() > 0 ;
		if (  isHaveChild ){
			resouces.append("</ul>").append(ENTER);
			resouces.append("</div>").append(ENTER);
			resouces.append("</div>").append(ENTER);
		}
		resouces.append("</li>").append(ENTER);		
		Context context = new DefaultContext();
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
		
	}

	public String getYuiMenuStyle() {
		if ( yuiMenuStyle == null ){
			return getResourceHome() + "/build/menu/assets/menu.css";
		}
		return yuiMenuStyle; 
	}

	public void setYuiMenuStyle(String yuiMenuStyle) {
		this.yuiMenuStyle = yuiMenuStyle;
	}

	public void setResourceHome(String resourceHome) {
		this.resourceHome = resourceHome;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public boolean isAutoSubMenuDisplay() {
		return autoSubMenuDisplay;
	}

	public void setAutoSubMenuDisplay(boolean autoSubMenuDisplay) {
		this.autoSubMenuDisplay = autoSubMenuDisplay;
	}

	public int getHideDelay() {
		return hideDelay;
	}

	public void setHideDelay(int hideDelay) {
		this.hideDelay = hideDelay;
	}

	public boolean isLazyload() {
		return lazyload;
	}

	public void setLazyload(boolean lazyload) {
		this.lazyload = lazyload;
	}

}
