/**   
 * @Title: EasyuiTreeBuilder.java 
 * @Package com.tgyt.tree.easyui 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-12 下午03:04:27 
 * @version V1.0   
 */

package com.tgyt.tree.easyui;

import java.io.StringWriter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.TemplateEngine;
import com.tgyt.templateEngine.support.DefaultContext;
import com.tgyt.templateEngine.support.DefaultTemplate;
import com.tgyt.templateEngine.support.StrTemplateUtil;
import com.tgyt.templateEngine.support.TemplateEngineFactory;
import com.tgyt.templateEngine.support.TemplateType;
import com.tgyt.tree.BuildTreeException;
import com.tgyt.tree.Node;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.Constants;
import com.tgyt.tree.support.WebTreeNode;

/**
 * @ClassName: EasyuiTreeBuilder
 * @Description: easyui 构建树型结构
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-12 下午03:04:27
 * 
 */
/**
 * @author zyl
 * 
 */
public class EasyuiTreeBuilder extends AbstractWebTreeBuilder {

	private final Log log = LogFactory.getLog(EasyuiTreeBuilder.class);

	/**
	 * @Fields style : 样式文件的路径
	 */
	private String style;

	/**
	 * @Fields state : 表示结点为开或者关闭，默认为开，关闭的值为closed
	 */
	private String state;

	/**
	 * @Fields resourceHome : 资源根路径
	 */
	private String resourceHome;

	public static final String DEFAULT_TREE_ID = "tree";
	public static final String DEFAULT_ROOT_NAME = "root";
	private String rootName = DEFAULT_ROOT_NAME;

	private String treeID = DEFAULT_TREE_ID;

	// 树型菜单的结构，默认树形结构
	private String treeType = Constants.DEFAULT_TREE_TYPE;

	private boolean enableDD = false;
	/**
	 * 用于保存构造树用到的变量名.
	 */
	protected List vars = null;

	private boolean animate = true;

	private TemplateEngine engine = null;
	{
		engine = TemplateEngineFactory.getInstance(TemplateType.VELOCITY);
		this.setAnimate(false);
	}

	/**
	 * 跟节点是否展开
	 */
	private boolean rootExpand = true;

	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		// 引入css
		if (this.importCss) {
			// /tgWeb/tg/commons/easyui
			resouces.append(
					"<link type='text/css' rel='stylesheet' href='${resouceHome}/themes/default/easyui.css' />")
					.append(ENTER);
			resouces.append(
					"<link type='text/css' rel='stylesheet' href='${resouceHome}/themes/icon.css' />")
					.append(ENTER);
		}
		// 引入js
		if (this.importJs) {
			resouces.append(
					"<script type=\"text/javascript\" src=\"${resouceHome}/jquery-1.6.min.js\"></script>")
					.append(ENTER);
			resouces.append(
					"<script type=\"text/javascript\" src=\"${resouceHome}/jquery.easyui.min.js\"></script>")
					.append(ENTER);
		}

		resouces.append("<ul id=\"${treeID}\" class=\"easyui-tree\"");
		if (Constants.CHECK_TREE_TYPE.equals(treeType)) {// 复选树
			resouces.append(" checkbox=\"true\"");
		}
		if (true == this.enableDD) {//是否可拖拽
			resouces.append(" dnd=\"true\"");
		}
		resouces.append(" animate=\"${animate}\">").append(ENTER);

		// 装载配置信息
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("style", getStyle());
		context.put("treeID", this.treeID);

		context.put("animate", this.animate);

		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
	}

	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow)
			throws BuildTreeException {

		StringBuffer resouces = new StringBuffer();
		if (pRootNode instanceof WebTreeNode == false) {
			throw new IllegalArgumentException(
					"node type is error, should be WebTreeNode type");
		}

		resouces.append("<li state=\"${state}\" id=\"${id}\">").append(ENTER);
		resouces.append("<span>${text}</span>").append(ENTER);

		WebTreeNode webTreeNode = (WebTreeNode) pRootNode;
		log.debug("buildRootNodeStart..." + this.getState());
		Context context = new DefaultContext();
		String nodeScriptName = getNodeScriptName(webTreeNode);
		context.put("nodeScriptName", nodeScriptName);

		context.put("text", webTreeNode.getName());
		context.put("action", webTreeNode.getAction());
		context.put("treeID", this.getTreeID());
		context.put("rootName", this.getRootName());
		context.put("state", this.getState());

		context.put("id", webTreeNode.getId());
		context.put("target", webTreeNode.getTarget());
		DefaultTemplate rootNodeTemplate = new DefaultTemplate();
		rootNodeTemplate.setInputEncoding("utf-8");

		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
	}

	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow)
			throws BuildTreeException {
		log.debug("buildRootNodeEnd..." + pRow);
		StringBuffer resouces = new StringBuffer();
		resouces.append("</li>").append(ENTER);

		Context context = new DefaultContext();
		context.put("rootScript", getNodeScriptName((WebTreeNode) pRootNode));
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
	}

	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel,
			int pRow) throws BuildTreeException {

		StringBuffer resouces = new StringBuffer();
		if (pNode instanceof WebTreeNode == false) {
			throw new IllegalArgumentException(
					"node type is error, should be WebTreeNode!");
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
			if (isfirst) {
				resouces.append("<ul>").append(ENTER);
				resouces.append("<li  id=\"${id}\">").append(ENTER);
				resouces.append("<span>${text}</span>").append(ENTER);
				resouces.append("</li>").append(ENTER);
			} else {
				resouces.append("<li  id=\"${id}\">").append(ENTER);
				resouces.append("<span>${text}</span>").append(ENTER);
				resouces.append("</li>").append(ENTER);
			}
		} else {
			if (isfirst) {
				resouces.append("<ul>").append(ENTER);
			}
			resouces.append("<li  id=\"${id}\" state=\"${state}\">").append(ENTER);
			resouces.append("<span>${text}</span>").append(ENTER);
		}

		Context context = new DefaultContext();
		String nodeScriptName = getNodeScriptName(webTreeNode);
		context.put("nodeScriptName", nodeScriptName);
		context.put("parentNodeScriptName", parentNodeScriptName);

		context.put("text", webTreeNode.getName());
		context.put("action", webTreeNode.getAction());
		context.put("treeID", this.getTreeID());
		context.put("rootName", this.getRootName());
		context.put("state", webTreeNode.getState());

		context.put("id", webTreeNode.getId());
		context.put("target", webTreeNode.getTarget());
		StringWriter writer = new StringWriter();
		DefaultTemplate rootNodeTemplate = new DefaultTemplate();
		rootNodeTemplate.setInputEncoding("utf-8");

		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));

		log.debug("buildNodeStart..." + webTreeNode.getName() + " "
				+ webTreeNode.isLeaf() + " isfirst " + webTreeNode.isFirst()
				+ " treescript" + treeScript.toString());

	}

	// 构造结点结束
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
			if (islast) {
				resouces.append("</ul>").append(ENTER);
			} else {

			}
		} else {
			if (islast) {
				resouces.append("</li>").append(ENTER);
				resouces.append("</ul>").append(ENTER);
			} else {

			}
		}

		Context context = new DefaultContext();
		DefaultTemplate rootNodeTemplate = new DefaultTemplate();
		rootNodeTemplate.setInputEncoding("utf-8");

		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));
		log.debug("buildNodeEnd...是不是子结点" + webTreeNode.getName()
				+ " isLast() " + webTreeNode.isLast() + " treescript "
				+ treeScript.toString());
	}

	public void buildTreeEnd() throws BuildTreeException {
		log.debug("buildTreeEnd...");
		StringBuffer temp = new StringBuffer();
		temp.append("</ul>");
		Context context = new DefaultContext();
		context.put("treeID", this.getTreeID());
		context.put("rootName", this.getRootName());
		treeScript.append(StrTemplateUtil.merge(temp.toString(), context));
	}

	public String getResourceHome() {
		if (resourceHome == null) {
			// contxtPath,当path为"/"时,有的服务器返回空,有的会返回"/",所以在这需要做
			// 判断
			String contextPath = this.webContext.getContextPath();
			String basePath = "/tg/commons/easyui";
			if ("/".equals(contextPath) || "\\".equals(contextPath)) {
				return basePath;
			}
			return contextPath + basePath;
		} else {
			return resourceHome;
		}
	}

	public void setResourceHome(String resourceHome) {
		this.resourceHome = resourceHome;
	}

	public String getStyle() {
		if (style == null) {
			return getResourceHome() + "/resources/themes/default/easyui.css";
		}
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isRootExpand() {
		return rootExpand;
	}

	public void setRootExpand(boolean rootExpand) {
		this.rootExpand = rootExpand;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public String getTreeID() {
		return treeID;
	}

	public void setTreeID(String treeID) {
		this.treeID = treeID;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getState() {
		if (null == state) {
			this.state = "closed";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public boolean isEnableDD() {
		return enableDD;
	}

	public void setEnableDD(boolean enableDD) {
		this.enableDD = enableDD;
	}

}
