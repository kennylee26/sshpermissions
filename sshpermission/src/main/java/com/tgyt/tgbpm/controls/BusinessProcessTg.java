/**   
  * @Title: BusinessProcessTg.java 
  * @Package com.tgyt.tgbpm.controls 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author sunct sunchaotong18@163.com 
  * @date 2011-12-14 下午4:57:12 
  * @version V1.0   
  */

package com.tgyt.tgbpm.controls;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.pvm.internal.stream.ByteArrayStreamInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Decoder;

import com.tgyt.framework.controls.struts2.BaseTg;
import com.tgyt.tgbpm.biz.ITgTaskUserService;

/** 
 * @ClassName: BusinessProcessTg 
 * @Description: 工作流action
 * @author sunct sunchaotong18@163.com 
 * @date 2011-12-14 下午4:57:12 
 *  
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("tgbpm.businessProcess")
public class BusinessProcessTg extends BaseTg{

	@Resource(name="processEngine")
	private ProcessEngine processEngine ;
	@Autowired
	private ITgTaskUserService tgTaskUserService;
	//发布的流程文件名称
	private String processDef;
	//流程部署ID
	private String deploymentId;
	//流程对应的图片资源名称
	private String imageResourceName;
	
	//发布的流程XML内容
	private String xml;
	//图片的字节数组的字符串
	private String images;

	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(String images) {
		this.images = images;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}


	public String getImageResourceName() {
		return imageResourceName;
	}

	public void setImageResourceName(String imageResourceName) {
		this.imageResourceName = imageResourceName;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessDef() {
		return processDef;
	}

	public void setProcessDef(String processDef) {
		this.processDef = processDef;
	}

	/** 
	  * @Title: index 
	  * @Description: 索引工作流部署主页
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String index(){
		return SUCCESS;
	}
	/** 
	  * @Title: getItems 
	  * @Description: 获得部署的工作流列表
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void getItems(){
		try{
			RepositoryService repositoryService = processEngine.getRepositoryService();
			int begin = (page-1)*rows;
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().page(begin, rows).list();
			List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
			for(Iterator<ProcessDefinition> iter=list.iterator();iter.hasNext();){
				ProcessDefinition def = iter.next();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", def.getName());
				map.put("version", def.getVersion());
				map.put("deploymentId", def.getDeploymentId());
				map.put("description", def.getDescription());
				map.put("id", def.getId());
				map.put("imageResourceName", def.getImageResourceName());
				map.put("key", def.getKey());
				maps.add(map);
			}
			JSONArray json = JSONArray.fromObject(maps);
			outJsonPlainString(response, "{\"total\":"+list.size()+",\"rows\":"+json.toString()+"}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"total\":0,\"rows\":[]}");
		}
	}
	/** 
	  * @Title: save 
	  * @Description:部署保存工作流程
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void save(){
		try{
			RepositoryService repositoryService = processEngine.getRepositoryService();
			InputStream in = new FileInputStream(new File(processDef));
			ZipInputStream zin = new ZipInputStream(in);
			repositoryService.createDeployment().addResourcesFromZipInputStream(zin).deploy();
			zin.close();
			in.close();
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	/**
	 * 将流程设计器中定义的文件流保存到JBPM4.4中
	  * @Title: saveAsJbpm4 
	  * @Description: TODO(这里用一句话描述这个方法的作用) 
	  * @param 
	  * @return void
	  * @throws
	 */
	public void saveAsJbpm4(){
		try{
			RepositoryService repositoryService = processEngine.getRepositoryService();
			NewDeployment deployment = repositoryService.createDeployment();
			//发布的流程XML内容
			if(processDef!=null && !"".equals(processDef) && xml!=null && !"".equals(xml)){
				deployment.addResourceFromString(processDef, xml);
			}
			if(imageResourceName!=null && !"".equals(imageResourceName)){
				BASE64Decoder decoder = new BASE64Decoder();
				InputStream input = new ByteArrayInputStream(decoder.decodeBuffer(images));
				deployment.addResourceFromInputStream(imageResourceName, input);
			}
			deployment.deploy();
			
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"success\":false}");
		}
	}
	public void saveAsJbpm4Image(){
		try{
			RepositoryService repositoryService = processEngine.getRepositoryService();
			NewDeployment deployment = repositoryService.createDeployment();
//			if(imageResourceName!=null && !"".equals(imageResourceName) && images!=null && images.length>0){
//				System.out.println("=========================="+Arrays.toString(images));
//				BASE64Decoder decoder = new BASE64Decoder();
//				InputStream input = new ByteArrayInputStream((Arrays.toString(images)).getBytes());
//				deployment.addResourceFromInputStream(imageResourceName, input);
//			}
			deployment.addResourceFromInputStream("leave.png", request.getInputStream());
			deployment.deploy();
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"success\":false}");
		}
	}
	/** 
	  * @Title: delete 
	  * @Description:删除指定的工作流文件
	  * @param 
	  * @return void
	  * @throws 
	  */
	public void delete(){
		try{
			RepositoryService repositoryService = processEngine.getRepositoryService();
			List<ProcessDefinition> processDef = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).list();
			//将流程任务人员关系表中的对应数据删除
			tgTaskUserService.deleteByProcessKey(processDef.iterator().next().getKey());
			repositoryService.deleteDeploymentCascade(deploymentId);
			outJsonPlainString(response, "{\"success\":true}");
		}catch(Exception e){
			e.printStackTrace();
			outJsonPlainString(response, "{\"error\":true}");
		}
	}
	
	public void getImage(){
		try{
			processEngine.getHistoryService().createHistoryTaskQuery().list();
			RepositoryService repositoryService = processEngine.getRepositoryService();
			InputStream inputStream = repositoryService.getResourceAsStream(deploymentId,imageResourceName);
			//InputStream inputStream =new FileInputStream(new File("D:/program/java/servers/apache-tomcat-5.5.23/webapps/jbpm2/WEB-INF/classes/leave.png"));
			byte[] b = new byte[1024];
			int len = -1; 
			ServletOutputStream sos=response.getOutputStream();
			while ((len = inputStream.read(b, 0, 1024)) != -1) {
				sos.write(b, 0, len);
			}
			inputStream.close();
			sos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
