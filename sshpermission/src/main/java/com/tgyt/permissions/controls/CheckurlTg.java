package com.tgyt.permissions.controls;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.permissions.common.HasParent;
import com.tgyt.permissions.model.Resources;


/** 
  * @ClassName: ActionsTg 
  * @Description: 操作管理控制类 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午5:04:21 
  *  
  */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller(value="permissions.checkurlTgControl")
public class CheckurlTg extends BaseTg {
	
	private String pid;
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	/** 
	  * @Title: check 
	  * @Description: 空url时的处理函数
	  * @return String
	  * @throws 
	  */
	public String check(){
//		System.out.println(pid);
		HasParent.returnCurrentObj(pid,request);
//		System.out.println(request.getAttribute("jsonOne"));
//		System.out.println(request.getAttribute("jsonTwo"));
		return SUCCESS;
	}

	
}
