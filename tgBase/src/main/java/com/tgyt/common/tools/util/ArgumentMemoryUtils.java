/**   
  * @Title: ArgumentMemoryUtils.java 
  * @Package com.tgyt.permissions.listener 
  * @Description: 
  * @author ligangying ligangying1987@163.com 
  * @date 2011-10-2 上午11:43:38 
  * @version V1.0   
  */

package com.tgyt.common.tools.util;

import java.util.HashMap;
import java.util.Map;

/** 
  * @ClassName: ArgumentMemoryUtils 
  * @Description: 全局参数工具类，用来同步数据库数据到内存
  * @author sunct sunchaotong18@163.com 
  * @date 2011-10-6 上午10:19:17 
  *  
  */
public final class ArgumentMemoryUtils {
	/** 
	  * @Fields argumentsMap :存放全局参数的Map 
	  */ 
	private static Map<String,String> argumentsMap = new HashMap<String ,String>();

	/** 
	  * @ClassName: ArgumentsMemoryUtilsHolder 
	  * @Description: 私有静态类，用来存放单例
	  * @author sunct sunchaotong18@163.com 
	  * @date 2011-10-6 下午2:48:57 
	  *  
	  */
	private static class ArgumentsMemoryUtilsHolder{
		static final ArgumentMemoryUtils INSTANCE = new ArgumentMemoryUtils();
	}
	
	/** 
	  * @Title: getInstance 
	  * @Description: 单例方法 
	  * @param @return
	  * @return ArgumentMemoryUtils
	  * @throws 
	  */
	public static ArgumentMemoryUtils getInstance(){
		return ArgumentsMemoryUtilsHolder.INSTANCE;
	}
	
	/** 
	  * <p>Title: 私有构造方法</p> 
	  * <p>Description: 为了实现单例</p>  
	  */ 
	private ArgumentMemoryUtils(){
	}
	/** 
	  * @Title: getValueByName 
	  * @Description:根据参数名称得到参数值
	  * @param @param name
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public  String getValueByName(String name){
		return argumentsMap.get(name);
	}
	
	
	/** 
	  * @Title: getArgumentsMap 
	  * @Description:获得参数map 
	  * @param @return
	  * @return Map<String,String>
	  * @throws 
	  */
	public  Map<String, String> getArgumentsMap() {
		return argumentsMap;
	}
	
	/** 
	  * @Title: setArgumentsMap 
	  * @Description: 设置参数map
	  * @param @param argumentsMap
	  * @return void
	  * @throws 
	  */
	public  void setArgumentsMap(Map<String, String> argumentsMap) {
		ArgumentMemoryUtils.argumentsMap = argumentsMap;
	}
}
