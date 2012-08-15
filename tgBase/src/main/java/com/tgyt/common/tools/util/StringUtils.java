/**   
  * @Title: StringUtils.java 
  * @Package com.tgyt.common.tools.util 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 上午10:52:51 
  * @version V1.0   
  */

package com.tgyt.common.tools.util;

import java.util.List;

/** 
 * @ClassName: StringUtils 
 * @Description: 判断字符串是否为空
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:52:51 
 *  
 */
public abstract class StringUtils {
	/**
     * The value needs to be a String[]. A String, Null, or List will be
     * converted to a String[]. In addition it will attempt to do a String
     * conversion for other object types.
     * 
     * @param value
     *            The value to convert to an String[]
     * @return A String[] value.
     */
    public static String[] getValueAsArray(Object value) {
        if (value == null) {
            return new String[] {}; // put in a placeholder
        }

        if (value instanceof String[]) {
            return (String[]) value;
        } else if (value instanceof List) {
            List valueList = (List) value;
            return (String[])valueList.toArray(new String[valueList.size()]);
        }

        return new String[] { value.toString() };
    }
    
    public static boolean isNotEmpty(String pStr){
    	return ! isEmpty(pStr); 
    }
    public static boolean isEmpty(String pStr){
    	if ( pStr == null ){
    		return true;
    	}
    	if ( "".equals(pStr) ){
    		return true;
    	}
    	return false;
    }
	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 * @param delim delimiter to use (probably a ",")
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (ObjectUtils.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * Convenience method to return a String array as a CSV String.
	 * E.g. useful for <code>toString()</code> implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}
}
