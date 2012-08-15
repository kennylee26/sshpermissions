/**   
  * @Title: NodeFactory.java 
  * @Package com.tgyt.tree.taglib 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午05:22:45 
  * @version V1.0   
  */

package com.tgyt.tree.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.common.tools.util.ClassUtils;
import com.tgyt.tree.support.WebTreeDynamicNode;
import com.tgyt.tree.support.WebTreeNode;

/** 
 * @ClassName: NodeFactory 
 * @Description: 结点工厂
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午05:22:45 
 *  
 */
public class NodeFactory {
	private static final Log logger = LogFactory.getLog( NodeFactory.class );
	
	private NodeFactory(){
		
	}
	
	public static WebTreeNode getInstance(String pClassName){
		if ( "default".equalsIgnoreCase(pClassName) ){
			return new WebTreeNode();
		}
		if ( "dynamic".equalsIgnoreCase(pClassName) ){
			return new WebTreeDynamicNode();
		}
		
		try {
			Object obj = ClassUtils.getNewInstance(pClassName);
			if ( obj instanceof WebTreeNode == false ){
				final String msg = 
					"类:" + pClassName + "的父类不是:" + WebTreeNode.class.getName();
				logger.error(msg);
				throw new IllegalArgumentException(msg);
			}
			return (WebTreeNode)obj;
		} catch (Exception e) {
           final String msg =
        	   "创建类:" + pClassName + "实例失败";
           
           throw new CreateObjectException(msg, e);
         
		}
	}
}
