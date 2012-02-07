/**   
  * @Title: HashParent.java 
  * @Package com.tgyt.permissions.common 
  * @Description: 北京太谷雨田信息科技有限责任公司 版本所有
  * @author WangMing wang1988ming@qq.com 
  * @date 2011-10-5 上午11:09:29 
  * @version V1.0   
  */

package com.tgyt.permissions.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;

import com.tgyt.permissions.model.CloneResources;
import com.tgyt.permissions.model.Resources;

/** 
 * @ClassName: HashParent 
 * @Description: 判断是否有父节点
 * @author WangMing wang1988ming@qq.com
 * @date 2011-10-5 上午11:09:29 
 *  
 */
public class HasParent {
	
	/** 
	  * @Title: returnCurrentObj 
	  * @Description: 返回下一级的资源 
	  * @param @param id 父极的id
	  * @param @return
	  * @return JSONObject
	  * @throws 
	  */
	public static List<Resources> returnCurrentObj(String id,HttpServletRequest request){
		JSONArray jsonOne = null;
		JSONArray jsonTwo = null;
		
		List<Resources> result = new ArrayList<Resources>();
		List<Resources> list = (List<Resources>) SecurityUtils.getSubject().getSession().getAttribute("resourceList");
		Resources parent = null;
		if(null != id){
			for(int i=0;i<list.size();i++){
				if(!id.equals(list.get(i).getId().toString())){
					continue;
				}
				parent = list.get(i);
				for(int j=0;j<list.size();j++){
					Resources res = list.get(j);
					if(null == res.getParent() || !id.equals(res.getParent().getId().toString())){
						continue;
					}
					result.add(res);
				}
				break;
			}
			
		}else{
			for(int i=0;i<list.size();i++){
				if(null == list.get(i).getParent()){
					result.add(list.get(i));
				}
			}
		}
		Map<String,Object> secondMenu = new HashMap<String,Object>();
		if(result != null && result.size() > 0){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			Map<String,Object> middleMap = new HashMap<String,Object>();
			//树状图二级菜单集合
			List<Object> secondList = new ArrayList<Object>();
			String[] ids = new String[result.size()];
			for(int i=0;i<result.size();i++){
				ids[i] = result.get(i).getId().toString();
				
				Map<String,String> map = new HashMap<String,String>();
				map.put("icon", result.get(i).getIcon());
				map.put("name", result.get(i).getName());
				map.put("id", result.get(i).getId().toString());
				map.put("url", result.get(i).getLink());
				middleMap.put(result.get(i).getId().toString(), map);
				//将二级菜单放入集合中
				secondList.add(map);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("Icon", ids);
			jsonOne = JSONArray.fromObject(map);
			
			resultMap.put("app", middleMap);
			jsonTwo = JSONArray.fromObject(resultMap);
			//将二级菜单放入树型页面map中
			secondMenu.put("children", secondList);
		}
		if(parent!=null){
			Map<String,String> map = new HashMap<String,String>();
			map.put("icon", parent.getIcon());
			map.put("name", parent.getName());
			map.put("id", parent.getId().toString());
			map.put("url", parent.getLink());
			//将一级菜单放入属性页面map中
			secondMenu.put("parent", map);
			request.setAttribute("jsonTree", JSONArray.fromObject(secondMenu));
		}
//		System.out.println("=========================");
//		System.out.println(jsonOne);
//		System.out.println(jsonTwo);
//		System.out.println("=========================");
		if(jsonOne != null && jsonTwo != null){
			request.setAttribute("jsonOne", jsonOne.toString().substring(1, jsonOne.toString().length()-1));
			request.setAttribute("jsonTwo", jsonTwo.toString().substring(1, jsonTwo.toString().length()-1));
		}
		
		return result;
	}
	
	public static void isHasParent(){
		List<Resources> resourceList = (List<Resources>) SecurityUtils.getSubject().getSession().getAttribute("resourceList");
		Set<Resources> set = new HashSet<Resources>();
		if(resourceList != null && resourceList.size() > 0){
			for(int i=0;i<resourceList.size();i++){
				Resources temp = resourceList.get(i);
				if(null != temp.getParent()){
					set.add(temp.getParent());
					set.add(temp);
				}else{
					set.add(temp);
				}
			}
		}
		List<Resources> list = new ArrayList<Resources>();
		
		Iterator<Resources> it = set.iterator();
		while(it.hasNext()){
			list.add(it.next());
		}
		//将最后结果排序
		Collections.sort(list, new Comparator<Resources>(){
			
			public int compare(Resources o1, Resources o2) {
				return o1.getId().compareTo(o2.getId());
			}
			
		});
		SecurityUtils.getSubject().getSession().setAttribute("resourceList", list);
	}
}
