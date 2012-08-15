/**   
  * @Title: DefaultContext.java 
  * @Package com.tgyt.templateEngine.support 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午03:13:55 
  * @version V1.0   
  */

package com.tgyt.templateEngine.support;

import java.util.HashMap;
import java.util.Map;

import com.tgyt.templateEngine.Context;
import com.tgyt.templateEngine.NoSuchKeyException;

/** 
 * @ClassName: DefaultContext 
 * @Description: 默认环境设置
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午03:13:55 
 *  
 */
public class DefaultContext implements Context{

	/**
	 * 用来保存参数.
	 */
	private Map parametersMap = null;

	public DefaultContext(){
		parametersMap = new HashMap();
	}
	
	public DefaultContext(Map pMap){
		if ( pMap == null ){
			throw new NullPointerException(); 
		}
		parametersMap = pMap;
	}
	
	/**
	 * 获取所有参数,
	 * @return
	 */
	public Map getParameters() {
		return parametersMap;
	}

	/**
	 * 添加参数
	 * @param pKey
	 * @param value
	 */
    public Context put(String pKey, Object pValue) {
        this.getParameters().put(pKey, pValue);
        return this;
    }

    public Object get(String pKey){
    	return this.parametersMap.get(pKey);
    }

	/**
	 * 添加参数Map
	 * @param parameters
	 */
    public Context putAll(Map parameters) {
        this.getParameters().putAll(parameters);
        return this;
    }

    /**
     * 清除所有参数
     *
     */
    public void clear() {
        this.getParameters().clear();
    }

	public Context put(String pKey, byte pValue) {
		return put(pKey, new Byte(pValue) );
	}

	public Context put(String pKey, short pValue) {
		return put(pKey, new Short(pValue));
	}

	public Context put(String pKey, int pValue) {
		return put(pKey, new Integer(pValue) );
	}

	public Context put(String pKey, long pValue) {
		return put(pKey, new Long(pValue) );
	}

	public Context put(String pKey, float pValue) {
		return put(pKey, new Float(pValue) );
	}

	public Context put(String pKey, double pValue) {
		return put(pKey, new Double(pValue) );
	}

	public Context put(String pKey, boolean pValue) {
		return put(pKey, new Boolean(pValue) );
	}

	public boolean containsKey(String pKey) {
		return this.parametersMap.containsKey(pKey);
	}

	public byte getByte(String pKey) throws NoSuchKeyException {
		if ( this.containsKey(pKey) == false ){
			throw new NoSuchKeyException(pKey);
		}
		Byte result = (Byte)this.get(pKey);
		return result.byteValue();
	}

	public byte getByte(String pKey, byte pDefaultValue){
		if ( this.containsKey(pKey) == false ){
			return pDefaultValue;
		}
		return getByte(pKey);
		
	}

	public short getShort(String pKey) throws NoSuchKeyException {
		if ( this.containsKey(pKey) == false ){
			throw new NoSuchKeyException(pKey);
		}
		Short result = (Short)this.get(pKey);
		return result.shortValue();
	}

	public short getShort(String pKey, short pDefaultValue) {
		if ( this.containsKey(pKey) ){
			return pDefaultValue;
		}
		return getShort(pKey);
	}

	public int getInt(String pKey) throws NoSuchKeyException {
		if ( this.containsKey(pKey) == false ){
			throw new NoSuchKeyException(pKey);
		}
		Integer result = (Integer)this.get(pKey);
		return result.intValue();
	}

	public int getInt(String pKey, int pDefaultValue) {
		if ( this.containsKey(pKey) == false ){
			return pDefaultValue;
		}
		return getInt(pKey);
	}

	public long getLong(String pKey) throws NoSuchKeyException {
        if ( this.containsKey(pKey) == false ){
        	throw new NoSuchKeyException(pKey);
        }
        Long result = (Long)this.get(pKey);
		return result.longValue();
	}

	public long getLong(String pKey, long pDefaultValue) {
		if ( this.containsKey(pKey) == false ){
			return pDefaultValue;
		}
		return getLong(pKey);
	}

	public float getFloat(String pKey) throws NoSuchKeyException {
        if ( this.containsKey(pKey) == false ){
        	throw new NoSuchKeyException(pKey);
        }
        Float result = (Float)this.get(pKey);
        return result.floatValue();
	}

	public float getFloat(String pKey, float pDefaultValue) {
		if ( this.containsKey(pKey) == false ){
			return pDefaultValue;
		}
		return getFloat(pKey);
	}

	public double getDouble(String pKey) throws NoSuchKeyException {
        if ( this.containsKey(pKey) == false ){
        	throw new NoSuchKeyException(pKey);
        }
        Double result = (Double)this.get(pKey);
        return result.doubleValue();
	}

	public double getDouble(String pKey, double pDefaultValue) {
        if ( this.containsKey(pKey) == false ){
        	return pDefaultValue;
        }
        return getDouble(pKey);
        
	}

	public boolean getBoolean(String pKey) throws NoSuchKeyException {
        if ( this.containsKey(pKey) == false ){
        	throw new NoSuchKeyException(pKey);
        }
        Boolean result = (Boolean)this.get(pKey);
        return result.booleanValue();
	}

	public boolean getBoolean(String pKey, boolean pDefaultValue) {
        if ( this.containsKey(pKey) == false ){
        	return pDefaultValue;
        }
        return getBoolean(pKey);
	}
	
}