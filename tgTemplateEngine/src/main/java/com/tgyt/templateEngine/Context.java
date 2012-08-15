/**   
  * @Title: Context.java 
  * @Package com.tgyt.templateEngine 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:56:10 
  * @version V1.0   
  */

package com.tgyt.templateEngine;

import java.util.Map;

/** 
 * @ClassName: Context 
 * @Description: 数据容器，用于存储命名参数
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:56:10 
 *  
 */
public interface Context {

	/**
	 * 获取所有参数,
	 * @return
	 */
	public Map getParameters();

	/**
	 * 添加参数
	 * @param pKey
	 * @param value
	 */
    public Context put(String pKey, Object pValue);
    public Context put(String pKey, byte pValue);
    public Context put(String pKey, short pValue);
    public Context put(String pKey, int pValue);
    public Context put(String pKey, long pValue);
    public Context put(String pKey, float pValue);
    public Context put(String pKey, double pValue);
    public Context put(String pKey, boolean pValue);

    public Object get(String pKey);
    public boolean containsKey(String pKey);
    
    public byte getByte(String pKey) throws NoSuchKeyException;
    public byte getByte(String pKey, byte pDefaultValue);
    public short getShort(String pKey) throws NoSuchKeyException;
    public short getShort(String pKey, short pDefaultValue);
    public int getInt(String pKey) throws NoSuchKeyException;
    public int getInt(String pKey, int pDefaultValue);
    public long getLong(String pKey) throws NoSuchKeyException;
    public long getLong(String pKey, long pDefaultValue);
    public float getFloat(String pKey) throws NoSuchKeyException;
    public float getFloat(String pKey, float pDefaultValue);
    public double getDouble(String pKey) throws NoSuchKeyException;
    public double getDouble(String pKey, double pDefaultValue);
    public boolean getBoolean(String pKey) throws NoSuchKeyException;
    public boolean getBoolean(String pKey, boolean pDefaultValue);

	/**
	 * 添加参数Map
	 * @param parameters
	 */
    public Context putAll(Map parameters);

    /**
     * 清除所有参数
     *
     */
    public void clear();
}
