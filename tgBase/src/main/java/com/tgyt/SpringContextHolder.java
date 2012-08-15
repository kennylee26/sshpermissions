/**   
  * @Title: SpringContextHolder.java 
  * @Package com.tgyt 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-7-27 下午02:34:06 
  * @version V1.0   
  */

package com.tgyt;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/** 
 * @ClassName: SpringContextHolder 
 * @Description: 
 * 与Spring相关的工具类,该类的所有成员方法均为静态方法,用以获取Spring容器中的
 * 上下文信息
 * @author zhangfeng 13940488705@163.com
 * @date 2011-7-27 下午02:34:06 
 *  
 */
public class SpringContextHolder implements ApplicationContextAware, BeanFactoryPostProcessor{
private static ApplicationContext applicationContext;
	

	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
	
	public static Object getBean(Class clzz){
		return getBean(clzz.getName());
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;

	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static ServletContext getServletContext(){
		if(applicationContext instanceof WebApplicationContext){
			return ((WebApplicationContext)applicationContext).getServletContext();
		}
		return null;
	}
	

	//	加Bean工厂后置处理器,只是为了适应spring的IOC加载顺序,使这个类在启动时第一个被实例化并被加载
	public void postProcessBeanFactory(	ConfigurableListableBeanFactory beanFactory) throws BeansException {
		;
	}
}
