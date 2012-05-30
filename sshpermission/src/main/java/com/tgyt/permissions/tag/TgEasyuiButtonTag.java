/**   
  * @Title: TgEasyuiButtonTag.java 
  * @Package com.tgyt.permissions.tag 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2012-5-30 上午10:22:31 
  * @version V1.0   
  */

package com.tgyt.permissions.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/** 
 * @ClassName: TgEasyuiButtonTag 
 * @Description: Easyui的按钮生成标签，结合shiro权限判断
 * @author sunct sunchaotong18@163.com 
 * @date 2012-5-30 上午10:22:31 
 *  
 */
@SuppressWarnings("serial")
public class TgEasyuiButtonTag extends TagSupport{

	//easyui按钮对应的图标class
	private String iconCls = "";
	//按钮对应的js函数名称
	private String method = "";
	//按钮对应的shiro访问权限字符串
	private String permission = "";
	//按钮显示的操作名称
	private String operationName = "";
	
	
	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}


	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	
	@Override
	public int doStartTag() throws JspException {
		if(!"".equals(this.permission)){
			Subject subject = SecurityUtils.getSubject();
			if(subject.isPermitted(this.permission)){

				StringBuffer buffer = new StringBuffer("<a class='easyui-linkbutton'  plain='true' ");
				if(!"".equals(this.method)){
					buffer.append(" href='javascript:"+this.method+"' ");
				}
				if(!"".equals(this.iconCls)){
					buffer.append(" iconCls='"+this.iconCls+"' ");
				}
				buffer.append(" >");
				if(!"".equals(this.operationName)){
					buffer.append(this.operationName);
				}
				buffer.append(" </a> ");
				try {
					pageContext.getOut().println(buffer.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return SKIP_BODY;
	}

}
