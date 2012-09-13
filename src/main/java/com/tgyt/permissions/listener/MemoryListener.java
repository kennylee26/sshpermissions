/**   
  * @Title: MemoryListener.java 
  * @Package com.tgyt.permissions.listener 
  * @Description: 
  * @author ligangying ligangying1987@163.com 
  * @date 2011-10-2 上午9:56:33 
  * @version V1.0   
  */

package com.tgyt.permissions.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tgyt.common.tools.util.ArgumentMemoryUtils;
import com.tgyt.common.tools.util.DictionaryMemoryUtils;
import com.tgyt.permissions.dao.ArgumentsDAO;
import com.tgyt.permissions.dao.DictionaryDao;
import com.tgyt.permissions.model.Arguments;
import com.tgyt.permissions.model.Dictionarys;


/** 
  * @ClassName: MemoryListener 
  * @Description: 初始化全局参数和系统字典 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-10-6 下午3:03:16 
  *  
  */
public class MemoryListener implements ServletContextListener {
	//系统字典Service层
	private DictionaryDao dictionaryDao;
	//全局参数
	private ArgumentsDAO argumentsDAO;
	private ArgumentMemoryUtils argumentUtils = ArgumentMemoryUtils.getInstance();
	private DictionaryMemoryUtils dictionaryUtils = DictionaryMemoryUtils.getInstance();
	
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("tomcat 开始结束");
		dictionaryUtils.getDictionarysMap().clear();
		System.out.println(dictionaryUtils.getDictionarysMap() + "======================");
		argumentUtils.getArgumentsMap().clear();
		System.out.println(argumentUtils.getArgumentsMap() + "========================");
		System.out.println("tomcat 结束完毕");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		
		
		this.setDictionaryDao((DictionaryDao)ctx.getBean("dictionaryDao"));
		this.setArgumentsDAO((ArgumentsDAO)ctx.getBean("argumentsDAO"));
		
		System.out.println("tomcat 开始启动");
		//系统字典读进内存
		List<String> nickNames = new ArrayList<String>();
		Map<String,Map<String,String>> dictionarysMap = new HashMap<String,Map<String,String>>();
		nickNames = dictionaryDao.getHandler().findListOfObj("SELECT distinct nickName from Dictionarys");//baseDAO.findListBySql("SELECT distinct nickname from c_dictionarys", Dictionarys.class);
		for(Iterator<String> iter=nickNames.iterator();iter.hasNext();){
			String nick =  iter.next();
			List<Dictionarys> dics = dictionaryDao.findList("FROM Dictionarys where nickName='"+nick+"'");
			Map<String,String> maps = new HashMap<String,String>();
			for(Iterator<Dictionarys> one=dics.iterator();one.hasNext();){
				Dictionarys d = one.next();
				maps.put(d.getValue(), d.getName());//Map<value,name>
			}
			dictionarysMap.put(nick, maps);
		}
		dictionaryUtils.setDictionarysMap(dictionarysMap);
		//全局参数读进内存
		
		List<Arguments> list = new ArrayList<Arguments>();
		list = argumentsDAO.findList("from Arguments");
		Map<String,String> argumentsMap = new HashMap<String ,String>();
		for(Iterator<Arguments> iter=list.iterator();iter.hasNext();){
			Arguments arg = iter.next();
			argumentsMap.put(arg.getName(), arg.getValue());
		}
		argumentUtils.setArgumentsMap(argumentsMap);
		
		System.out.println("tomcat 读入内存完毕!");
//		System.out.println(dictionaryUtils.getDictionarysMap() + "======================");
//		System.out.println(argumentUtils.getArgumentsMap() + "========================");
	}

	/**
	 * @return the argumentsDAO
	 */
	public ArgumentsDAO getArgumentsDAO() {
		return argumentsDAO;
	}

	/**
	 * @param argumentsDAO the argumentsDAO to set
	 */
	public void setArgumentsDAO(ArgumentsDAO argumentsDAO) {
		this.argumentsDAO = argumentsDAO;
	}

	/**
	 * @return the dictionaryDao
	 */
	public DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	/**
	 * @param dictionaryDao the dictionaryDao to set
	 */
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}


}
