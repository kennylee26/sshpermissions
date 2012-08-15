/**   
  * @Title: AbstractUUIDGenerator.java 
  * @Package com.tgyt.common.id.generator 
  * @Description: 
  * @author zhangfeng 13940488705@163.com 
  * @date 2011-8-9 下午02:00:37 
  * @version V1.0   
  */

package com.tgyt.common.id.generator;

import java.net.InetAddress;

import com.tgyt.common.id.IDGenerator;

/** 
 * @ClassName: AbstractUUIDGenerator 
 * @Description: 代码来自hibernate
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:00:37 
 *  
 */
abstract class AbstractUUIDGenerator implements IDGenerator {

	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt( InetAddress.getLocalHost().getAddress() );
		}
		catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );
	public static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	
	public AbstractUUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class
	 * in the same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there
	 * are > Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized(AbstractUUIDGenerator.class) {
			if (counter<0) counter=0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) ( System.currentTimeMillis() >>> 32 );
	}
	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}


}
