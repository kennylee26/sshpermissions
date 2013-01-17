/**   
  * @Title: LogInterceptor.java 
  * @Package com.tgyt.permissions.interceptor 
  * @Description: 
  * @author ligangying ligangying1987@163.com 
  * @date 2011-9-28 下午5:53:41 
  * @version V1.0   
  */

package com.tgyt.permissions.interceptor;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jndi.cosnaming.IiopUrl.Address;
import com.tgyt.permissions.biz.ILogService;
import com.tgyt.permissions.model.Logs;
import com.tgyt.permissions.model.Users;

/** 
 * @ClassName: LogInterceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author ligangying ligangying1987@163.com 
 * @date 2011-9-28 下午5:53:41 
 *  
 */
@Component
public class LogInterceptor {
	@Autowired
	private ILogService logService;
	
	public Object invoke(ProceedingJoinPoint point) throws Throwable{
		Object result = null;
		//执行方法名
		String name = point.getSignature().getName();
		Long start = System.currentTimeMillis();
		result = point.proceed();
		Long end = System.currentTimeMillis();
		//日志需要的dongdong
		//当前用户
		String user = (String) SecurityUtils.getSubject().getPrincipal();
		//操作类型
		String opertype = "";
		if(name.contains("save")){
			opertype = "save操作";
		}
		if(name.contains("update")){
			opertype = "update操作";
		}
		if(name.contains("del")){
			opertype = "delte操作";
		}
		//内容
		String content = user + ",用户执行" + name + "方法";
		//ip
		InetAddress host = InetAddress.getLocalHost();
		String ip = host.getHostAddress();
		//所耗时间
		Long time = end - start;
		//日志时间
		Date date = new Date();
		java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//备注
		String memo = name + "方法";
		
		Logs log = new Logs(1, opertype, content, time, ip, user,format.format(date), memo);
		logService.save(log);
		
		return result;
	}
}
