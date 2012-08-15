/**   
 * @Title: ZipUtils.java 
 * @Package com.tgyt.common.tools.util 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 上午10:54:24 
 * @version V1.0   
 */

package com.tgyt.common.tools.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: ZipUtils
 * @Description: 文件压缩处理
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 上午10:54:24
 * 
 */
public class ZipUtils {
	private final static Log logger = LogFactory.getLog(ZipUtils.class);

	/**
	 * 解压缩,注意:文件名不能包含中文.
	 * 
	 * @param in
	 * @param outputDirectory
	 * @throws Exception
	 */
	public static void unzip(final ZipInputStream in, String outputDirectory)
			throws Exception {
		if (in == null) {// 输入流为空
			return;
		}
		if (outputDirectory == null) {
			final String MSG = "输出目录为空null,无法解压";
			logger.debug(MSG);
			return;
		}

		File dirFile = new File(outputDirectory);
		if (dirFile.exists() == false) {
			dirFile.mkdirs();
		}

		ZipEntry z;
		while ((z = in.getNextEntry()) != null) {
			if (z.isDirectory()) {
				String name = z.getName();
				name = name.substring(0, name.length() - 1);
				final String dir = outputDirectory + File.separator + name;
				File f = new File(dir);
				if (f.exists() == false)
					if (f.mkdir() == false) {
						final String MSG = "创建目录失败!" + dir;
						logger.error(MSG);
						throw new Exception(MSG);
					}
			} else {
				final String file = outputDirectory + File.separator
						+ z.getName();
				File f = new File(file);
				if (f.exists() == true) {
					f.delete();
				}
				FileOutputStream fos = new FileOutputStream(f);
				try {
					final int cache = 2048;
					byte[] b = new byte[cache];
					int aa = 0;
					while ((aa = in.read(b)) != -1) {
						fos.write(b, 0, aa);
					}
				} finally {
					fos.close();
				}
			}
		}
	}
}
