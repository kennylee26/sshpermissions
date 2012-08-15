/**   
  * @Title: UserAttributeTag.java 
  * @Package com.tgyt.tree.taglib 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:26:18 
  * @version V1.0   
  */

package com.tgyt.tree.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/** 
 * @ClassName: UserAttributeTag 
 * @Description: 用于设置节点属性
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:26:18 
 *  
 */
public class UserAttributeTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	
	public int doEndTag() throws JspException {
		NodeTag nodeTag = (NodeTag) findAncestorWithClass(this, NodeTag.class);		
		if ( nodeTag == null ){
			throw new JspException("userAttribute 必须是 Node 的子元素!");
		}
		nodeTag.setAttribute(this.name, this.value);
		return super.doEndTag();
	}
	
	public void release() {
		this.name = null;
		this.value = null;
		super.release();
	}
	
	//属性名
	private String name;
	//属性值
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}