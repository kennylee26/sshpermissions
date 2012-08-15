/**   
 * @Title: CmdConstants.java 
 * @Package com.tgyt.common.tools.cmd.test 
 * @Description: 
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:40:31 
 * @version V1.0   
 */
package com.tgyt.common.tools.cmd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * @ClassName: RunCmd
 * @Description: 用java文件去执行dos命令
 * @author sunqiang sunqiangbingxue@sina.com
 * @date 2011-9-20 上午9:49:57
 * 
 */

public class RunCmd {
	static Logger logger = Logger.getLogger(RunCmd.class.getName());

	/**
	 * @Title: cmd
	 * @Description: 用java执行类似于命令行中得命令，环境已经配好
	 * @param @param cmd
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String cmd(String cmd) {
		String line = "";
		try {
			String command = RunCmd.command() + cmd;
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String newLine = "";
			while ((newLine = br.readLine()) != null) {
				line = line + newLine + "\n";
			}
			br.close();
			logger.info(line);
		} catch (Exception e) {
			final String msg = "您输入命令错误，请输入正确命令";
			throw new ExcuteCmdException(msg, e);
		}
		return line;
	}

	/**
	 * @Title: pathCmd
	 * @Description: 用java去执行特定路径下的命令
	 * @param @param path
	 * @param @param cmd
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String pathCmd(String path, String cmd) {
		String line = "";
		try {
			String command = RunCmd.command() + cmd;
			File dir = new File(path);
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(command, null, dir);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String newLine = "";
			while ((newLine = br.readLine()) != null) {
				line = line + newLine + "\n";
			}
			logger.info(line);
			br.close();

		} catch (Exception e) {
			final String msg = "您输入路径或者命令错误，请输入正确命令或路径";
			throw new ExcuteCmdException(msg, e);
		}
		return line;
	}

	/**
	 * @Title: enterCmd
	 * @Description: 执行命令中带有自动输入回车的命令
	 * @param @param path
	 * @param @param cmd
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String enterCmd(String path, String cmd) {
		String line = "";
		Process process = null;
		OutputStream out = null;
		BufferedInputStream in = null;
		try {
			String command = RunCmd.command() + cmd;
			File dir = new File(path);
			Runtime r = Runtime.getRuntime();
			process = r.exec(command, null, dir);
			out = process.getOutputStream();
			in = new BufferedInputStream(process.getInputStream());
			out.write("\r\n".getBytes());
			out.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String newLine = "";
			while ((newLine = br.readLine()) != null) {
				line = line + newLine + "\n";
			}
			logger.info(line);
			br.close();

		} catch (Exception e) {
			final String msg = "您输入路径或者命令错误，请输入正确命令或路径";
			throw new ExcuteCmdException(msg, e);
		}
		return line;
	}

	/**
	 * @Title: command
	 * @Description: 获取执行命令根目录环境
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String command() {
		String rootPath = System.getenv("SystemRoot");
		String command = rootPath + "\\system32\\cmd.exe /c ";
		return command;
	}
}
