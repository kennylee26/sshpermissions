/**   
 * @Title: IDHelper.java 
 * @Package com.tgyt.common.id.util 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午02:35:30 
 * @version V1.0   
 */

package com.tgyt.common.id.util;

import com.tgyt.common.id.fomater.DefaultSequenceFormater;
import com.tgyt.common.id.generator.DefaultIDGenerator;
import com.tgyt.common.id.generator.UUIDGenerator;
import com.tgyt.common.id.prefix.DefaultPrefixGenerator;
import com.tgyt.common.id.sequence.DefaultSequenceGenerator;

/**
 * @ClassName: IDHelper
 * @Description: id生成帮助
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:35:30
 * 
 */
public abstract class IDHelper {

	private static final UUIDGenerator uuidGenerator = new UUIDGenerator();

	private static final DefaultIDGenerator tgidGenerator = new DefaultIDGenerator();

	static {

		DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
		prefixGenerator.setWithDate(true);
		tgidGenerator.setPrefixGenerator(prefixGenerator);

		// 序号生成器
		DefaultSequenceGenerator sequenceGenerator = new DefaultSequenceGenerator(
				"com-tgyt-common-id");
		sequenceGenerator.setMinValue(0);
		sequenceGenerator.setMaxValue(999999999999L);
		sequenceGenerator.setCycle(true);
		sequenceGenerator.setCache(1000);
		tgidGenerator.setSequenceGenerator(sequenceGenerator);

		DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater();
		sequenceFormater.setPattern("000000000000");
		tgidGenerator.setSequenceFormater(sequenceFormater);
	}

	private IDHelper() {
	}

	public static String uuid() {
		return uuidGenerator.create();
	}

	public static String e3id() {
		return tgidGenerator.create();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(IDHelper.e3id());
		}

	}

}