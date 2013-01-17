package com.tgyt.permissions.tree;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tgyt.tree.Node;
import com.tgyt.tree.TreeDirector;
import com.tgyt.tree.TreeModel;
import com.tgyt.tree.UserDataUncoder;
import com.tgyt.tree.easyui.EasyuiTreeBuilder;
import com.tgyt.tree.easyui.TgytTreeBuilder;
import com.tgyt.tree.support.AbstractWebTreeBuilder;
import com.tgyt.tree.support.AbstractWebTreeModelCreator;
import com.tgyt.tree.support.DefaultNodeComparator;
import com.tgyt.tree.support.DefaultTreeDirector;
import com.tgyt.tree.support.DefaultTreeModel;
import com.tgyt.tree.support.ReverseComparator;
import com.tgyt.tree.support.WebTreeNode;

public class Utils {
	public static String showeasyuiTree(UserDataUncoder orgUncoder,AbstractWebTreeModelCreator treeModelCreator,List list,HttpServletRequest qRequest){
		
		treeModelCreator.init(qRequest);
		TreeModel tempModel = treeModelCreator.create(list, orgUncoder);
		WebTreeNode virtualRootNode = new WebTreeNode("根节点", "Virtual");
		java.util.Iterator rootNodes = tempModel.getRootNodes();
		while (rootNodes.hasNext()) {
			Node rootNode = (Node) rootNodes.next();
			rootNode.setParent(virtualRootNode);
		}
		DefaultTreeModel treeModel = new DefaultTreeModel();
		treeModel.addRootNode(virtualRootNode);
		TreeDirector director = new DefaultTreeDirector();// 构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator()));
		AbstractWebTreeBuilder treeBuilder = new EasyuiTreeBuilder();// 构造树Builder
		treeBuilder.init(qRequest);
		director.build(treeModel, treeBuilder);// 执行构造
		return treeBuilder.getTreeScript();
	}
	
	public static String getTree(UserDataUncoder orgUncoder,AbstractWebTreeModelCreator treeModelCreator,List list,HttpServletRequest qRequest){
		treeModelCreator.init(qRequest);
		TreeModel tempModel = treeModelCreator.create(list, orgUncoder);
		
		WebTreeNode virtualRootNode = new WebTreeNode("根节点", "Virtual");
		java.util.Iterator rootNodes = tempModel.getRootNodes();
		while (rootNodes.hasNext()) {
			Node rootNode = (Node) rootNodes.next();
			rootNode.setParent(virtualRootNode);
		}
		DefaultTreeModel treeModel = new DefaultTreeModel();
		treeModel.addRootNode(virtualRootNode);
		TreeDirector director = new DefaultTreeDirector();// 构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator()));
		AbstractWebTreeBuilder treeBuilder = new TgytTreeBuilder();// 构造树Builder
		treeBuilder.init(qRequest);
		director.build(treeModel, treeBuilder);// 执行构造
		return treeBuilder.getTreeScript();
	}
}
