/**   
  * @Title: FormatJSON.java 
  * @Package com.tgyt.data.model 
  * @Description: 
  * @author WangMing	wang1988ming@qq.com 
  * @date 2011-8-19 下午01:45:49 
  * @version V1.0   
  */

package com.tgyt.common.tools.json;

import java.lang.reflect.Field;
import java.util.List;



/** 
  * @ClassName: FormatJSON 
  * @Description: 处理List中JSON串各属性添加上变量名 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午3:29:15 
  * 
  * @param <T> 
  */
public class FormatJSON {

	
	/** 
	  * @Title: format 
	  * @Description: 该方法给集合转变成的JSON串中的各个属性添加变量名 
	  * @param @param varName 要添加的变量名
	  * @param @param list 要添加的集合
	  * @param @param excepts 哪些字段不需要添加变量名
	  * @param @return
	  * @param @throws Exception
	  * @return String
	  * @throws 
	  */
	public static String format(String varName, List list,String excepts) throws Exception{
		StringBuffer result = new StringBuffer("[");
		for(Object obj:list){
			Field[] fields = obj.getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			result.append("{");
			for(Field att:fields){
				Object o = att.get(obj);
				String value = Json.toJson(o);
				if(value!=null && value.indexOf("{")==-1 ){
					boolean flag = false;
					if(null!=excepts){
						for(String ex:excepts.split(";")){
							if(att.getName().equals(ex)){
								flag = true;
								break;
							}
						}
					}
					if(!flag){
						result.append("\""+varName+"."+att.getName()+"\":");
					}else{
						result.append("\""+att.getName()+"\":");
					}
					result.append(value);
				}else{
					result.append("\""+att.getName()+"\":");
//					JSONArray json = JSONArray.fromObject(o);
//					String str = json.toString();
					if(value.indexOf("[")==0){
						value=value.substring(1, value.length()-1);
					}
					result.append(value);
				}
				result.append(",");
			}
			result.deleteCharAt(result.toString().length()-1);
			result.append("},");
		}
		result.deleteCharAt(result.toString().length()-1).append("]");
		return result.toString();
	}
}
