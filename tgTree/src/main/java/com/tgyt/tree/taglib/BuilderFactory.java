/**   
 * @Title: BuilderFactory.java 
 * @Package com.tgyt.tree.taglib 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午04:57:48 
 * @version V1.0   
 */

package com.tgyt.tree.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.common.tools.util.ClassUtils;
import com.tgyt.tree.ext.CheckExtTreeBuilder;
import com.tgyt.tree.ext.CompositeExtTreeBuilder;
import com.tgyt.tree.ext.ExtLoadTreeBuilder;
import com.tgyt.tree.ext.ExtTreeBuilder;
import com.tgyt.tree.ext.OutlookExtTreeBuilder;
import com.tgyt.tree.ext.PrvCheckExtTreeBuilder;
import com.tgyt.tree.support.WebTreeBuilder;
import com.tgyt.tree.support.WebTreeNode;
import com.tgyt.tree.xtree.CheckXLoadTreeBuilder;
import com.tgyt.tree.xtree.CheckXTreeBuilder;
import com.tgyt.tree.xtree.CompositeXLoadTreeBuilder;
import com.tgyt.tree.xtree.CompositeXTreeBuilder;
import com.tgyt.tree.xtree.PrvCheckXTreeBuilder;
import com.tgyt.tree.xtree.RadioXLoadTreeBuilder;
import com.tgyt.tree.xtree.RadioXTreeBuilder;
import com.tgyt.tree.xtree.XLoadTreeBuilder;
import com.tgyt.tree.xtree.XTreeBuilder;

/**
 * @ClassName: BuilderFactory
 * @Description: 构建树factory
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午04:57:48
 * 
 */
public class BuilderFactory {

	private static final Log logger = LogFactory.getLog(NodeFactory.class);

	private BuilderFactory() {

	}

	public static WebTreeBuilder getInstance(String pClassName) {

		if ("default".equalsIgnoreCase(pClassName)) {
			return new CompositeXTreeBuilder();
		}

		if ("XTree".equalsIgnoreCase(pClassName)) {
			return new XTreeBuilder();
		}
		if ("XLoadTree".equalsIgnoreCase(pClassName)) {
			return new XLoadTreeBuilder();
		}

		if ("RadioXTree".equalsIgnoreCase(pClassName)) {
			return new RadioXTreeBuilder();
		}
		if ("RadioXLoadTree".equalsIgnoreCase(pClassName)) {
			return new RadioXLoadTreeBuilder();
		}

		if ("CheckXTree".equalsIgnoreCase(pClassName)) {
			return new CheckXTreeBuilder();
		}
		if ("PrvCheckXTree".equalsIgnoreCase(pClassName)) {
			return new PrvCheckXTreeBuilder();
		}

		if ("CheckXLoadTree".equalsIgnoreCase(pClassName)) {
			return new CheckXLoadTreeBuilder();
		}

		if ("CompositeXTree".equalsIgnoreCase(pClassName)) {
			return new CompositeXTreeBuilder();
		}
		if ("CompositeXLoadTree".equalsIgnoreCase(pClassName)) {
			return new CompositeXLoadTreeBuilder();
		}

		if ("ExtTree".equalsIgnoreCase(pClassName)) {
			return new ExtTreeBuilder();
		}
		if ("ExtLoadTree".equalsIgnoreCase(pClassName)) {
			return new ExtLoadTreeBuilder();
		}
		if ("CheckExtTree".equalsIgnoreCase(pClassName)) {
			return new CheckExtTreeBuilder();
		}
		if ("PrvCheckExtTree".equalsIgnoreCase(pClassName)) {
			return new PrvCheckExtTreeBuilder();
		}
		if ("CompositeExtTree".equalsIgnoreCase(pClassName)) {
			return new CompositeExtTreeBuilder();
		}

		if ("OutlookExtTree".equalsIgnoreCase(pClassName)) {
			return new OutlookExtTreeBuilder();
		}

		try {
			Object obj = ClassUtils.getNewInstance(pClassName);
			if (obj instanceof WebTreeNode == false) {
				final String msg = "类:" + pClassName + "的父类不是:"
						+ WebTreeBuilder.class.getName();
				logger.error(msg);
				throw new IllegalArgumentException(msg);
			}
			return (WebTreeBuilder) obj;
		} catch (Exception e) {
			final String msg = "创建类:" + pClassName + "实例失败";

			throw new CreateObjectException(msg, e);

		}
	}
}
