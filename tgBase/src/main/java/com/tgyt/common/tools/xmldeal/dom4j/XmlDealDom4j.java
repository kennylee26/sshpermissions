package com.tgyt.common.tools.xmldeal.dom4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/** 
  * @ClassName: XmlDealDom4j 
  * @Description: DOM4J工具类，用来进行XML的操作 
  * @author sunct sunchaotong18@163.com 
  * @date 2012-2-3 下午2:41:06 
  *  
  */
public class XmlDealDom4j {
	/** 
	  * @Title: getDocument 
	  * @Description:从指定的路径读取文档内容，并转换成Document对象返回 
	  * @param @param path
	  * @param @return
	  * @return Document
	  * @throws 
	  */
	public static Document getDocument(String path){
			
		    InputStream in = XmlDealDom4j.class.getResourceAsStream(path);
			SAXReader reader = new SAXReader();
			Document document = null;
			try {
				document = reader.read(in);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return document;
	}
	/** 
	  * @Title: getRootElement 
	  * @Description:获得Document的跟元素
	  * @param @param document
	  * @param @return
	  * @return Element
	  * @throws 
	  */
	public static Element getRootElement(Document document){
		Element rootElement = document.getRootElement();
		return rootElement;
	}
	/** 
	  * @Title: getChildrenElement 
	  * @Description:获得指定ELement的孩子Element 
	  * @param @param element
	  * @param @return
	  * @return List<Element>
	  * @throws 
	  */
	public static List<Element> getChildrenElement(Element element){
		List<Element> elementList = element.elements();
		return elementList;
	}
	/** 
	  * @Title: getAttributes 
	  * @Description:获得指定Element的所有属性集合 
	  * @param @param element
	  * @param @return
	  * @return List<Attribute>
	  * @throws 
	  */
	public static List<Attribute> getAttributes(Element element){
		List<Attribute> list = element.attributes();
		return list;
	}
	

    /** 
      * @Title: createDocument 
      * @Description: 创建root元素为rootName的Document对象并返回。 
      * @param @param rootName
      * @param @return
      * @return Document
      * @throws 
      */
    public static Document createDocument(String rootName) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement(rootName);
        return doc;
    }
    /** 
      * @Title: addElement 
      * @Description:在指定的Element中添加指定名称的子ELement，并返回父节点
      * @param @param parent
      * @param @param elementName
      * @param @return
      * @return Element
      * @throws 
      */
    public static Element addElement(Element parent, String elementName) {
    	return parent.addElement(elementName);
    }
   
    /** 
      * @Title: removeElementByAttribute 
      * @Description: 在Document对象中，以elementName，attributeName，attributeValue为参数删除对应的Element元素。
      * @param @param document
      * @param @param elementName
      * @param @param attributeName
      * @param @param attributeValue
      * @param @return
      * @return Document
      * @throws 
      */
    public static Document removeElementByAttribute(Document document, String elementName,String attributeName,String attributeValue) {
          List<Element> list = document.getRootElement().elements(elementName);        
        exit:   for (Element element : list) {
             for(Iterator it=element.attributeIterator();it.hasNext();){
                 Attribute attribute = (Attribute) it.next();   
                 if(attribute.getName().equals(attributeName)&&attribute.getValue().equals(attributeValue)){
                     element.getParent().remove(element);
                     break exit;
                 }
             }           
        }
          return document ;        
    }
    /** 
      * @Title: removeElementByElementName 
      * @Description:在Document对象中，以elementName为参数删除对应的Element元素。 
      * @param @param document
      * @param @param elementName
      * @param @return
      * @return Document
      * @throws 
      */
    public static Document removeElementByElementName(Document document, String elementName) {
    	List<Element> list = getElementsByName(document.getRootElement(),elementName);        
    	for (Element element : list) {
    		for(Iterator it=element.attributeIterator();it.hasNext();){
				element.getParent().remove(element);
    		}           
    	}
    	return document ;        
    }
    
    /** 
      * @Title: getElementByName 
      * @Description:获得指定element下，指定名称的element集合 
      * @param @param parent
      * @param @param elementName
      * @param @return
      * @return List<Element>
      * @throws 
      */
    public static List<Element> getElementsByName(Element parent,String elementName){
    	return parent.elements(elementName);
    }
    /** 
      * @Title: writeDocumentToFile 
      * @Description:将Document对象写入指定路径的文件中 
      * @param @param document
      * @param @param filePath
      * @param @throws IOException
      * @return void
      * @throws 
      */
    public static void writeDocumentToFile(Document document, String filePath)throws IOException {
        XMLWriter writer = new XMLWriter(new FileWriter(filePath));
        writer.write(document);
        writer.flush();
        writer.close();
    }

}


