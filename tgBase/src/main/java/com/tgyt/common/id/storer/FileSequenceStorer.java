/**   
 * @Title: FileSequenceStorer.java 
 * @Package com.tgyt.common.id.storer 
 * @Description: 
 * @author zhangfeng 13940488705@163.com 
 * @date 2011-8-9 下午02:30:53 
 * @version V1.0   
 */

package com.tgyt.common.id.storer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tgyt.common.id.SequenceStorer;
import com.tgyt.common.id.StoreSequenceException;
import com.tgyt.common.id.test.Hello;

/**
 * @ClassName: FileSequenceStorer
 * @Description: 文件序列保存
 * @author zhangfeng 13940488705@163.com
 * @date 2011-8-9 下午02:30:53
 * 
 */
public class FileSequenceStorer implements SequenceStorer {

	private static final Log logger = LogFactory
			.getLog(FileSequenceStorer.class);

	public static final String DEFAULT_FILE_PATH = "com-tgyt-common-id-sequence-store.properties";

	/**
	 * 文件路径，包含文件名，
	 */
	private String filePath = DEFAULT_FILE_PATH;

	protected String getRealFilePath() throws StoreSequenceException {
		java.io.File tmp = new java.io.File(filePath);
		if (tmp.exists()) {
			return this.filePath;
		}
		URL url = Hello.class.getClassLoader().getResource(this.filePath);
		if (url == null) {
			final String msg = "存储sequence失败!没有发现文件：" + filePath;
			logger.error(msg);
			throw new StoreSequenceException(msg);
		}
		return url.getFile();
	}

	public long load(String sequenceID) throws StoreSequenceException {

		java.util.Properties props = new java.util.Properties();
		final String realFilePath = getRealFilePath();
		if (logger.isDebugEnabled()) {
			logger.debug("序号ID:[" + sequenceID + "]");
			logger.debug("资源路径:[" + this.filePath + "]");
			logger.debug("实际文件路径:[" + realFilePath + "]");
		}
		java.io.FileInputStream is = null;
		try {
			is = new java.io.FileInputStream(realFilePath);
			props.load(is);
			String result = props.getProperty(sequenceID);
			if (result == null) {
				return -1;
			} else {
				return Long.parseLong(result);
			}
		} catch (FileNotFoundException e) {
			final String msg = "存储sequence失败!没有发现文件：" + realFilePath;
			logger.error(msg, e);
			throw new StoreSequenceException(msg, e);
		} catch (IOException e) {
			final String msg = "存储sequence失败!" + e.getMessage();
			logger.error(msg, e);
			throw new StoreSequenceException(msg, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					final String msg = "关闭文件:" + realFilePath + "失败!"
							+ e.getMessage();
					logger.debug(msg, e);
				}
			}
		}
	}

	public void save(long sequence, String sequenceID)
			throws StoreSequenceException {
		java.util.Properties props = new java.util.Properties();
		final String realFilePath = getRealFilePath();
		if (logger.isDebugEnabled()) {
			logger.debug("序号ID:[" + sequenceID + "]");
			logger.debug("资源路径:[" + this.filePath + "]");
			logger.debug("实际文件路径:[" + realFilePath + "]");
		}
		java.io.FileInputStream is = null;
		try {
			is = new java.io.FileInputStream(realFilePath);
			props.load(is);
			props.setProperty(sequenceID, sequence + "");
		} catch (FileNotFoundException e) {
			final String msg = "存储sequence失败!没有发现文件：" + realFilePath;
			logger.error(msg, e);
			throw new StoreSequenceException(msg, e);
		} catch (IOException e) {
			final String msg = "存储sequence失败!" + e.getMessage();
			logger.error(msg, e);
			throw new StoreSequenceException(msg, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					final String msg = "关闭文件:" + realFilePath + "失败!"
							+ e.getMessage();
					logger.debug(msg, e);
				}
			}
		}

		java.io.FileOutputStream out = null;
		try {
			out = new java.io.FileOutputStream(realFilePath);
			props.store(out, "tg id sequence storer, don't edit");
		} catch (FileNotFoundException e) {
			final String msg = "存储sequence失败!没有发现文件：" + realFilePath;
			logger.error(msg, e);
			throw new StoreSequenceException(msg, e);
		} catch (IOException e) {
			final String msg = "存储sequence失败!" + e.getMessage();
			logger.error(msg, e);
			throw new StoreSequenceException(msg, e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					final String msg = "关闭文件:" + realFilePath + "失败!"
							+ e.getMessage();
					logger.debug(msg, e);
				}
			}
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
