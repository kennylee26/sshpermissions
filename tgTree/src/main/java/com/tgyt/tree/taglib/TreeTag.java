/**   
  * @Title: TreeTag.java 
  * @Package com.tgyt.tree.taglib 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:25:31 
  * @version V1.0   
  */

package com.tgyt.tree.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.tree.NodeVisitor;
import com.tgyt.tree.TreeDirector;
import com.tgyt.tree.TreeModel;
import com.tgyt.tree.ext.ExtLoadTreeBuilder;
import com.tgyt.tree.support.DefaultNodeComparator;
import com.tgyt.tree.support.DefaultTreeDirector;
import com.tgyt.tree.support.JspPageWebContext;
import com.tgyt.tree.support.PropertyNodeComparator;
import com.tgyt.tree.support.ReverseComparator;
import com.tgyt.tree.support.WebContext;
import com.tgyt.tree.support.WebTreeBuilder;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: TreeTag 
 * @Description: tree 标签
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:25:31 
 *  
 */
public class TreeTag extends BodyTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = 1L;
	private String var;
	private String items;
	private String scope;
	private String builder = "default";
	private NodeVisitor visitor;
	private boolean defaultSort = true;
	
	/**
	 * 节点排序器，如果设置了，则使用它来对节点排序
	 */
	private Comparator comparator = null;
	/**
	 * 排序属性，如果没有设置comparator，则使用
	 * 业务对象的sortProperty属性值进行排序 
	 */
	private String sortProperty = null;
	/**
	 * 是否启用反向排序
	 */
	private boolean reverse = false;
	
	private final Log logger = LogFactory.getLog( this.getClass() );
	/**
	 * 动态属性
	 */
	private java.util.LinkedHashMap dynamicAttributes = new java.util.LinkedHashMap();
	
	//
	private java.util.List nodes = new ArrayList();
	private java.util.Map idParentIds = new java.util.HashMap();
	private java.util.Collection userDatas = null;
	private java.util.Iterator userDatasIterator = null;
	private Object currUserData;
	
	public void addNode(WebTreeNode pNode){
		nodes.add(pNode);
	}
	public void addIdParentIds(String pId, String pParentId){
		idParentIds.put(pId, pParentId);
	}

	public int doStartTag() throws JspException {
		userDatas = (java.util.Collection)this.pageContext.findAttribute(this.items);
		if ( userDatas == null ){
			return SKIP_BODY;
		}
		if ( userDatas.isEmpty() ){
			return SKIP_BODY;	
		}
		userDatasIterator = userDatas.iterator();
		currUserData = userDatasIterator.next();
		this.pageContext.setAttribute(this.var, currUserData);
		return EVAL_BODY_INCLUDE;
	}
	public int doAfterBody() throws JspException {
		/**
		 * @todo: 提供添加节点的方法.
		 */
		if ( userDatasIterator.hasNext() ){
			currUserData = userDatasIterator.next();
			this.pageContext.setAttribute(this.var, currUserData);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}
	
	private void setProperty(Object pObj, String pProperty, Object pValue) throws JspException{
		try {
			BeanUtils.setProperty(pObj, pProperty, pValue);
		} catch (Exception ex){
			final String msg =
				"设置节点：" + pObj.getClass().getName() + "的属性:" + pProperty + "失败！" +
				"属性值为:" + pValue;
			logger.error(msg, ex);
			throw new JspException(msg, ex);
		}
		
	}
	
	public int doEndTag() throws JspException {
		TreeModel treeModel = NodeUtils.convert(this.nodes, this.idParentIds);
		WebTreeBuilder treeBuilder = BuilderFactory.getInstance(this.builder);
		WebContext context = new JspPageWebContext(this.pageContext) ;
		System.out.println("context path=" +  context.getContextPath() );
		treeBuilder.init( context );
		java.util.Iterator keys = dynamicAttributes.keySet().iterator();
		while( keys.hasNext() ){
			Object key = keys.next();
			Object value = dynamicAttributes.get(key);
			this.setProperty(treeBuilder, (String)key, value);
		}
		/**
		 * @todo: 允许设置不同的TreeDirector
		 */
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(getRealComparator());
		if ( visitor != null ){
		   director.setNodeVisitor(visitor);
		}
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		JspWriter writer = this.pageContext.getOut();
		cleanUp();
		try {
			writer.print(treeScript);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e.getMessage(), e);
		}
		return super.doEndTag();
	}
	  private void cleanUp()
	    {
		    dynamicAttributes.clear();
		    nodes.clear();
		    idParentIds.clear();
		    currUserData = null;
		}

	
	public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
		dynamicAttributes.put(name, value);
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
	
	private Comparator getRealComparator(){
		Comparator result = null;
		if ( this.comparator != null ){
			result = this.comparator;
		} else if ( this.sortProperty != null ){
			result = new PropertyNodeComparator(this.sortProperty);
		} else {
			if ( defaultSort == false ){
				return null;
			} else {
				result = new DefaultNodeComparator();				
			}
		}
		if ( this.reverse ){
			return new ReverseComparator( result );
		} else {
			return result;
		}
	}

	public void release() {
		this.var = null;
		this.items = null;
		this.scope = null;
		this.builder = "default";
		this.comparator = null;
		this.sortProperty = null;
		this.reverse = false;
		super.release();
	}
	public Object getCurrUserData() {
		return currUserData;
	}
	public Comparator getComparator() {
		return comparator;
	}
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}
	public String getSortProperty() {
		return sortProperty;
	}
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
	public boolean isReverse() {
		return reverse;
	}
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
	public NodeVisitor getVisitor() {
		return visitor;
	}
	public void setVisitor(NodeVisitor visitor) {
		this.visitor = visitor;
	}
	public boolean isDefaultSort() {
		return defaultSort;
	}
	public void setDefaultSort(boolean defaultSort) {
		this.defaultSort = defaultSort;
	}
	
	public static void main(String[] args){
		ExtLoadTreeBuilder builder = new ExtLoadTreeBuilder();
		TreeTag tag = new TreeTag();
	    try {
			tag.setProperty(builder, "createDive", "true" );
		} catch (JspException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
