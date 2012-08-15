/**   
  * @Title: AbstractWebTreeBuilder.java 
  * @Package com.tgyt.tree.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午04:08:51 
  * @version V1.0   
  */

package com.tgyt.tree.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.tree.BuildTreeException;

/** 
 * @ClassName: AbstractWebTreeBuilder 
 * @Description: 抽象web tree builder
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:08:51 
 *  
 */
public abstract class AbstractWebTreeBuilder extends TreeBuilderSupport implements WebTreeBuilder {
	
	protected WebContext webContext;
	
	private static final int DEFAULT_BUFFER_SIZE = 200;
	private final Log log = LogFactory.getLog( this.getClass() );
	
	protected StringBuffer treeScript = null;
	private int bufferSize = DEFAULT_BUFFER_SIZE;
	/**
	 * 是否引入css,如果为false,则使用用户自己设置的css.
	 * 注意: css路径是带web context path的路径,如: /e3/e3/ext/ext-all.css,
	 * e3是 web context path
	 */
	protected boolean importCss = false;
	/**
	 * 是否引入js,如果为false,则使用用户自己设置的js,注意:
	 * js路径,是带web context path的路径,如: /e3/e3/ext/ext.js
	 * e3是 web context path.
	 */
	protected boolean importJs = false;
	
	public AbstractWebTreeBuilder(){
		
	}
	
	
	public void init(WebContext pWebContext){
		this.webContext = pWebContext;
	    treeScript = new StringBuffer(bufferSize);		
	}
	public void init(HttpServletRequest pRequest){ 
		init(new HttpServletRequestWebContext(pRequest));
	}
	
	private static boolean isJsIdentifier(String s) {
		if ( s == null ){
			return false;
		}
        if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i=1; i<s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	
	
	/**
	 * 注意：
	 * 1:必须保证，同一个节点，获取的脚本名称必须相同。
	 *  也就是说,不管是第一次调用，还是第2次，返回的名字都一样
	 *  ，绝对不允许出现随机名称.
	 * 2:这里的命名规则不能再被修改，因为有很多页面依赖这种
	 *   命名方式，如果要修改，只能是写一个之类，覆盖这种命名方法.
	 */
	protected String getNodeScriptName(WebTreeNode pNode){
		String result = pNode.getId();
		if ( result == null ){
			throw new BuildTreeException("节点:" + pNode + "没有设置节点ID!");
		}
		if ( isJsIdentifier( result ) == false ){
			throw new BuildTreeException("节点:" + pNode + "的ID:" + result + "不是有效的js标识符号!");
		}
		return result;
	}
	 
	protected boolean isEmpty(final String pUrl){
		if (pUrl == null ){
			return true;
		}
		if ( "".equals(pUrl.trim()) ){
			return true;
		} else {
			return false;
		}
	}
	
	public void clearScript(){
		if ( treeScript != null ){
			treeScript = new StringBuffer(bufferSize);
		}
	}
	public String getTreeScript(){
		String result = treeScript.toString();
		if ( log.isDebugEnabled() ){
			log.debug("script:\n" + result);
		}
		return result;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}


	public boolean isImportCss() {
		return importCss;
	}


	public void setImportCss(boolean importCss) {
		this.importCss = importCss;
	}


	public boolean isImportJs() {
		return importJs;
	}


	public void setImportJs(boolean importJs) {
		this.importJs = importJs;
	}
}