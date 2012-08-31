package com.bjpowernode.drp.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Bean工厂
 * @author Administrator
 *
 */
public class BeanFactory {

	private static BeanFactory instance = new BeanFactory();
	
	//存放产品{key=产品编号，value=具体产品实例}
	private Map beans = new HashMap();
	
	private Document doc;
	
	private BeanFactory() {
		try {
			doc = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream("applicationContext.xml"));
		} catch (DocumentException e) {
			throw new AppException("系统错误，请联系系统管理员");
		}
	}
	
	public static BeanFactory getInstance() {
		return instance;
	}
	
//	/**
//	 * 根据产品标识得到产品
//	 * @param id
//	 * @return
//	 */
//	public synchronized Object  getBean(String id) {
//		//如果在Map存在已经创建的产品返回
//		if (beans.containsKey(id)) {
//			return beans.get(id);
//		}
//		//查找xml文件中id属性等于某值的bean标签
//		Element elt = (Element)doc.selectObject("//bean[@id=\"" + id + "\"]");
//		Object object = null;
//		try {
//			object = Class.forName(elt.attributeValue("class")).newInstance();
//			//将创建好的产品放入到Map中
//			beans.put(id, object);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new AppException("系统错误，请联系系统管理员");
//		}
//		return object;
//	}
	
//	/**
//	 * 根据产品标识得到产品
//	 * @param id
//	 * @return
//	 */
//	public  Object  getBean(String id) {
//		synchronized(this) {
//			//如果在Map存在已经创建的产品返回
//			if (beans.containsKey(id)) {
//				return beans.get(id);
//			}
//			//查找xml文件中id属性等于某值的bean标签
//			Element elt = (Element)doc.selectObject("//bean[@id=\"" + id + "\"]");
//			Object object = null;
//			try {
//				object = Class.forName(elt.attributeValue("class")).newInstance();
//				//将创建好的产品放入到Map中
//				beans.put(id, object);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new AppException("系统错误，请联系系统管理员");
//			}
//			return object;
//		}
//	}

	/**
	 * 根据产品标识得到产品
	 * @param id
	 * @return
	 */
	public  Object  getBean(Class c) {
		synchronized(beans) {
			//如果在Map存在已经创建的产品返回
			if (beans.containsKey(c.getName())) {
				return beans.get(c.getName());
			}
			//查找xml文件中id属性等于某值的bean标签
			
			Object object = null;
			try {
				Element elt = (Element)doc.selectObject("//bean[@id=\"" + c.getName() + "\"]");
				object = Class.forName(elt.attributeValue("class")).newInstance();
				//将创建好的产品放入到Map中
				beans.put(c.getName(), object);
			} catch (Exception e) {
				e.printStackTrace();
				throw new AppException("系统错误，请联系系统管理员");
			}
			return object;
		}
	}
}
