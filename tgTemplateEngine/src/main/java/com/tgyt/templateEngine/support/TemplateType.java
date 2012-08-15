/**   
  * @Title: TemplateType.java 
  * @Package com.tgyt.templateEngine.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:19:21 
  * @version V1.0   
  */

package com.tgyt.templateEngine.support;

/** 
 * @ClassName: TemplateType 
 * @Description: 模板类型
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:19:21 
 *  
 */
public class TemplateType {
	
	private String type;
	private String description;

	public static final TemplateType VELOCITY = new TemplateType("Velocity",
			"Velocity engine");
	public static final TemplateType FREE_MARKER = new TemplateType(
			"FreeMarker", "FreeMarker engine");
	public static final TemplateType WEB_MACRO = new TemplateType("WebMacro",
			"WebMacro engine");
	public static final TemplateType JXP = new TemplateType("Jxp", "Jxp engine");

	private TemplateType(String pType, String pDescription) {
		this.type = pType;
		this.description = pDescription;
	}

	public int hashCode() {
		return this.type.hashCode();
	}

	public boolean equals(Object pObj) {
		if ( pObj == null ){
			return false;
		}
		if (pObj instanceof TemplateType == false) {
			return false;
		}
		TemplateType objTemplateType = (TemplateType) pObj;
		return this.type.equals(objTemplateType.type);
	}

	public String toString() {
		return this.description;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}
}
