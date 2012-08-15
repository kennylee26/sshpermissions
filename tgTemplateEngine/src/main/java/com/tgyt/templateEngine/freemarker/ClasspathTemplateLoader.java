/**   
  * @Title: ClasspathTemplateLoader.java 
  * @Package com.tgyt.templateEngine.freemarker 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:00:13 
  * @version V1.0   
  */

package com.tgyt.templateEngine.freemarker;

import java.net.URL;

import freemarker.cache.URLTemplateLoader;

/** 
 * @ClassName: ClasspathTemplateLoader 
 * @Description: 类加载路径 
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:00:13 
 *  
 */
public class ClasspathTemplateLoader extends URLTemplateLoader{
    protected URL getURL(String name)
    {
    	URL result = null;
        
        if (name == null || name.length() == 0)
        {
            throw new  IllegalArgumentException("No template name provided");
        }
        
        try 
        {
            ClassLoader classLoader = this.getClass().getClassLoader();
            result= classLoader.getResource( name );
        }
        catch( Exception fnfe )
        {
            throw new RuntimeException("获取资源失败!资源:" + name);
        }
        
        return result;
    }

}
