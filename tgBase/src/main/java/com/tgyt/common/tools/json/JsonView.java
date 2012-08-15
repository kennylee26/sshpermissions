/**   
  * @Title: JsonView.java 
  * @Package com.tgyt.common.tools.json 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-15 下午02:15:54 
  * @version V1.0   
  */

package com.tgyt.common.tools.json;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: JsonView 
 * @Description: 处理json数据信息
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-15 下午02:15:54 
 *  
 */
public class JsonView {
    private String json;
    
    public JsonView(){
        json = "{}";
    }
    
    public JsonView(Map<String,Object> map){
        json = Json.toJson(map);
    }
    
    public JsonView(List<?> list){
        json = Json.toJson(list);
    }
    
    public JsonView(Object[] objects){
        json = Json.toJson(objects);
    }
    
    public JsonView(Object obj){
        json = Json.toJson(obj);
    }
    
    /**
     * 用字符串构造JSON视图
     * @param str 字符串表示的JSON表达式，如"success:true,age:32,salary:2000.50,name:'名称'"
     */
    public JsonView(String str){
        Map<String,Object> map = parseStr(str);
        json = Json.toJson(map);
    }
    
    @Override
    public String toString(){
        return json;
    }
    
    private Map<String,Object> parseStr(String str){
        Map<String,Object> map = new HashMap<String,Object>();
        for(String strPart: str.split(",")){
            String[] ss = strPart.split(":");
            if (ss == null || ss.length != 2){
                continue;
            }
            String key = ss[0];
            String value = ss[1].trim();
            if (value.startsWith("'") && value.endsWith("'")){
                map.put(key, value.substring(1, value.length()-1));
            }
            else if (value.startsWith("\"") && value.endsWith("\"")){
                map.put(key, value.substring(1, value.length()-1));
            }
            else if (value.equals("true") || value.equals("false")){
                map.put(key, Boolean.valueOf(value));
            }
            else if (value.indexOf(".") == -1){
                try{
                    int val = Integer.parseInt(value);
                    map.put(key, val);
                }
                catch(Exception e){
                    map.put(key, value);
                }
            }
            else{
                try{
                    BigDecimal val = new BigDecimal(value);
                    map.put(key, val);
                }
                catch(Exception e){
                    map.put(key, value);
                }
            }
        }
        return map;
    }
}
